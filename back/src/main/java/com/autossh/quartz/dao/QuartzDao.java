package com.autossh.quartz.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface QuartzDao {
    /**
     * 提供给quartz，查询所有任务
     */
    List<JSONObject> getAllJobInfo();

    /**
     * 提供给页面，查询任务
     * @param name
     * @return
     */
    JSONObject getJobInfo(String name);
}
