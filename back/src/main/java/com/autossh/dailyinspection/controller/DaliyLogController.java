package com.autossh.dailyinspection.controller;

import com.alibaba.fastjson.JSONObject;
import com.autossh.dailyinspection.service.DailyLogService;
import com.autossh.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 日检命令配置controller层
 */
@RestController
@RequestMapping("/logConfig")
public class DaliyLogController {

    @Autowired
    private DailyLogService service;

    /**
     * 查看命令列表
     * @param request
     * @return
     */
    @RequiresPermissions("scriptConfig:list")
    @GetMapping("/listLog")
    public JSONObject listLog(HttpServletRequest request){
        return service.listLog(CommonUtil.request2Json(request));
    }

    /**
     * 删除命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:delete")
    @PostMapping("/deleteLog")
    public JSONObject deleteLog(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "id");
        return service.deleteLog(requestJson);
    }

}
