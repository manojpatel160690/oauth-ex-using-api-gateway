/**
 * 
 */
package com.oauth.user.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oauth.user.ms.entity.User;

/**
 * @author manoj
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.emailId = ?1")
	public User findUserByEmailId(String emailId);
	
}
