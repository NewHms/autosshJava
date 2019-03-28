package com.autossh.dailyinspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.dao.ServerConfigDao;
import com.autossh.dailyinspection.service.ServerConfigService;
import com.autossh.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("serverConfigService")
public class ServerConfigServiceImpl implements ServerConfigService {

    @Autowired
    private ServerConfigDao dao;

    /**
     * 新增命令
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addServer(JSONObject jsonObject) {
        dao.addServer(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 命令列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listServer(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = dao.countServer(jsonObject);
        List<JSONObject> list = dao.listServer(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateServer(JSONObject jsonObject) {
        dao.updateServer(jsonObject);
        return CommonUtil.successJson();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject deleteServer(JSONObject jsonObject) {
        dao.deleteServer(jsonObject);
        return CommonUtil.successJson();
    }


    /**
     * 查询所有服务器的类型
     * 在选择服务器类型的时候要使用此方法
    @Override
    public JSONObject getAllServerType() {
        List<JSONObject> servertype = dao.getAllServerType();
        return CommonUtil.successPage(servertype);
    }
     */
}