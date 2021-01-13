package com.etender.SecurityRole.Services;

import java.util.List;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;



public interface TenderDepartmentService {

	public List<TenderDepartmentEntity> findAllByOrderByNameAsc();
	
	public List<TenderDepartmentEntity> findAllByOrderByNameDesc();

	public TenderDepartmentEntity findById (int theId);

	public TenderDepartmentEntity findByName(String dept);
	
}
