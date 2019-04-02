package com.autossh.quartz.controller;

import com.autossh.quartz.service.QuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时脚本控制
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @Autowired @Qualifier("Scheduler")
    private Scheduler scheduler;


    /**
     * 更新单个定时任务
     * @param jobName
     * @param jobGroup
     * @param cron
     * @param
     */
    @GetMapping("/refresh")
    /*public void refresh(String jobName, String jobGroup, String triggerName, String triggerGroup,String cron){
        try {
           // QuartzManager quartzManager = new QuartzManager();
            QuartzManager.modifyJobTime(jobName,jobGroup,triggerName,triggerGroup,cron);
           // quartzService.refresh(jobName,jobGroup,cron,className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public void rescheduleJob(String jobName, String jobGroup, String cron) throws Exception
    {
        jobreschedule(jobName, jobGroup, cron);
    }

    public void jobreschedule(String jobName, String jobGroup, String cron) throws Exception
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
