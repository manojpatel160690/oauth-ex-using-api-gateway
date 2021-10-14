/**
 * 
 */
package com.oauth.user.ms.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manoj
 *
 */
@Slf4j
public class CorsFilter extends GenericFilterBean implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("doFilter() - configuring CORS filter for the application");
		HttpServletResponse res = (HttpServletResponse) response;

		HttpServletRequest req = (HttpServletRequest)request;

		String originUrl = req.getHeader("Origin");

		log.info("doFilter() - request origin from : {}" , originUrl);

		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, originUrl);
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, OPTIONS, DELETE, PUT");
		res.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
		res.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Access-Control-Expose-Headers,access-control-allow-headers,access-control-allow-methods,Access-Control-Allow-Origin,authorization,content-type,Origin,X-Requested-With,Accept,Access-Control-Request-Headers");

		if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
		} else {		
			chain.doFilter(request, response);
		}

		log.info("doFilter() - filter CORS configured for the application");
	}

}
