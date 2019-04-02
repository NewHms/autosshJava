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
	 * 新增命令
	 */
	int addPortion(JSONObject jsonObject);

	/**
	 * 统计命令总数
	 */
	int countPortion(JSONObject jsonObject);

	/**
	 * 命令列表
	 */
	List<JSONObject> listPortion(JSONObject jsonObject);

	/**
	 * 更新命令
	 */
	int updatePortion(JSONObject jsonObject);

    /**
     * 删除命令
     */
	int deletePortion(JSONObject jsonObject);
}
