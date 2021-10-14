/**
 * 
 */
package com.oauth.user.ms.dto;

import lombok.Data;

/**
 * @author manoj
 *
 */
@Data
public class LoginResponseDTO {

	private String emailId;
	private String userName;
	private String role;
	private String authToken;
	private long authTokenValidity;
	private String refreshToken;
	private long refreshTokenValidity;
	
}
