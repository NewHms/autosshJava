package com.autossh.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.autossh.login.service.LoginService;
import com.autossh.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author: hxy
 * @description: 自定义Realm
 * @date: 2017/10/24 10:06
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private LoginService loginService;

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		//查询用户的权限
		JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
		logger.info("permission的值为:" + permission);
		logger.info("本用户权限为:" + permission.get("permissionList"));
		//为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * LoginController.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		String loginName = (String) authcToken.getPrincipal();
		// 获取用户密码
		//String password = new String((char[]) authcToken.getCredentials());
		// 查询得到密码
		JSONObject user = loginService.getUser(loginName);

		if (user != null) {
            /**
             * 四个参数
             * principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
             * credentials：数据库中的密码（经过加密的密码）
             * credentialsSalt：盐值（使用用户名） 若不使用加盐加密，则注释掉
             * realmName：当前realm对象的name，调用父类的getName()方法即可
             */
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    loginName,
                    user.get("password"),
                    ByteSource.Util.bytes(loginName),
                    getName());
           // authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.get("random")));
            user.remove("password");
            //将用户信息放入session中
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
            return authenticationInfo;

		}else {
            //没找到帐号
            throw new UnknownAccountException();
		    //return null;
        }

		/*//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getString("username"),
				user.getString("password"),
				ByteSource.Util.bytes("salt"), //salt=username+salt,采用明文访问时，不需要此句
				getName()
		);*/
        // RetryLimitHashedCredentialsMatcher(authcToken,);
        //		//session中不需要保存密码


	}
}
