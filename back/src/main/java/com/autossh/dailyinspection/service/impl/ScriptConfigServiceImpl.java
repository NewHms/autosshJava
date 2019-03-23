package com.autossh.dailyinspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.dao.ScriptConfigDao;
import com.autossh.dailyinspection.service.ScriptConfigService;
import com.autossh.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("scriptConfigService")
public class ScriptConfigServiceImpl implements ScriptConfigService {

    @Autowired
    private ScriptConfigDao dao;

    /**
     * 新增命令
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addScript(JSONObject jsonObject) {
        dao.addScript(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 命令列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listScript(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = dao.countScript(jsonObject);
        List<JSONObject> list = dao.listScript(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateScript(JSONObject jsonObject) {
        dao.updateScript(jsonObject);
        return CommonUtil.successJson();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject deleteScirpt(JSONObject jsonObject) {
        dao.deleteScirpt(jsonObject);
        return CommonUtil.successJson();
    }


    /**
     * 查询所有服务器的类型
     * 在选择服务器类型的时候要使用此方法
     */
    @Override
    public JSONObject getAllServerType() {
        List<JSONObject> servertype = dao.getAllServerType();
        return CommonUtil.successPage(servertype);
    }
}
