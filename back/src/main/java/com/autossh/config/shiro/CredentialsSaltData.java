package com.autossh.config.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密密码
 */
public class CredentialsSaltData {
    public static void main(String[] args) {
        //正常md5加密
        // String pwd = new SimpleHash("MD5", "123456").toHex();
        //加盐，以用户名为盐
        String pwd =  new SimpleHash("MD5", "123456", ByteSource.Util.bytes("admin")).toHex();
        //BCrypt 方式加密
        //String pwd = BCrypt.hashpw("123456", BCrypt.gensalt());

        System.out.println(pwd);
    }

}

