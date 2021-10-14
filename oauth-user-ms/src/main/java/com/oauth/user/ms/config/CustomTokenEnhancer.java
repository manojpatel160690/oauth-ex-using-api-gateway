package com.oauth.user.ms.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manoj
 *
 */
@Slf4j
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        log.debug("enhance() : enhancing the access token");
		//UserDetail userDetail = (UserDetail)authentication.getPrincipal();

		Map<String,Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
		
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);

		return super.enhance(customAccessToken, authentication);
    }
	
}
