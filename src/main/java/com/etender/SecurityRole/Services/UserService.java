package com.etender.SecurityRole.Services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.etender.SecurityRole.Entity.Role;
import com.etender.SecurityRole.Entity.User;
import com.etender.SecurityRole.Repository.UserRepository;





@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void createSupplier(User user) {
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setUser_password(encoder.encode(user.getUser_password())); 
		Role userRole = new Role("SUP");
		List<Role> role = new ArrayList<>();
		role.add(userRole);
		user.setRole(role);
		userRepository.save(user);
	}
	
	public void createAdmin(User user) {
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setUser_password(encoder.encode(user.getUser_password())); 
		Role userRole = new Role("ADMIN");
		List<Role> role = new ArrayList<>();
		role.add(userRole);
		user.setRole(role);
		userRepository.save(user);
	}
	
	public void createDept(User user) {
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setUser_password(encoder.encode(user.getUser_password())); 
		Role userRole = new Role("DEPT");
		List<Role> role = new ArrayList<>();
		role.add(userRole);
		user.setRole(role);
		userRepository.save(user);
	}
	
	
	
	
	public User findOne(String email) {
		
	  return userRepository.findByEmail(email);
	}

	public boolean isUserPresent(String email) {
		// TODO Auto-generated method stub
		User u=userRepository.findByEmail(email);
		if(u!=null)
			return true;
		
		return false;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	/*
	 * public List<User> findByName(String user_full_name) { // TODO Auto-generated
	 * method stub return
	 * userRepository.findByUser_full_nameLike("%"+user_full_name+"%"); }
	 */

}
