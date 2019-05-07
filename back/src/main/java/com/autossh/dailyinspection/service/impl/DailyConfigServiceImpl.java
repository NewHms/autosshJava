package com.autossh.dailyinspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.dao.DailyConfigDao;
import com.autossh.dailyinspection.service.DailyConfigService;
import com.autossh.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dailyConfigService")
public class DailyConfigServiceImpl implements DailyConfigService {


    @Autowired
    private DailyConfigDao dao;

    /**
     * 新增配置
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addConfig(JSONObject jsonObject) {
        dao.addConfig(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 配置列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listConfig(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = dao.countConfig(jsonObject);
        List<JSONObject> list = dao.listConfig(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 获取一条未进行配置的监控项
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listConfigOne(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = dao.countConfig(jsonObject);
        List<JSONObject> list = dao.listConfigOne(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 更新配置
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateConfig(JSONObject jsonObject) {
        dao.updateConfig(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 删除配置
     * @param jsonObject
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject deleteConfig(JSONObject jsonObject) {
        dao.deleteConfig(jsonObject);
        return CommonUtil.successJson();
    }
}
