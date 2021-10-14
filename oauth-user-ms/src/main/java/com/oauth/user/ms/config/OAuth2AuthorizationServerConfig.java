/**
 * 
 */
package com.oauth.user.ms.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author manoj
 *
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${check.user.scopes: true}")
	private boolean checkUserScopes;
	
	@Value("${oauth.validityInSeconds.accessTokenValiditySeconds}")
	private int accessTokenValiditySeconds;
	
	@Value("${oauth.validityInSeconds.refreshTokenValiditySeconds}")
	private int refreshTokenValiditySeconds;

	private String clientid = "globaldrop";
	private String clientSecret = "globaldrop";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setAccessTokenConverter(new CustomAccessTokenConverter());
		accessTokenConverter.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
		//accessTokenConverter.setSigningKey("password");
		return accessTokenConverter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices()  {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		  clients.inMemory().withClient(clientid).secret(passwordEncoder.encode(clientSecret)).scopes("read",
		  "write") .authorizedGrantTypes("password",
		  "refresh_token").accessTokenValiditySeconds(accessTokenValiditySeconds)
		  .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
		 
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security)  {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()")
		.allowFormAuthenticationForClients()
		;
	}

	@Override
	public void configure (AuthorizationServerEndpointsConfigurer endpoints) {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter()));
		endpoints.authenticationManager (authenticationManager).tokenEnhancer(tokenEnhancerChain)
		.accessTokenConverter(accessTokenConverter())
		.tokenStore(tokenStore())
		.exceptionTranslator(webResponseExceptionTranslator())
		;
	}
	
	@SuppressWarnings("unchecked")
	@Bean
	public <T> WebResponseExceptionTranslator<T> webResponseExceptionTranslator() {
		return (WebResponseExceptionTranslator<T>) new OAuthWebResponseExceptionTranslator<>();
	}

}
