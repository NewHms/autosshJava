package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author ZS
 */
public interface ServerConfigService {
	/**
	 * 新增命令
	 */
	JSONObject addServer(JSONObject jsonObject);

	/**
	 * 命令列表
	 */
	JSONObject listServer(JSONObject jsonObject);

	/**
	 * 更新命令
	 */
	JSONObject updateServer(JSONObject jsonObject);

    /**
     * 删除命令
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
