/**
 * 
 */
package com.oauth.user.ms.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.oauth.user.ms.service.UserService;

/**
 * @author manoj
 *
 */
@Configuration
@EnableWebSecurity
public class UserMsSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JsonToUrlEncodedAuthenticationFilter jsonFilter;
	
	public static final List<String> ALLOWED_METHODS = Arrays.asList("HEAD", "GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH");
	public static final List<String> ALLOWED_HEADERS = Arrays.asList(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS ,HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS
			, HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, HttpHeaders.AUTHORIZATION
			, HttpHeaders.CACHE_CONTROL, HttpHeaders.CONTENT_TYPE
			, HttpHeaders.ORIGIN, HttpHeaders.ACCEPT, "X-Requested-With"
			);
	public static final String ALLOWED_URL_CORS = "/**";

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		AuthenticationEntryPoint authenticationEntryPoint = new OAuthAuthenticationEntryPoint();
		return authenticationEntryPoint;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.cors().disable()
		.headers().frameOptions().disable()
		.and()
		.httpBasic().disable()
		.formLogin().disable().logout().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
		.and()
		.authorizeRequests().antMatchers("/h2-console/**", "/oauth/token", "/users/**").permitAll()
		.and()
		.authorizeRequests().anyRequest().authenticated()
		.and()
		.addFilterAfter(jsonFilter, BasicAuthenticationFilter.class)
		;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(getAllowedOrigins());
		configuration.setAllowedMethods(ALLOWED_METHODS);
		configuration.setAllowCredentials(Boolean.TRUE);
		configuration.setAllowedHeaders(ALLOWED_HEADERS);

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(ALLOWED_URL_CORS, configuration);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> logggingFilter() {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CorsFilter());
		registrationBean.addUrlPatterns("/*");;
		return registrationBean;
	}
	
	private List<String> getAllowedOrigins() {
		/*
		 * List<String> allowedOrigins = null; if(Objects.isNull(ALLOWED_ORIGIN_URLS)) {
		 * allowedOrigins = new ArrayList<>(); allowedOrigins.add("*"); } else {
		 * allowedOrigins = Arrays.asList("/oauth/token", "/user/register",
		 * "/oauth/check_token"); }
		 */
		return Arrays.asList("/oauth/token", "/user/register", "/oauth/check_token");
	}


}
