package com.autossh.quartz.job;

import com.autossh.dailyinspection.controller.ServerConfigController;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MyTest2{
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Scheduled(cron = "*/10 * * * * *")
    public void test(){
        ServerConfigController serverConfigController = new ServerConfigController();
        serverConfigController.killRunJob(scheduler);

    }
}
