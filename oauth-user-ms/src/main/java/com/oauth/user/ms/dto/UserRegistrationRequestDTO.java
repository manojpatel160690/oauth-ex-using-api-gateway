/**
 * 
 */
package com.oauth.user.ms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oauth.user.ms.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author manoj
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDTO {
	
	@NotBlank(message = "Mandatory input is not provided in request")
	private String emailId;
	
	@NotBlank(message = "Mandatory input is not provided in request")
	private String password;
	
	@NotBlank(message = "Mandatory input is not provided in request")
	private String firstName;
	
	@NotBlank(message = "Mandatory input is not provided in request")
	private String lastName;
	
	@NotNull(message = "Mandatory input is not provided in request")
	private Role role;

}
