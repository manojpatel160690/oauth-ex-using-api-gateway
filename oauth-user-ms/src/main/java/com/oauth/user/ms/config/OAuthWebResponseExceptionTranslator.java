/**
 * 
 */
package com.oauth.user.ms.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import com.oauth.user.ms.dto.ApiResponse;

/**
 * @author manoj
 *
 */
public class OAuthWebResponseExceptionTranslator<T> implements WebResponseExceptionTranslator<ApiResponse> {

	@Override
	public ResponseEntity<ApiResponse> translate(Exception ex) throws Exception {
		ApiResponse apiResponse = null;
		if(ex instanceof OAuth2Exception) {
			OAuth2Exception auth2Exception = (OAuth2Exception)ex;
			apiResponse = new ApiResponse(false, null, auth2Exception.getMessage(), null);
		} else {
			apiResponse = new ApiResponse(false, null, "Something went wrong with the request.", null);
		}
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		return (ResponseEntity<ApiResponse>) responseEntity;
	}

}
