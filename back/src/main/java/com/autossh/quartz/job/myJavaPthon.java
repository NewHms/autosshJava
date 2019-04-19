package com.autossh.quartz.job;

import org.quartz.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class myJavaPthon implements InterruptableJob {
    private volatile boolean isJobInterrupted = false;
    private JobKey jobKey = null;
    private volatile Thread thisThread;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String dailyIp = jobExecutionContext.getJobDetail().getJobDataMap().get("jobHost").toString();
        String dailyType = jobExecutionContext.getJobDetail().getJobDataMap().get("jobType").toString();
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        //System.out.println(jobKey);
        thisThread = Thread.currentThread();
        System.out.println("Thread name of the current job: " + thisThread.getName());
        System.out.println("Job " + jobKey + " executing at " + new Date());
        /*定义个获取结果的变量*/
        String result = "";
        try {
            Thread.sleep(100*1000);
//
//            /*调用python，其中字符串数组对应的是python，python文件路径，向python传递的参数*/
//            String[] strs = new String[]{"python","D:\\workSpace\\python\\check_db_daily.py",dailyIp,dailyType};
//
//            /*Runtime类封装了运行时的环境。每个 Java 应用程序都有一个 Runtime 类实例,
//              使应用程序能够与其运行的环境相连接。
//              一般不能实例化一个Runtime对象，应用程序也不能创建自己的 Runtime 类实例，
//              但可以通过 getRuntime 方法获取当前Runtime运行时对象的引用。
//              exec(String[] cmdarray) 在单独的进程中执行指定命令和变量。*/
//            Process pr = Runtime.getRuntime().exec(strs);
//
//            /*使用缓冲流接受程序返回的结果*/
//            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));//注意格式
//
//            /*定义一个接受python程序处理的返回结果*/
//            String line = " ";
//
//            while ((line = in.readLine()) != null) {
//                //循环打印出运行的结果
//                result += line + "\n";
//            }
//            //关闭in资源
//            in.close();
//            pr.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 查看是否中断
            if (isJobInterrupted) {
                //LOG.info("Job " + jobKey + " did not complete");
                System.out.println("Job " + jobKey + " did not complete");
            } else {
                //LOG.info("Job " + jobKey + " completed at " + new Date());
                System.out.println("Job " + jobKey + " completed at " + new Date());
            }
        }
        System.out.println("python传来的结果：");
        //打印返回结果
        System.out.println(result);
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

        System.out.println("Job " + jobKey + "  -- INTERRUPTING --");
        isJobInterrupted = true;
        if (thisThread != null) {
            // this called cause the ClosedByInterruptException to happen
            System.out.println(thisThread.isInterrupted());
            thisThread.interrupt();
            System.out.println(thisThread.isInterrupted());
        }

    }
}
