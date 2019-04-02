package com.autossh.quartz.service;

import com.alibaba.fastjson.JSONObject;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.util.List;

public interface QuartzService {
    /**
     * 提供给quartz，查询所有任务
     */
    List<JSONObject> getJobInfo();

    //获取JobDetail,JobDetail是任务的定义
    public JobDetail geJobDetail(JobKey jobKey,JobDataMap map,String className) throws Exception;

    public Trigger getTrigger(String jobName,String jobGroup,String cron) throws Exception;

    void refresh(String jobName,String jobGroup,String cron,String className) throws Exception;
}
