
package com.etender.SecurityRole.Config;


/*
 * 
 * This class is for Security Configurations
 * 
 */

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select email as principal, user_password as credentails, true from user where email=?")
//		.authoritiesByUsernameQuery("select user_email as principal, role_name as role from user_role where user_email=?")
		.authoritiesByUsernameQuery("select email as principal, user_role as role from user where email=?")
		.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");  
		
	}
   
	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/register", "/", "/about", "/login", "/css/**", "/webjars/**","/index","/tenderMain/**","/tenderMain/getValidTender","/index/**","/search","/showAllDept","/tenderDepartment/findOne/**","/tenderDepartment/**","/error","/admin/**","/otpCompare","/admin/addDeptUser","contactus").permitAll()
				.antMatchers("/profile","/user/profile","/user/**").hasAnyRole("SUP,DEPT,ADMIN")
				.antMatchers("/users","/admin/showAllDept").hasRole("ADMIN")
				.and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/index").and().logout().logoutSuccessUrl("/index");
	}

}