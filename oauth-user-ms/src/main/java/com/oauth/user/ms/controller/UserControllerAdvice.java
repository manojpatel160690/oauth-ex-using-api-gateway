/**
 * 
 */
package com.oauth.user.ms.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oauth.user.ms.dto.ApiError;
import com.oauth.user.ms.dto.ApiResponse;
import com.oauth.user.ms.enums.Severity;
import com.oauth.user.ms.exceptions.DataNotFoundException;
import com.oauth.user.ms.exceptions.UserLoginException;
import com.oauth.user.ms.exceptions.UserRegistrationException;

/**
 * @author manoj
 *
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({DataNotFoundException.class})
	public ResponseEntity<ApiResponse> handleUsernameNotFoundException(DataNotFoundException ex) {
		ApiError apiError = new ApiError(ex.getCode(), ex.getErrorMessage(), Severity.CRITICAL, "User is not registered in the system with provided emailId");
		ApiResponse apiResponse = new ApiResponse(false, null, "Login failed", apiError);
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@ExceptionHandler({UserLoginException.class})
	public ResponseEntity<ApiResponse> handleUserLoginException(UserLoginException ex) {
		ApiError apiError = new ApiError(ex.getCode(), ex.getErrorMessage(), Severity.CRITICAL, "Something went wrong while logging in user to the system");
		ApiResponse apiResponse = new ApiResponse(false, null, "Login failed", apiError);
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@ExceptionHandler({UserRegistrationException.class})
	public ResponseEntity<ApiResponse> handleUserRegistrationException(UserRegistrationException ex) {
		ApiError apiError = new ApiError(ex.getCode(), ex.getErrorMessage(), Severity.CRITICAL, "Something went wrong while registering user to the system");
		ApiResponse apiResponse = new ApiResponse(false, null, "User registration failed", apiError);
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
}
