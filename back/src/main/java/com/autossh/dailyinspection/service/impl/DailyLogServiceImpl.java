package com.autossh.dailyinspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.dao.DailyLogDao;
import com.autossh.dailyinspection.service.DailyLogService;
import com.autossh.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dailyLogService")
public class DailyLogServiceImpl implements DailyLogService {

    @Autowired
    private DailyLogDao dao;

    /**
     * 服务器列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listLog(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = dao.countLog(jsonObject);
        List<JSONObject> list = dao.listLog(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 删除服务器
     * @param jsonObject
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject deleteLog(JSONObject jsonObject) {
        dao.deleteLog(jsonObject);
        return CommonUtil.successJson();
    }

}
