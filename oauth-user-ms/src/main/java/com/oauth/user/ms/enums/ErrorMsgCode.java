package com.oauth.user.ms.enums;

import lombok.Getter;

@Getter
public enum ErrorMsgCode {

	USER_NOT_FOUND_EXCEPTION(100, "User not found in the system."),
	USER_LOGIN_EXCEPTION(101, "Something went wrong while performing login in the system."),
	USER_REGISTRATION_EXCEPTION(102, "Something went wrong while registering user in the system."),
	USER_ALREADY_EXISTS_EXCEPTION(103, "User already exists in the system")
	;
	
	private final int code;
	private final String errorMessage;
	
	private ErrorMsgCode(int code, String errorMessage) {
		this.code = code;
		this.errorMessage = errorMessage;
	}
	
}
