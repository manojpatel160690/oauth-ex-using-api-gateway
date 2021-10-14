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
public class UserRegistrationException extends RuntimeException {

	private static final long serialVersionUID = -2951327678591432547L;

	private final int code;
	private final String errorMessage;
	private Throwable cause;
	
	public UserRegistrationException(ErrorMsgCode errorMsgCode) {
		super(errorMsgCode.getErrorMessage());
		this.code = errorMsgCode.getCode();
		this.errorMessage = errorMsgCode.getErrorMessage();
	}
	
	public UserRegistrationException(ErrorMsgCode errorMsgCode, Throwable cause) {
		this(errorMsgCode);
		this.cause = cause;
	}
}
