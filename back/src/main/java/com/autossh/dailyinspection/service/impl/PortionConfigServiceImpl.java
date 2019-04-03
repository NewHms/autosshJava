package com.autossh.dailyinspection.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.dao.PortionConfigDao;
import com.autossh.dailyinspection.service.PortionConfigService;
import com.autossh.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("portionConfigService")
public class PortionConfigServiceImpl implements PortionConfigService {

    @Autowired
    private PortionConfigDao dao;

    /**
     * 新增命令
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addPortion(JSONObject jsonObject) {
        dao.addPortion(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 命令列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listPortion(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = dao.countPortion(jsonObject);
        List<JSONObject> list = dao.listPortion(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 查询一条需要回显在add窗口
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listPortionOne(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = dao.countPortion(jsonObject);
        List<JSONObject> list = dao.listPortionOne(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 更新命令
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updatePortion(JSONObject jsonObject) {
        String strSysVersion = jsonObject.get("sysVersion").toString().replace("[","").replace("]","");
        jsonObject.replace("sysVersion",strSysVersion);
        dao.updatePortion(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 删除命令
     * @param jsonObject
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject deletePortion(JSONObject jsonObject) {
        dao.deletePortion(jsonObject);
        return CommonUtil.successJson();
    }
}
