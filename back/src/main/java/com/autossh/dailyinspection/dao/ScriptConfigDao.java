package com.autossh.dailyinspection.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author han-shy
 */
@Component
public interface ScriptConfigDao {
	/**
	 * 新增命令
	 */
	int addScript(JSONObject jsonObject);

	/**
	 * 统计命令总数
	 */
	int countScript(JSONObject jsonObject);

	/**
	 * 命令列表
	 */
	List<JSONObject> listScript(JSONObject jsonObject);

	/**
	 * 更新命令
	 */
	int updateScript(JSONObject jsonObject);

    /**
     * 删除命令
     */
	int deleteScirpt(JSONObject jsonObject);

	/**
	 * 查询所有服务器的类型
	 * 在选择服务器类型的时候要使用此方法
	 */
	List<JSONObject> getAllServerType();

	/**
	 * 查询所有服务器的适用版本
	 * 在选择服务器适用版本的时候要使用此方法
	 */
	List<JSONObject> getAllSystemType();
}
