package com.autossh.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class test {

    //我么要执行创建表的DDl语句
    static String creatsql = "select IP from dc_distribute_setting limit 1";

    final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //指定连接数据库的url
    final static String DB_URL = "jdbc:mysql://47.104.192.159:3306/spring-boot-vue";
    //mysql用户名
    final static String name = "root";
    //mysql密码
    final static String pwd = "1";
    String ip = "";
    Connection conn = null;
    Statement stmt = null;
    {
        //注册jdbc驱动
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, name, pwd);
            System.out.println("//连接数据库");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(creatsql);
            while(rs.next()){
                ip = rs.getString("IP");

                //输出结果
                //System.out.println(ip);
            }
            stmt.close();
            conn.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //System.out.println("//关闭资源");
    }
    @Scheduled(cron = "0/10 * * * * *")
    public void scheduled(){
        System.out.println(ip);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        System.out.println("=====>>>>>当前时间"+ df.format(new Date()));
    }
//    @Scheduled(fixedRate = 5000)
//    public void scheduled1() {
//        System.out.println("=====>>>>>使用fixedRate{}"+System.currentTimeMillis());
//    }
//    @Scheduled(fixedDelay = 5000)
//    public void scheduled2() {
//        System.out.println("=====>>>>>fixedDelay{}"+System.currentTimeMillis());
//    }
}