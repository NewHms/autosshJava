package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;


/**
 * @author ZS
 */
public interface DailyLogService {

	/**
	 * 服务器列表
	 */
	JSONObject listLog(JSONObject jsonObject);

    /**
     * 删除服务器
     */
    JSONObject deleteLog(JSONObject jsonObject);

}
