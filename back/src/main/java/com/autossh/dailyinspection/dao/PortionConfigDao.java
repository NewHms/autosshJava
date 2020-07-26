package com.autossh.dailyinspection.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zs
 */
@Component
public interface PortionConfigDao {
	/**
	 * 新增配置
	 */
	int addPortion(JSONObject jsonObject);

	/**
	 * 统计配置总数
	 */
	int countPortion(JSONObject jsonObject);

	/**
	 * 命令配置
	 */
	List<JSONObject> listPortion(JSONObject jsonObject);

	/**
	 * 查询一条需要回显在index add窗口
	 */
	List<JSONObject> listPortionOne(JSONObject jsonObject);

	/**
	 * 更新配置
	 */
	int updatePortion(JSONObject jsonObject);

    /**
     * 删除配置
     */
	int deletePortion(JSONObject jsonObject);
}
