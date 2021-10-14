/**
 * 
 */
package com.oauth.user.ms.dto;

import com.oauth.user.ms.enums.Severity;

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
public class ApiError {

	private int code;
	private String errorMessage;
	private Severity severity;
	private String detailMessage;
	
}
