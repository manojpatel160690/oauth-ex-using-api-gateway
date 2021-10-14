/**
 * 
 */
package com.oauth.user.ms.exceptions;

import com.oauth.user.ms.enums.ErrorMsgCode;

import lombok.Getter;

/**
 * @author manoj
 *
 */
@Getter
public class UserLoginException extends RuntimeException {

	private static final long serialVersionUID = -7522323412203848284L;
	
	private final int code;
	private final String errorMessage;
	private Throwable cause;
	
	public UserLoginException(ErrorMsgCode errorMsgCode) {
		super(errorMsgCode.getErrorMessage());
		this.code = errorMsgCode.getCode();
		this.errorMessage = errorMsgCode.getErrorMessage();
	}
	
	public UserLoginException(ErrorMsgCode errorMsgCode, Throwable cause) {
		this(errorMsgCode);
		this.cause = cause;
	}
	
}
