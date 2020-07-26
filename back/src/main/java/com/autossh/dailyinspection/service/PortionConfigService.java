package com.autossh.dailyinspection.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ZS
 */
public interface PortionConfigService {
	/**
	 * 新增配置
	 */
	JSONObject addPortion(JSONObject jsonObject);

	/**
	 * 配置列表
	 */
	JSONObject listPortion(JSONObject jsonObject);

	/**
	 *  查询一条需要回显在index add窗口
	 */
	JSONObject listPortionOne(JSONObject jsonObject);

	/**
	 * 更新配置
	 */
	JSONObject updatePortion(JSONObject jsonObject);

    /**
     * 删除配置
     */
    JSONObject deletePortion(JSONObject jsonObject);
}
