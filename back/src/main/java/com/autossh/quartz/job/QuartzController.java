package com.autossh.quartz.job;

import com.autossh.config.quartz.QuartzConfig;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.autossh.dailyinspection.service.ServerConfigService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时脚本控制
 */
@RestController
@RequestMapping("/quartz")
@Configuration
public class QuartzController {

    @Autowired @Qualifier("Scheduler")
    public Scheduler scheduler;

    @Autowired
    public ServerConfigService serverConfigService;
    QuartzConfig quartzConfig = new QuartzConfig();


    @GetMapping("/deleteAll")
    public void deleteAllJob(String jobGroup) throws Exception
    {
        quartzConfig.deleteAllCommonJob(jobGroup, scheduler);
    }

    @GetMapping("/addAll")
    public void addAllJob() throws Exception
    {
        quartzConfig.addAllCronJob(scheduler, serverConfigService);
    }

//    @GetMapping("/delete")
//    public void deleteJob(String jobName, String jobGroup) throws Exception
//    {
//        QuartzConfig quartzConfig = new QuartzConfig();
//        quartzConfig.deleteCommonJob(jobName,jobGroup,scheduler);
//    }
//    @GetMapping("/refresh")
//
//    public void rescheduleJob(String jobName, String jobGroup, String cron) throws Exception
//    {
//        try {
//            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
//            // 表达式调度构建器
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
//
//            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//            // 按新的cronExpression表达式重新构建trigger
//            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//
//            // 按新的trigger重新设置job执行
//            scheduler.rescheduleJob(triggerKey, trigger);
//        } catch (SchedulerException e) {
//            System.out.println("更新定时任务失败"+e);
//            throw new Exception("更新定时任务失败");
//        }
//    }
//
//    /**
//     * 添加任务
//     * @param jobName
//     * @param jobGroup
//     * @param cron
//     * @param className
//     */
//    @GetMapping("/addJob")
//    public void addJob(String jobName, String jobGroup, String cron,String className){
//        try {
//            QuartzConfig quartzConfig = new QuartzConfig();
//            quartzConfig.addCommonCronJob(jobName,jobGroup,cron,scheduler,className);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
