package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author ZS
 */
public interface ServerConfigService {
	/**
	 * 新增服务器
	 */
	JSONObject addServer(JSONObject jsonObject);

	/**
	 * 服务器列表
	 */
	JSONObject listServer(JSONObject jsonObject);

	/**
	 * 更新服务器
	 */
	JSONObject updateServer(JSONObject jsonObject);

    /**
     * 删除服务器
     */
    JSONObject deleteServer(JSONObject jsonObject);

	/**
	 * 提供给quartz，查询所有任务
	 */

	List<JSONObject> getAllJobInfo();

	/**
	 * 提供给数据对比
	 */

	List<JSONObject> equalsServer(String id);

}
