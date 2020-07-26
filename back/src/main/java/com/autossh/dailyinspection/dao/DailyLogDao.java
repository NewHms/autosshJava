package com.autossh.dailyinspection.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author han-shy
 */
@Component
public interface DailyLogDao {

	/**
	 * 统计服务器总数
	 */
	int countLog(JSONObject jsonObject);

	/**
	 * 服务器列表
	 */
	List<JSONObject> listLog(JSONObject jsonObject);

    /**
     * 删除服务器
     */
	int deleteLog(JSONObject jsonObject);

	/**
	 * 导出execl
	 */
	String dailyLogToExecl();

}
