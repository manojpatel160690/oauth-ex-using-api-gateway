/**
 * 
 */
package com.oauth.api.gateway.config;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manoj
 *
 */
@Component
@Slf4j
public class ErrorFilter extends ZuulFilter {
	
	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.error("run() : using route filter");
		return null;
	}


}
