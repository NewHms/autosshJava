package com.autossh.dailyinspection.controller;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.service.ScriptConfigService;
import com.autossh.util.CommonUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 日检命令配置controller层
 */
@RestController
@RequestMapping("/scriptConfig")
public class ScriptConfigController {

    @Autowired
    private ScriptConfigService service;

    /**
     * 查看命令列表
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listScriptConfig")
    public JSONObject listScriptConfig(HttpServletRequest request){
        return service.listScript(CommonUtil.request2Json(request));
    }

    /**
     * 添加命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:add")
    @PostMapping("/addScript")
    public JSONObject addScript(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson,"shellName");
        return service.addScript(requestJson);
    }

    /**
     * 修改命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:update")
    @PostMapping("/updateScript")
    public JSONObject updateScript(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "shellName");
        return service.updateScript(requestJson);
    }

    /**
     * 删除命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:delete")
    @PostMapping("/deleteScript")
    public JSONObject deleteScript(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "id");
        return service.deleteScirpt(requestJson);
    }

    @RequiresPermissions(value = {"scriptConfig:add", "scriptConfig:update"}, logical = Logical.OR)
    @GetMapping("/getAllServerType")
    public JSONObject getAllServerType() {
        return service.getAllServerType();
    }
}
