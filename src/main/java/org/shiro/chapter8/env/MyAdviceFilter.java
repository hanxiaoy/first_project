package org.shiro.chapter8.env;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAdviceFilter extends AdviceFilter {

	private Logger log = LoggerFactory.getLogger(MyAdviceFilter.class);
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		log.info("MyAdviceFilter.preHandle()");
		return true;
	}
	
	@Override
	 protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		log.info("MyAdviceFilter.postHandle()");
	}
	
	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
		log.info("MyAdviceFilter.afterCompletion()");
	}
}
