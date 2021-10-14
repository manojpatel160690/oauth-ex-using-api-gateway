/**
 * 
 */
package com.oauth.user.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author manoj
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationResponseDTO {

	private long userId;
	private String emailId;
	private String role;
	
}
