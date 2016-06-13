package org.shiro.chapter8.env;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyOncePerRequestFilter extends OncePerRequestFilter {

	private Logger log = LoggerFactory.getLogger(MyOncePerRequestFilter.class);
	
	@Override
	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.info("MyIniWebEnvironment.doFilterInternal()");
		chain.doFilter(request, response);
	}

}
