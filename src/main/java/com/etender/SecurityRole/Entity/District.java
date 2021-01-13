package com.etender.SecurityRole.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class District {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotEmpty(message="Name field should not be empty")
	@Column(name="id")
	private int id;
	
	
	
	@Column(name="name")
	private String name;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public District(@NotEmpty(message = "Name field should not be empty") int id, String name) {
	
		this.id = id;
		this.name = name;
	}



	@Override
	public String toString() {
		return "District [id=" + id + ", name=" + name + "]";
	}



	public District() {
		
	}
	
	
	
	
	
	
}
