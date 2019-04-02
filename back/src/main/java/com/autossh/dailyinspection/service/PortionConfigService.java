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
	 * 更新命令
	 */
	JSONObject updatePortion(JSONObject jsonObject);

    /**
     * 删除命令
     */
    JSONObject deletePortion(JSONObject jsonObject);
}
