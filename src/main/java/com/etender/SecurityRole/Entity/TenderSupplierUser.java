package com.etender.SecurityRole.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;



@Entity
public class TenderSupplierUser {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@NotEmpty(message="Name field should not be empty")
	@Column(name="id")
	private int id;
	
	
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="department_subscription")
	private String departmentSubscription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDepartmentSubscription() {
		return departmentSubscription;
	}

	public void setDepartmentSubscription(String departmentSubscription) {
		this.departmentSubscription = departmentSubscription;
	}

	public TenderSupplierUser(@NotEmpty(message = "Name field should not be empty") int id, Integer userId,
			String departmentSubscription) {
		
		this.id = id;
		this.userId = userId;
		this.departmentSubscription = departmentSubscription;
	}
	
	
	

	public TenderSupplierUser(Integer userId, String departmentSubscription) {
	
		this.userId = userId;
		this.departmentSubscription = departmentSubscription;
	}

	@Override
	public String toString() {
		return "tender_supplier_user [id=" + id + ", userId=" + userId + ", departmentSubscription="
				+ departmentSubscription + "]";
	}
	
	

	public TenderSupplierUser() {
		
	}
	
	
	
	
	

}
