package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;

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
	 * 查询所有服务器的类型
	 * 在选择服务器类型的时候要使用此方法
	 * JSONObject getAllServerType();
	 */

}
