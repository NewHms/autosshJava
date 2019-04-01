package com.autossh.quartz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.autossh.quartz.dao.QuartzDao;
import com.autossh.quartz.service.QuartzService;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzDao quartzDao;

    private ApplicationContext applicationContext;

    @Override
    public List<JSONObject> getJobInfo() {
       return quartzDao.getAllJobInfo();
    }

    @Override
    public JobDetail geJobDetail(JobKey jobKey, JobDataMap map,String className) throws Exception{
            return JobBuilder.newJob((Class<? extends Job>) Class.forName(className))
                    .withIdentity(jobKey)
                    .setJobData(map)
                    .storeDurably()
                    .build();
    }

    @Override
    public Trigger getTrigger(String jobName, String jobGroup,String cron) {
        return TriggerBuilder.newTrigger()
                         .withIdentity(jobName, jobGroup)
                         .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                         .build();
    }

    /**
     * 重启单个任务
     * @param jobName
     * @param jobGroup
     * @param cron
     * @param className
     * @throws Exception
     */
    @Override
    public void refresh(String jobName,String jobGroup,String cron,String className) throws Exception{
        // 先测试方法，测试通过后修改成通过ID去后台查的。
    /*    TriggerKey triggerKey = new TriggerKey(jobName, jobGroup);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        Scheduler scheduler = factory.getScheduler();
        JobDataMap map = new JobDataMap();
        map.put("jobName",jobName);
        map.put("jobGroup",jobGroup);
        map.put("cron",cron);
        map.put("className",className);*/
        //Scheduler  scheduler = (Scheduler) applicationContext.getParent().getBean("Scheduler");

        WebApplicationContext webContext = ContextLoaderListener.getCurrentWebApplicationContext();
        StdScheduler scheduler = (StdScheduler)webContext.getBean("Scheduler");
        try{
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
