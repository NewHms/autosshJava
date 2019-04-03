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
	 * 新增命令
	 */
	int addServer(JSONObject jsonObject);

	/**
	 * 统计命令总数
	 */
	int countServer(JSONObject jsonObject);

	/**
	 * 命令列表
	 */
	List<JSONObject> listServer(JSONObject jsonObject);

	/**
	 * 更新命令
	 */
	int updateServer(JSONObject jsonObject);

    /**
     * 删除命令
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
