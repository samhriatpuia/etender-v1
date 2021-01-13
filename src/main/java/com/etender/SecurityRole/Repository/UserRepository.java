package com.etender.SecurityRole.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.etender.SecurityRole.Entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

	/* List<User> findByUser_full_nameLike(String user_full_name); */

	User findByEmail(String email); 
	
	public User findByUserId(Integer id);


}
