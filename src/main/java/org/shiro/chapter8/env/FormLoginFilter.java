package org.shiro.chapter8.env;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormLoginFilter extends PathMatchingFilter {

	private Logger log = LoggerFactory.getLogger(FormLoginFilter.class);
	
	private  final String loginUrl = "/login.jsp";
	private final String successUrl = "/";

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		log.info("FormLoginFilter.onPreHandle()");
		
		if(SecurityUtils.getSubject().isAuthenticated()){
			return true;
		}
		
		HttpServletRequest requ = (HttpServletRequest) request;
		
		HttpServletResponse resp = (HttpServletResponse) response;
		
		log.info("==================="  + requ.getMethod());
		
		if(isLoginRequest(requ)){
			if("post".equalsIgnoreCase(requ.getMethod())){
				boolean isLogin = login(request);
				if(isLogin){
					return redirectToSuccess(requ,resp);
				}
			}
			return true;
		} else {
			saveRequestAndRedirectToLogin(requ, resp);
			return false;
		}
	}

	public boolean isLoginRequest(HttpServletRequest request){
		
		return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(request));
	}
	
	private boolean login(ServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.info("username == " + username + ",password == " +  password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try{
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
		}catch(Exception e){
			request.setAttribute("shiroLoginFailure", e.getClass());
			log.error("login",e);
			return false;
		}
		return true;
	}

	private void saveRequestAndRedirectToLogin(HttpServletRequest requ, HttpServletResponse resp) throws IOException {
		WebUtils.saveRequest(requ);
		WebUtils.issueRedirect(requ, resp, loginUrl);
	}

	private boolean redirectToSuccess(HttpServletRequest requ, HttpServletResponse resp) throws IOException {
		WebUtils.redirectToSavedRequest(requ, resp, successUrl);
		return false;
	}
	
	
}
