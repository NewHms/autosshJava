package com.autossh.config.quartz;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.service.ServerConfigService;
import org.quartz.*;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class QuartzConfig {
    @Autowired
    private ServerConfigService serverConfigService;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //用于quartz集群,QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //任务调度监听类
        factory.setGlobalTriggerListeners(triggerListenerLogMonitor());
        return factory;
    }

    /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    @Bean
    public TriggerListenerLogMonitor triggerListenerLogMonitor() {
        return new TriggerListenerLogMonitor();
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        //添加同步任务 	author:sujin
        addmyTestJob(scheduler);
        return scheduler;
    }

    /**
     * 新增一个定时任务
     * @param scheduler
     * @author sujin
     */
    public void addmyTestJob(Scheduler scheduler){
        List<JSONObject> json = serverConfigService.getAllJobInfo();

        // 将数据库配置的任务全部添加
        for(int i = 0 ;i < json.size(); i++) {
            String jobName = json.get(i).getString("subject");
            String jobGroup = json.get(i).getString("group");
            String cron = json.get(i).getString("execTime");
            String className = json.get(i).getString("classPath");
            addCommonCronJob(jobName, jobGroup, cron, scheduler, className);
        }
    }

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     * @param scheduler
     */
    public void deleteCommonJob(String jobName, String jobGroup, Scheduler scheduler) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);//先暂停任务
            scheduler.deleteJob(jobKey);//再删除任务
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有定时任务
     * @param jobGroup
     * @param scheduler
     */
    public void deleteAllCommonJob(String jobGroup, Scheduler scheduler){
        GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(jobGroup);
        try{
            Set<JobKey> jobkeySet = scheduler.getJobKeys(matcher);
            List<JobKey> jobkeyList = new ArrayList<JobKey>();
            jobkeyList.addAll(jobkeySet);
            scheduler.deleteJobs(jobkeyList);
        }catch (SchedulerException e){
            e.printStackTrace();
        }

//        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
//        try {
//            scheduler.pauseJob(jobKey);//先暂停任务
//            scheduler.deleteJob(jobKey);//再删除任务
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 添加一些定时任务，如日志清理任务
     */
    public void addCommonCronJob(String jobName, String jobGroup, String cron, Scheduler scheduler, String className) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            //任务触发
            Trigger checkExist = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (checkExist == null) {
                JobDetail jobDetail = null;
                jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(className))
                        .requestRecovery(true)//当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务
                        .withIdentity(jobName, jobGroup)
                        .build();
                jobDetail.getJobDataMap().put("jobName", jobName);
                jobDetail.getJobDataMap().put("jobGroup", jobGroup);
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
                /*withMisfireHandlingInstructionDoNothing
                ——不触发立即执行
                ——等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
                withMisfireHandlingInstructionIgnoreMisfires
                ——以错过的第一个频率时间立刻开始执行
                ——重做错过的所有频率周期后
                ——当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行
                withMisfireHandlingInstructionFireAndProceed
                ——以当前时间为触发频率立刻触发一次执行
                ——然后按照Cron频率依次执行*/
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(jobName, jobGroup)
                        .withSchedule(cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires())
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
                addCommonCronJob(jobName, jobGroup, cron, scheduler, className);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void rescheduleJob(String jobName, String jobGroup, String cron, Scheduler scheduler) throws Exception
    {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new Exception("更新定时任务失败");
        }
    }

}
