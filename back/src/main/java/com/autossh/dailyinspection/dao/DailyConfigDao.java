package com.autossh.dailyinspection.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zs
 */
@Component
public interface DailyConfigDao {
	/**
	 * 新增配置
	 */
	int addConfig(JSONObject jsonObject);

	/**
	 * 统计配置总数
	 */
	int countConfig(JSONObject jsonObject);

	/**
	 * 统计配置总数
	 */
	int countDistConfig(JSONObject jsonObject);

	/**
	 * 命令配置
	 */
	List<JSONObject> listConfig(JSONObject jsonObject);

	/**
	 * 命令配置
	 */
	List<JSONObject> listDistConfig(JSONObject jsonObject);

	/**
	 * 获取一条未进行配置的监控项
	 */
	List<JSONObject> listConfigOne(JSONObject jsonObject);

	/**
	 * 更新配置
	 */
	int updateIdConfig(JSONObject jsonObject);

	/**
	 * 更新配置
	 */
	int updateCodeConfig(JSONObject jsonObject);

    /**
     * 删除配置
     */
	int deleteConfig(JSONObject jsonObject);
}
