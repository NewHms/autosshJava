package com.autossh.dailyinspection.controller;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.service.PortionConfigService;
import com.autossh.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 日检服务器配置controller层
 */
@RestController
@RequestMapping("/portionConfig")
public class PortionConfigController {

    @Autowired
    private PortionConfigService service;

    /**
     * 查看配置列表
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listPortionConfig")
    public JSONObject listPortionConfig(HttpServletRequest request){
        return service.listPortion(CommonUtil.request2Json(request));
    }

    /**
     * 查询一条需要回显在index add窗口
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listPortionOneConfig")
    public JSONObject listPortionOneConfig(HttpServletRequest request){
        return service.listPortionOne(CommonUtil.request2Json(request));
    }

    /**
     * 添加配置
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:add")
    @PostMapping("/addPortion")
    public JSONObject addPortion(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson,"IP,sysVersion,execTime");
        return service.addPortion(requestJson);
    }

    /**
     * 修改配置
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:update")
    @PostMapping("/updatePortion")
    public JSONObject updatePortion(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "IP,sysVersion,execTime");
        return service.updatePortion(requestJson);
    }

    /**
     * 删除配置
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:delete")
    @PostMapping("/deletePortion")
    public JSONObject deletePortion(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "id");
        return service.deletePortion(requestJson);
    }
}
