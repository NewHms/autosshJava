package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author han-shy
 */
public interface ScriptConfigService {
	/**
	 * 新增命令
	 */
	JSONObject addScript(JSONObject jsonObject);

	/**
	 * 命令列表
	 */
	JSONObject listScript(JSONObject jsonObject);

	/**
	 * 更新命令
	 */
	JSONObject updateScript(JSONObject jsonObject);

    /**
     * 删除命令
     */
    JSONObject deleteScirpt(JSONObject jsonObject);

	/**
	 * 查询所有服务器的类型
	 * 在选择服务器类型的时候要使用此方法
	 */
	JSONObject getAllServerType();

	/**
	 * 查询所有服务器的适用版本
	 * 在选择服务器适用版本的时候要使用此方法
	 */
	JSONObject getAllSystemType();
}
