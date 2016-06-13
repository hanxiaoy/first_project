package org.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Authentioncation {

	
	private void login(String file){
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(file);
		
		org.apache.shiro.mgt.SecurityManager manager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(manager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		subject.login(token);
		
	}
	
	public void testAllSuccessfulStategyWithSuccess(){
		Subject subject = SecurityUtils.getSubject();
		
	}
}
