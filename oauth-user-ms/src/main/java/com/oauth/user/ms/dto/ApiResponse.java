/**
 * 
 */
package com.oauth.user.ms.dto;

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
public class ApiResponse {

	private boolean success;
	private Object payload;
	private String message;
	private ApiError error;
	
}
