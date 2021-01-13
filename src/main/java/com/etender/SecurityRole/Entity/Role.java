//created by Thanpuia Fanai 


package com.etender.SecurityRole.Entity;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;







@Entity
public class Role implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	
	
	@Column(name="name")
	private String name;
	
	
	@ManyToMany(mappedBy = "role")
	private List<User> user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return user;
	}

	public void setUsers(List<User> user) {
		this.user = user;
	}

	public Role(String name, List<User> user) {
		this.name = name;
		this.user = user;
	}

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(Integer id, String name) {
	
		this.id = id;
		this.name = name;
	}
	
	

}