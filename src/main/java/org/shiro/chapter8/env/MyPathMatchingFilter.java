package org.shiro.chapter8.env;

import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPathMatchingFilter extends PathMatchingFilter {

	private Logger log = LoggerFactory.getLogger(MyPathMatchingFilter.class);

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		log.info("url matches,config is " + Arrays.toString((String[])mappedValue));
//		return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
		
		return true;
	}
	
	
}
