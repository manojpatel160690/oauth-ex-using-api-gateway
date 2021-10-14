/**
 * 
 */
package com.oauth.user.ms.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author manoj
 *
 */
@Getter
@AllArgsConstructor
public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 5962083108385840250L;

	private final String username;
	private final String password;
	private final String firstName;
	private final String lastName;
	private Collection<SimpleGrantedAuthority> authorities;
	private final boolean enabled;
	private final boolean accountNonExpired;
	private final boolean credentialsNonExpired;
	private final boolean accountNonLocked;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(Objects.isNull(authorities)) {
			authorities = new ArrayList<>();
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
