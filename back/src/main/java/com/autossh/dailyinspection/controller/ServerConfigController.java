package com.autossh.dailyinspection.controller;

import com.alibaba.fastjson.JSONObject;
import com.autossh.config.quartz.QuartzConfig;
import com.autossh.dailyinspection.service.ServerConfigService;
import com.autossh.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日检服务器配置controller层
 */
@RestController
@RequestMapping("/serverConfig")
public class ServerConfigController {

    @Autowired
    private ServerConfigService service;

    @Autowired @Qualifier("Scheduler")
    private Scheduler scheduler;
    QuartzConfig quartzConfig = new QuartzConfig();
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
        if (requestJson.getString("crontab").equals("1")) {
            try {
                quartzConfig.addCommonCronJob(requestJson.getString("subject"),"group",requestJson.getString("execTime"),scheduler,"com.autossh.quartz.job.MyTest1");
                CommonUtil.hasAllRequired(requestJson,"password,dbPassword,crontab,host,applicationServer");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            CommonUtil.hasAllRequired(requestJson,"password,dbPassword,host,applicationServer");
        }
        return service.addServer(requestJson);
    }

    /**
     * 修改命令
     * @param requestJson
     * @return
     */
    @RequiresPermissions("scriptConfig:update")
    @PostMapping("/updateServer")
    public JSONObject updateServer(@RequestBody JSONObject requestJson) throws Exception {
        String id = requestJson.getString("id");
        List<JSONObject> json = service.equalsServer(id);
        String oldCrontab  = json.get(0).getString("crontab");
        //String oldExecTime = json.get(0).getString("execTime");
        String newCrontab  = requestJson.getString("crontab");
        //String newExecTime = requestJson.getString("execTime");
        if (oldCrontab.equals("0") && newCrontab.equals("1")){
            try {
                quartzConfig.addCommonCronJob(requestJson.getString("subject"),"group",requestJson.getString("execTime"),scheduler,"com.autossh.quartz.job.MyTest1");
                CommonUtil.hasAllRequired(requestJson, "password,dbPassword,crontab,host,applicationServer");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (oldCrontab.equals("1") && newCrontab.equals("1")){
            try {
                quartzConfig.rescheduleJob(requestJson.getString("subject"), "group", requestJson.getString("execTime"), scheduler);
                CommonUtil.hasAllRequired(requestJson, "password,dbPassword,crontab,host,applicationServer");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(oldCrontab.equals("1") && newCrontab.equals("0")){
            try {
                quartzConfig.deleteCommonJob(requestJson.getString("subject"), "group", scheduler);
                CommonUtil.hasAllRequired(requestJson, "password,dbPassword,crontab,host,applicationServer");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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
        try {
            quartzConfig.deleteCommonJob(requestJson.getString("subject"), "group", scheduler);
        }catch (Exception e){
            e.printStackTrace();
        }
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
