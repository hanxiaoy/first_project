package org.shiro.chapter6.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.shiro.chapter6.entity.User;
import org.shiro.chapter6.service.UserService;
import org.shiro.chapter6.service.impl.UserServiceImpl;

public class UserRealm extends AuthorizingRealm {

	UserService userService = new UserServiceImpl();

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username =  (String) principals.getPrimaryPrincipal();;
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		
		info.setRoles(userService.findRoles(username));
		
//		info.setStringPermissions(userService.findPermission(username));
		
		return info;
		
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		
//		User user = userService.findUserByUsername(username);
//				
//		if(user == null){
//			throw new UnknownAccountException("用户名错误");
//		}
//		if(Boolean.TRUE.equals(user.getLocked())){
//			throw new LockedAccountException("用户被锁定");
//		}
//		
//		SimpleAuthenticationInfo auth= new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),getName());
		return null;
	}
	
}
