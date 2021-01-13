package com.etender.SecurityRole.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etender.SecurityRole.Entity.User;
import com.etender.SecurityRole.Repository.TenderSupplierUserRepository;
import com.etender.SecurityRole.Repository.UserRepository;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	

	@Autowired
	TenderSupplierUserRepository tenderSupplierUserRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	public UserController(UserRepository userRepository)
	{
		
		this.userRepository=userRepository;
	
	}
	
	@GetMapping("/profile")
	public String Profile(Model theModel) {
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		User details= userRepository.findByEmail(userDetails.getUsername());

		theModel.addAttribute("user",userRepository.findByUserId(details.getUserId()));
		
		
		return "views/profile";
		
		
	}
	
	
	
	
	  @GetMapping(value="/changePass")
	  public String changePassw(Model theModel) {
		  
		  theModel.addAttribute("user",new User());
		  
		  
		  
		  return "views/changePassword";
		  
		  
	  }
	  
	  
	
	 @PostMapping(value = "/changePass")
	 	public String changePass(@ModelAttribute("user") User user,Model theModel) {
		 
		 
		 
		 UserDetails userDetails =
    			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	  User details= userRepository.findByEmail(userDetails.getUsername());
	 	  
	 	  
	 	  Optional<User> user11=userRepository.findById(details.getUserId());
	 	  
	 	  User user22=user11.get();
	 	  
	 	  
	 	  boolean result = passwordEncoder.matches(user.getUser_password(), user22.getUser_password());   //(passwordEncoder.matches(existingPassword, dbPassword))
	 	 
	 	  if(result)
	 	  { 
	 		 
	 		  
	 		  user22.setUser_password(passwordEncoder.encode(user.getNewPassword()));
	 		  
	 		  userRepository.save(user22);
	 		  
	 		  return "views/PasswordChangeSuccess";
	 		  
	 	  }
	 	  
	 	  else 
	 	  {
	 		System.out.println(user.getUser_password());
	 	  	System.out.println(passwordEncoder.encode(user.getUser_password()));
	 	  	System.out.println(user22.getUser_password());
	 	  	
	 	  	System.out.println(user.getUserId());
	 	  	System.out.println(user.getNewPassword());
	 	  	
	 	  	
	 	  	
	 	  	theModel.addAttribute("exist",true);
	 		  return "views/changePassword";
	 			  
	 	  }
	 	 
	 }
	
	 
	 @Bean
	    public PasswordEncoder getPasswordEncoder(){
	        return new BCryptPasswordEncoder();
	    } 
	
	
	

}
