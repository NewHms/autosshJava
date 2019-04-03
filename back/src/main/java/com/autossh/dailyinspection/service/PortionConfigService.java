package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ZS
 */
public interface PortionConfigService {
	/**
	 * 新增命令
	 */
	JSONObject addPortion(JSONObject jsonObject);

	/**
	 * 命令列表
	 */
	JSONObject listPortion(JSONObject jsonObject);

	/**
	 *  查询一条需要回显在add窗口
	 */
	JSONObject listPortionOne(JSONObject jsonObject);

	/**
	 * 更新命令
	 */
	JSONObject updatePortion(JSONObject jsonObject);

    /**
     * 删除命令
     */
    JSONObject deletePortion(JSONObject jsonObject);
}
