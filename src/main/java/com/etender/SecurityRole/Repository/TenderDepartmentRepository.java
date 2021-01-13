package com.etender.SecurityRole.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
/*
 * 
 * created by Lalthanpuia Chhangte 27July 19
 * 
 * 
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;



public interface TenderDepartmentRepository extends JpaRepository<TenderDepartmentEntity, Integer>{
	
	
	
	TenderDepartmentEntity findByName(String name);
	
	
	
	
	public Page<TenderDepartmentEntity> findAllByOrderByNameAsc(Pageable pageable);
	
	public List<TenderDepartmentEntity> findAllByOrderByNameAsc();
	
	public List<TenderDepartmentEntity> findAllByOrderByNameDesc();

}
