package com.autossh.quartz.job;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class dailyLogToExcel {

    public String logToExecl(String execTime){
        /*定义个获取结果的变量*/

        String result = "";
        try {
            String[] strs = new String[]{"python", "D:\\workSpace\\python\\dailyLog_execl.py", execTime};
            Process pr = Runtime.getRuntime().exec(strs);

            /*使用缓冲流接受程序返回的结果*/
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));//注意格式

            /*定义一个接受python程序处理的返回结果*/
            String line = " ";

            while ((line = in.readLine()) != null) {
                //循环打印出运行的结果
                result += line + "\n";
            }

            //关闭in资源
            in.close();
            pr.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "python传来的结果：" + result;
    }
}
