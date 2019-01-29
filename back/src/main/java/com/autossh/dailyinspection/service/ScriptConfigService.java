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
}
