package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ZS
 */
public interface DailyConfigService {
	/**
	 * 新增配置
	 */
	JSONObject addConfig(JSONObject jsonObject);

	/**
	 * 配置列表
	 */
	JSONObject listConfig(JSONObject jsonObject);

	/**
	 * 获取一条未进行配置的监控项
	 */
	JSONObject listConfigOne(JSONObject jsonObject);

	/**
	 * 更新配置
	 */
	JSONObject updateConfig(JSONObject jsonObject);

    /**
     * 删除配置
     */
    JSONObject deleteConfig(JSONObject jsonObject);
}
