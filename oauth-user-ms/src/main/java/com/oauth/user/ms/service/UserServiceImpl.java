/**
 * 
 */
package com.oauth.user.ms.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oauth.user.ms.dto.LoginResponseDTO;
import com.oauth.user.ms.dto.UserDetail;
import com.oauth.user.ms.dto.UserRegistrationRequestDTO;
import com.oauth.user.ms.dto.UserRegistrationResponseDTO;
import com.oauth.user.ms.entity.User;
import com.oauth.user.ms.enums.ErrorMsgCode;
import com.oauth.user.ms.enums.Role;
import com.oauth.user.ms.exceptions.DataNotFoundException;
import com.oauth.user.ms.exceptions.UserRegistrationException;
import com.oauth.user.ms.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manoj
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws DataNotFoundException {
		log.info("loadUserByUsername() : getting user details for username : {}", username);
		User user = userRepository.findUserByEmailId(username);
		if (Objects.isNull(user)) {
			throw new DataNotFoundException(ErrorMsgCode.USER_NOT_FOUND_EXCEPTION);
		}
		Role role = this.getRoleById(user.getRoleId());
		UserDetail userDetail = new UserDetail(user.getEmailId(), user.getPassword(), user.getFirstName(), user.getLastName(),
				this.getAuthorities(role), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked());
		return userDetail;
	}
	
	private Collection<SimpleGrantedAuthority> getAuthorities(Role role) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
		authorities.add(authority);
		return authorities;
	}
	
	@Override
	public LoginResponseDTO getUserById(String emailId) {
		User user = userRepository.findUserByEmailId(emailId);
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setEmailId(user.getEmailId());
		loginResponseDTO.setRole(this.getRoleById(user.getRoleId()).getRole());
		loginResponseDTO.setUserName(user.getFirstName() + " " + user.getLastName());
		return loginResponseDTO;
	}
	
	/*
	@Override
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws DataNotFoundException {
		try {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword());
			Authentication auth = authenticationManager.authenticate(authReq);
			UserDetail userDetail = (UserDetail)auth.getPrincipal();
			LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
			loginResponseDTO.setEmailId(userDetail.getUsername());
			Optional<? extends GrantedAuthority> authority = userDetail.getAuthorities().stream().findFirst();
			loginResponseDTO.setRole(authority.get().getAuthority());
			String userName = userDetail.getFirstName() + " " + userDetail.getLastName();
			loginResponseDTO.setUserName(userName);
			return loginResponseDTO;
		} catch(Exception ex) {
			if(ex instanceof DataNotFoundException)
				throw ex;
			log.error("login() : execption occurred while performing login", ex);
			throw new UserLoginException(ErrorMsgCode.USER_LOGIN_EXCEPTION, ex);
		}
	}
	*/
	
	@Override
	public UserRegistrationResponseDTO register(UserRegistrationRequestDTO userRegistrationRequestDTO) throws UserRegistrationException {
		log.info("register() : performing user registration for emailId : {}", userRegistrationRequestDTO.getEmailId().trim());
		try {
			User user = new User();
			user.setEmailId(userRegistrationRequestDTO.getEmailId().trim());
			user.setFirstName(userRegistrationRequestDTO.getFirstName().trim());
			user.setLastName(userRegistrationRequestDTO.getLastName().trim());
			user.setPassword(passwordEncoder.encode(userRegistrationRequestDTO.getPassword().trim()));
			user.setRoleId(userRegistrationRequestDTO.getRole().getRoleId());
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			User savedUser = userRepository.save(user);
			Role role = this.getRoleById(savedUser.getRoleId());
			return new UserRegistrationResponseDTO(savedUser.getUserId(), savedUser.getEmailId(), role.getRole());
		} catch (Exception ex) {
			if(ex instanceof DataIntegrityViolationException) {
				throw new UserRegistrationException(ErrorMsgCode.USER_ALREADY_EXISTS_EXCEPTION, ex);
			}
			log.error("register() : exception occurred while performing registration", ex);
			throw new UserRegistrationException(ErrorMsgCode.USER_REGISTRATION_EXCEPTION, ex);
		}
	}

	private Role getRoleById(int roleId) {
		//	USER(4), ADMIN(3), SYS_USER(2), SYS_ADMIN(1)
		switch(roleId) {
			case 1:
				return Role.SYS_ADMIN;
			case 2:
				return Role.SYS_USER;
			case 3:
				return Role.ADMIN;
			case 4:
				return Role.USER;
			default:
				return null;
		}
	}

	


}
