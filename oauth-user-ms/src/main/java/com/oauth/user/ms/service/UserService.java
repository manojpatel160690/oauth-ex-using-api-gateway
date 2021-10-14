/**
 * 
 */
package com.oauth.user.ms.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.oauth.user.ms.dto.LoginResponseDTO;
import com.oauth.user.ms.dto.UserRegistrationRequestDTO;
import com.oauth.user.ms.dto.UserRegistrationResponseDTO;

/**
 * @author manoj
 *
 */
public interface UserService extends UserDetailsService {

	//public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws DataNotFoundException;

	public UserRegistrationResponseDTO register(UserRegistrationRequestDTO userRegistrationRequestDTO);

	public LoginResponseDTO getUserById(String emailId);
}
