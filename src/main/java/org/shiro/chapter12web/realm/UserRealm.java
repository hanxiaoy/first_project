package org.shiro.chapter12web.realm;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.shiro.chapter12web.credentials.MySimpleByteSource;
import org.shiro.chapter6.entity.User;
import org.shiro.chapter6.service.UserService;

public class UserRealm extends AuthorizingRealm implements Serializable{ 

    private UserService userService;
    
    private String error ="";
    private String shiroLoginFailure ="";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals ) {
    	System.out.println("===========doGetAuthorizationInfo()");
        String username = (String)principals.getPrimaryPrincipal();

        MySimpleByteSource authorizationInfo = new MySimpleByteSource();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	System.out.println("=========================================doGetAuthenticationInfo()");
        String username = (String)token.getPrincipal();

        User user = userService.findByUsername(username);

        if(user == null) {
        	error ="没找到帐号";
        	shiroLoginFailure = "没找到帐号_shiroLoginFailure";
            throw new UnknownAccountException("没找到帐号");//没找到帐号
        }

        if(Boolean.TRUE.equals(user.getLocked())) {
        	error ="帐号锁定";
        	shiroLoginFailure = "帐号锁定_shiroLoginFailure";
            throw new LockedAccountException("帐号锁定"); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

	public UserService getUserService() {
		return userService;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getShiroLoginFailure() {
		return shiroLoginFailure;
	}

	public void setShiroLoginFailure(String shiroLoginFailure) {
		this.shiroLoginFailure = shiroLoginFailure;
	}


}
