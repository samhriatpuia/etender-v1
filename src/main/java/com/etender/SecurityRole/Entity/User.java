//created by Thanpuia Fanai 25thJuly

package com.etender.SecurityRole.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



 

@Entity
public class User implements Serializable {
	
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * @Column(name="user_id") private Integer user_id;
	 */
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	
	
	@NotEmpty(message="Name field should not be empty")
	@Column(name="user_full_name")
	private String user_full_name;
	
	@Column(name="user_login")
	private String user_login;
	

	@Size(min=2,max=200,message="Password should be between 2 to 20")
	@NotEmpty(message="Password field should not be empty")
	@Column(name="user_password")
	private String user_password;
	
	@Column(name="user_password_salt")
	private String user_password_salt;
	
	
	
	@Email
	@NotEmpty(message="Name field should not be empty")
	//@Column(name="email" unique="true")
	@Column(name="email",unique=true)
	private String email;
	
	
	@NotEmpty(message="Enter Valid Phone Number")
	@Column(name="user_phone")
	private String user_phone;
	
	
	@Column(name="user_address")
	private String user_address;
	
	@Column(name="user_role")
	private String user_role;
	
	
	@Column(name="user_created_date")
	private Integer user_created_date;
	
	@Column(name="user_last_logged")
	private Integer user_last_logged;
	
	
	
	//Department user entity
	
	
	
	
	

	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.ALL
			})
	@JoinTable(name = "USER_ROLE", joinColumns={
			@JoinColumn(name = "USER_EMAIL", referencedColumnName = "email") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "id") })
	
	private List<Role> role;
	
	
	@Transient
	private int department_id;
	
	@Transient
	private String designation;
	
	@Transient
	private String district;
	
	@Transient
	private int approved;
	
	
	@Transient
	private String newPassword;
	
	//////
	
	
	
	// Supplier user entity Transient
	
	
	@Transient
	private String departmentSubscribtion;
	
    @Transient
    private String msegsHoney;
	
	
	

	
	/*
	 * public Integer getUser_id() { return user_id; } public void
	 * setUser_id(Integer user_id) { this.user_id = user_id; }
	 */

	
	

	
	
	public User(){
		
	}





	public Integer getUserId() {
		return userId;
	}





	public void setUserId(Integer userId) {
		this.userId = userId;
	}





	public String getUser_full_name() {
		return user_full_name;
	}





	public void setUser_full_name(String user_full_name) {
		this.user_full_name = user_full_name;
	}





	public String getUser_login() {
		return user_login;
	}





	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}





	public String getUser_password() {
		return user_password;
	}





	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}





	public String getUser_password_salt() {
		return user_password_salt;
	}





	public void setUser_password_salt(String user_password_salt) {
		this.user_password_salt = user_password_salt;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getUser_phone() {
		return user_phone;
	}





	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}





	public String getUser_address() {
		return user_address;
	}





	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}





	public String getUser_role() {
		return user_role;
	}





	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}





	public Integer getUser_created_date() {
		return user_created_date;
	}





	public void setUser_created_date(Integer user_created_date) {
		this.user_created_date = user_created_date;
	}





	public Integer getUser_last_logged() {
		return user_last_logged;
	}





	public void setUser_last_logged(Integer user_last_logged) {
		this.user_last_logged = user_last_logged;
	}





	public List<Role> getRole() {
		return role;
	}





	public void setRole(List<Role> role) {
		this.role = role;
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





	public int getApproved() {
		return approved;
	}





	public void setApproved(int approved) {
		this.approved = approved;
	}





	public String getDepartmentSubscribtion() {
		return departmentSubscribtion;
	}





	public void setDepartmentSubscribtion(String departmentSubscribtion) {
		this.departmentSubscribtion = departmentSubscribtion;
	}


	
	
	



	public String getNewPassword() {
		return newPassword;
	}





	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}



	
	


	public String getMsegsHoney() {
		return msegsHoney;
	}





	public void setMsegsHoney(String msegsHoney) {
		this.msegsHoney = msegsHoney;
	}





	public User(Integer userId, @NotEmpty(message = "Name field should not be empty") String user_full_name,
			String user_login,
			@Size(min = 2, max = 200, message = "Password should be between 2 to 20") @NotEmpty(message = "Password field should not be empty") String user_password,
			String user_password_salt, @Email @NotEmpty(message = "Name field should not be empty") String email,
			@NotEmpty(message = "Enter Valid Phone Number") String user_phone, String user_address, String user_role,
			Integer user_created_date, Integer user_last_logged) {
	
		this.userId = userId;
		this.user_full_name = user_full_name;
		this.user_login = user_login;
		this.user_password = user_password;
		this.user_password_salt = user_password_salt;
		this.email = email;
		this.user_phone = user_phone;
		this.user_address = user_address;
		this.user_role = user_role;
		this.user_created_date = user_created_date;
		this.user_last_logged = user_last_logged;
	}





	public User(@NotEmpty(message = "Name field should not be empty") String user_full_name, String user_login,
			@Size(min = 2, max = 200, message = "Password should be between 2 to 20") @NotEmpty(message = "Password field should not be empty") String user_password,
			String user_password_salt, @Email @NotEmpty(message = "Name field should not be empty") String email,
			@NotEmpty(message = "Enter Valid Phone Number") String user_phone, String user_address, String user_role,
			Integer user_created_date, Integer user_last_logged) {
		super();
		this.user_full_name = user_full_name;
		this.user_login = user_login;
		this.user_password = user_password;
		this.user_password_salt = user_password_salt;
		this.email = email;
		this.user_phone = user_phone;
		this.user_address = user_address;
		this.user_role = user_role;
		this.user_created_date = user_created_date;
		this.user_last_logged = user_last_logged;
	}





	public User(Integer userId, @NotEmpty(message = "Name field should not be empty") String user_full_name,
			String user_login,
			@Size(min = 2, max = 200, message = "Password should be between 2 to 20") @NotEmpty(message = "Password field should not be empty") String user_password,
			String user_password_salt, @Email @NotEmpty(message = "Name field should not be empty") String email,
			@NotEmpty(message = "Enter Valid Phone Number") String user_phone, String user_address, String user_role,
			Integer user_created_date, Integer user_last_logged, List<Role> role) {
		
		this.userId = userId;
		this.user_full_name = user_full_name;
		this.user_login = user_login;
		this.user_password = user_password;
		this.user_password_salt = user_password_salt;
		this.email = email;
		this.user_phone = user_phone;
		this.user_address = user_address;
		this.user_role = user_role;
		this.user_created_date = user_created_date;
		this.user_last_logged = user_last_logged;
		this.role = role;
	}





	@Override
	public String toString() {
		return "User [userId=" + userId + ", user_full_name=" + user_full_name + ", user_login=" + user_login
				+ ", user_password=" + user_password + ", user_password_salt=" + user_password_salt + ", email=" + email
				+ ", user_phone=" + user_phone + ", user_address=" + user_address + ", user_role=" + user_role
				+ ", user_created_date=" + user_created_date + ", user_last_logged=" + user_last_logged + ", role="
				+ role + ", department_id=" + department_id + ", designation=" + designation + ", district=" + district
				+ ", approved=" + approved + ", departmentSubscribtion=" + departmentSubscribtion + "]";
	}






	
}
