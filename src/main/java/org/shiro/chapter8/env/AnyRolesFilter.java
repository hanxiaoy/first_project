package org.shiro.chapter8.env;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnyRolesFilter extends AccessControlFilter {

	private Logger log = LoggerFactory.getLogger(AnyRolesFilter.class);
	
	private String unAuthorizedUrl = "/unAuthroizedUrl.jsp"; 	
	
	private String loginUrl = "/login.jsp";
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		String[] roles = (String[])mappedValue;
		if(roles == null){
			return true;//如果没有设置角色参数，默认成功
		}
		
		for(String role : roles){
			if(getSubject(request, response).hasRole(role)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if(getSubject(request , response) == null){
			saveRequest(request);
			WebUtils.issueRedirect(request, response, loginUrl);
		}else{
			if(StringUtils.hasText(unAuthorizedUrl)){
				WebUtils.issueRedirect(request, response, unAuthorizedUrl);
			}else { 
				log.info("======> 401 ");
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		
		return false;
	}

}
