package com.autossh.dailyinspection.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author han-shy
 */
@Component
public interface ServerConfigDao {
	/**
	 * 新增服务器
	 */
	int addServer(JSONObject jsonObject);

	/**
	 * 统计服务器总数
	 */
	int countServer(JSONObject jsonObject);

	/**
	 * 服务器列表
	 */
	List<JSONObject> listServer(JSONObject jsonObject);

	/**
	 * 更新服务器
	 */
	int updateServer(JSONObject jsonObject);

    /**
     * 删除服务器
     */
	int deleteServer(JSONObject jsonObject);

	/**
	 * 查询某角色的全部数据
	 * 在删除和修改角色时调用
	 * List<JSONObject> getAllServerType();
	 */

	/**
	 * 提供给quartz，查询所有任务
	 */
	List<JSONObject> getAllJobInfo();


	/**
	 * 提供给数据对比
	 */
	List<JSONObject> equalsServer(String id);

	/**
	 * 提供给页面，查询任务
	 * @param name
	 * @return
	 */
	JSONObject getJobInfo(String name);
}
