package com.etender.SecurityRole.Entity;

/*
 * 	Created By: Lalthanpuia Chhangte
	Date 	:  27 July, 19
	Description:	TenderDepartmentUser
 * 
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tender_department_user")
public class TenderDepartmentUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="")
	Integer id;
	
	@Column (name="user_id")
	Integer user_id;
	
	@Column (name="department_id")
	int department_id;
	
	@Column (name="designation")
	String designation;
	
	@Column (name="district")
	String district;
	
	@Column (name="approved")
	Integer approved;

	
	
	

	public TenderDepartmentUser() {
		
	}

	public TenderDepartmentUser(Integer id, Integer user_id, int department_id, String designation, String district,
			Integer approved) {
		this.id = id;
		this.user_id = user_id;
		this.department_id = department_id;
		this.designation = designation;
		this.district = district;
		this.approved = approved;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	
	
}
