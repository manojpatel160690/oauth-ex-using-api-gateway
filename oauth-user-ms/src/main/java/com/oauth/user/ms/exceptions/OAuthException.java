/**
 * 
 */
package com.oauth.user.ms.exceptions;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author manoj
 *
 */
public class OAuthException extends OAuth2Exception {

	private static final long serialVersionUID = 4169547032085416188L;

	public OAuthException(String msg) {
		super(msg);
	}

}
