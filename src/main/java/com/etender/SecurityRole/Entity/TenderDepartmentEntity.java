package com.etender.SecurityRole.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tender_department")
public class TenderDepartmentEntity  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	int id;
	
	@Column (name="name")
	String name;
	
	@Column (name="color")
	String color;
	
	@Column (name="parent")
	int parent;
	
	@Column (name="user_id")
	private Integer user_id;
	
	@Column (name="created")
	int created;
	
	@Column (name="updated")
	int updated;

	public TenderDepartmentEntity() {
	}

	public TenderDepartmentEntity(int id, String name, String color, int parent, Integer user_id, int created, int updated) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.parent = parent;
		this.user_id=user_id;
		this.created = created;
		this.updated = updated;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer user_id) {
		this.user_id = user_id;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "TenderDepartment [id=" + id + ", name=" + name + ", color=" + color + ", parent=" + parent + ", userId="
				+ user_id + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
}
