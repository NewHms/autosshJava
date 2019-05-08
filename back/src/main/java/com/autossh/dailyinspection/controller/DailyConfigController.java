package com.autossh.dailyinspection.controller;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.service.DailyConfigService;
import com.autossh.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 日检服务器配置controller层
 */
@RestController
@RequestMapping("/dailyConfig")
public class DailyConfigController {

    @Autowired
    private DailyConfigService service;

    /**
     * 查看配置列表
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listConfig")
    public JSONObject listConfig(HttpServletRequest request){
        return service.listConfig(CommonUtil.request2Json(request));
    }

    /**
     * 查看配置列表
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listDistConfig")
    public JSONObject listDistConfig(HttpServletRequest request){
        return service.listDistConfig(CommonUtil.request2Json(request));
    }

    /**
     * 获取一条未进行配置的监控项
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listConfigOne")
    public JSONObject listConfigOne(HttpServletRequest request){
        return service.listConfigOne(CommonUtil.request2Json(request));
    }

    /**
     * 添加配置
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:add")
    @PostMapping("/addConfig")
    public JSONObject addConfig(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson,"code, dailyDesc, waring, critical");
        return service.addConfig(requestJson);
    }

    /**
     * 修改配置
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:update")
    @PostMapping("/updateCodeConfig")
    public JSONObject updateCodeConfig(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "waring, critical, dailyRule");
        return service.updateCodeConfig(requestJson);
    }

    /**
     * 修改配置
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:update")
    @PostMapping("/updateIdConfig")
    public JSONObject updateIdConfig(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "waringPriv, criticalPriv");
        return service.updateIdConfig(requestJson);
    }

    /**
     * 删除配置
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:delete")
    @PostMapping("/deleteConfig")
    public JSONObject deleteConfig(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "id");
        return service.deleteConfig(requestJson);
    }
}
