/**
 * 
 */
package com.oauth.api.gateway.config;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manoj
 *
 */
@Component
@Slf4j
public class ErrorFilter extends ZuulFilter {
	
	protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";
	
	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return -1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.error("run() : using route filter");
		RequestContext ctx = RequestContext.getCurrentContext();
	    ctx.set(SEND_ERROR_FILTER_RAN);
	    // rest of your code
	    return null;
	}


}
