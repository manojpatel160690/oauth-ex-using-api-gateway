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
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4898545845565248509L;
	
	private final int code;
	private final String errorMessage;

	public DataNotFoundException(ErrorMsgCode errorMsgCode) {
		super(errorMsgCode.getErrorMessage());
		this.code = errorMsgCode.getCode();
		this.errorMessage = errorMsgCode.getErrorMessage();
	}
	
}
