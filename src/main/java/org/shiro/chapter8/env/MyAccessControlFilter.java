package org.shiro.chapter8.env;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccessControlFilter extends AccessControlFilter {

	private Logger log = LoggerFactory.getLogger(MyAccessControlFilter.class);
	
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		log.debug("MyAccessControlFilter.isAccessAllowed()==>assess allowed");
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		log.info("MyAccessControlFilter.isAccessAllowed()===>访问拒绝也不处理，继续连接器链的执行 ");
		return true;
	}

}
