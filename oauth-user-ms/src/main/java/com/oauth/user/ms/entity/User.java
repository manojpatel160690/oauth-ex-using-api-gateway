/**
 * 
 */
package com.oauth.user.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author manoj
 *
 */
@Entity
@Table(name = "TBL_USER", uniqueConstraints = @UniqueConstraint(columnNames = "USR_EMAIL_ID"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "USR_USER_ID", nullable = false)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator_seq")
	//@SequenceGenerator(name = "user_id_generator_seq", sequenceName = "user_id_generator_seq", allocationSize = 1, initialValue = 1)
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_generator")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_generator")
	private long userId;
	
	@Column(name = "USR_EMAIL_ID", nullable = false, unique = true)
	private String emailId;
	
	@Column(name = "USR_PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "USR_FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name = "USR_LAST_NAME", nullable = false)
    private String lastName;
	
	@Column(name = "USR_ROLE_ID", nullable = false)
	private int roleId;	
	
	@Column(name = "USR_IS_ENABLED", nullable = false)
    private boolean enabled;
	
	@Column(name = "USR_IS_ACCOUNT_NON_EXPIRED", nullable = false)
    private boolean accountNonExpired;
	
	@Column(name = "USR_IS_CREDENTIALS_NON_EXPIRED", nullable = false)
    private boolean credentialsNonExpired;
	
	@Column(name = "USR_IS_ACCOUNT_NON_LOCKED", nullable = false)
    private boolean accountNonLocked;
	
}
