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

}
