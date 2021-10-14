/**
 * 
 */
package com.oauth.api.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author manoj
 *
 */
@Configuration
@EnableResourceServer
public class GatewaySecurityConfig extends ResourceServerConfigurerAdapter { // { WebSecurityConfigurerAdapter

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		AuthenticationEntryPoint authenticationEntryPoint = new OAuthAuthenticationEntryPoint();
		return authenticationEntryPoint;
	}
	
	@Override
    public void configure(final HttpSecurity http) throws Exception {
		http
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests().antMatchers("/auth/**").permitAll()
		.antMatchers("/**").authenticated();
    }
	
}
