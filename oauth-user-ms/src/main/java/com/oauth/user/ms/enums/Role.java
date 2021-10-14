/**
 * 
 */
package com.oauth.user.ms.enums;

import lombok.Getter;

/**
 * @author manoj
 *
 */
@Getter
public enum Role {
	
	SELLER(5, "SELLER()"),
	USER(4, "USER"),
	ADMIN(3, "ADMIN"),
	SYS_USER(2, "SYS_USER"),
	SYS_ADMIN(1, "SYS_ADMIN")
	;
	
	private final int roleId;
	private final String role;
	
	private Role(int roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}
	
}
