/**
 * 
 */
package com.oauth.user.ms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.user.ms.dto.ApiResponse;
import com.oauth.user.ms.dto.LoginResponseDTO;
import com.oauth.user.ms.dto.UserRegistrationRequestDTO;
import com.oauth.user.ms.dto.UserRegistrationResponseDTO;
import com.oauth.user.ms.service.UserService;

/**
 * @author manoj
 *
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;

	/*
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
		LoginResponseDTO loginResponseDTO =  userService.login(loginRequestDTO);
		ApiResponse apiResponse = new ApiResponse(true, loginResponseDTO, "Login Successful", null);
		return this.responseEntity(HttpStatus.ACCEPTED, apiResponse);
	}
	*/
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody @Valid UserRegistrationRequestDTO userRegistrationRequestDTO) {
		UserRegistrationResponseDTO userRegistrationResponseDTO = userService.register(userRegistrationRequestDTO);
		ApiResponse apiResponse = new ApiResponse(true, userRegistrationResponseDTO, "User registration successful", null);
		return this.responseEntity(HttpStatus.CREATED, apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getUserById(@PathVariable("id") String emailId) {
		LoginResponseDTO loginResponseDTO = userService.getUserById(emailId);
		ApiResponse apiResponse = new ApiResponse(true, loginResponseDTO, "User details fetched successfully", null);
		return this.responseEntity(HttpStatus.OK, apiResponse);
	}
	
	private ResponseEntity<ApiResponse> responseEntity(HttpStatus status, ApiResponse apiResponse) {
		return new ResponseEntity<>(apiResponse, status);
	}
}
