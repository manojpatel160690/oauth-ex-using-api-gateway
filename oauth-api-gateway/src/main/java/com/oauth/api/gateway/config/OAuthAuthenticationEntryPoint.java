/**
 * 
 */
package com.oauth.api.gateway.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manoj
 *
 */
@Slf4j
public class OAuthAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.info("path : {}", request.getServletPath());
		log.error("commence() : invalid authentication", authException);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	
}
