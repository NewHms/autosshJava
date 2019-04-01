package com.autossh.quartz.controller;

import com.autossh.config.quartz.QuartzManager;
import com.autossh.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * 更新单个定时任务
     * @param jobName
     * @param jobGroup
     * @param cron
     * @param
     */
    @GetMapping("/refresh")
    public void refresh(String jobName, String jobGroup, String triggerName, String triggerGroup,String cron){
        try {
           // QuartzManager quartzManager = new QuartzManager();
            QuartzManager.modifyJobTime(jobName,jobGroup,triggerName,triggerGroup,cron);
           // quartzService.refresh(jobName,jobGroup,cron,className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
