package org.shiro;

import java.security.MessageDigest;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tutorial {
	 private static final transient Logger log = LoggerFactory.getLogger(Tutorial.class);

	    public static void main(String[] args) throws Exception{
	        log.info("My First Apache Shiro Application");
	        
	        //1.
//	        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
	        Factory<SecurityManager> factory2 = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

	        //2.
	        SecurityManager securityManager = factory2.getInstance();
//	        SecurityManager securityManager = factory.getInstance();

	        //3.
	        SecurityUtils.setSecurityManager(securityManager);

	        Subject subject = SecurityUtils.getSubject();
	        
	        Session session = subject.getSession();
	        session.setAttribute("user", "userValue");
	        Date date = session.getStartTimestamp();
	        Date date2 = session.getLastAccessTime();
	        	
	        
	        if(!subject.isAuthenticated()){
	        	UsernamePasswordToken token = new UsernamePasswordToken("root","vespa");
	        	token.setRememberMe(true);
	        	subject.login(token);
	        }
	        
	        System.out.println("user["+subject.getPrincipal()+"]");
	        
	        
	        if(subject.hasRole("guest")){
	        	System.out.println("May the Schwartz be with you!");
	        }
	        else System.out.println( "Hello, mere mortal." );
	        
	        
	        if (subject.isPermitted("lightsaber:*"))  System.out.println("You may use a lightsaber ring.  Use it wisely.");
	        else   System.out.println("Sorry, lightsaber rings are for schwartz masters only.");
	        
	        
	        if ( subject.isPermitted( "winnebago:drive:eagle5" ) ) {
	        	 System.out.println("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
	                        "Here are the keys - have fun!");
	        } else {
	        	 System.out.println("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
	        }
	        
	        byte[]  bytes = new String("1").getBytes();
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        bytes = md.digest(bytes);
	        System.out.println(bytes);
	        System.out.println(new String(bytes));
	     
	        
//	        String encodedPassword =  new Sha512Hash("abc123", salt, count).toBase64();
	        
	        
	        subject.logout();
	        
	        System.exit(0);
	    }
}
