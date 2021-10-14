/**
 * 
 */
package com.oauth.user.ms.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author manoj
 *
 */
@Data
public class LoginRequestDTO {

	@NotBlank(message = "All mandatory fields are not provided")
	private String emailId;
	
	@NotBlank(message = "All mandatory fields are not provided")
	private String password;
	
}
