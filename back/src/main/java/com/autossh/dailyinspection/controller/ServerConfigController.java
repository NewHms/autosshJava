package com.autossh.dailyinspection.controller;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.service.ServerConfigService;
import com.autossh.util.CommonUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 日检服务器配置controller层
 */
@RestController
@RequestMapping("/serverConfig")
public class ServerConfigController {

    @Autowired
    private ServerConfigService service;

    /**
     * 查看命令列表
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listServerConfig")
    public JSONObject listServerConfig(HttpServletRequest request){
        return service.listServer(CommonUtil.request2Json(request));
    }

    /**
     * 添加命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:add")
    @PostMapping("/addServer")
    public JSONObject addServer(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson,"shellName");
        return service.addServer(requestJson);
    }

    /**
     * 修改命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:update")
    @PostMapping("/updateServer")
    public JSONObject updateServer(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "password,dbPassword,crontab,host,applicationServer");
        return service.updateServer(requestJson);
    }

    /**
     * 删除命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:delete")
    @PostMapping("/deleteServer")
    public JSONObject deleteServer(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "id");
        return service.deleteServer(requestJson);
    }
    /*
    @RequiresPermissions(value = {"scriptConfig:add", "scriptConfig:update"}, logical = Logical.OR)
    @GetMapping("/getAllServerType")
    public JSONObject getAllServerType() {
        return service.getAllServerType();
    }
    */
}
