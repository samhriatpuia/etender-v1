package com.etender.SecurityRole.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.etender.SecurityRole.Entity.TenderMain;
 

@Repository
public interface TenderMain1Repository extends PagingAndSortingRepository<TenderMain, Integer> {
	
	
	//public Page<TenderMain> findAllByOrderByIssueDateAsc(Pageable page);
	
public Page<TenderMain> findAllByOrderByIssueDateDesc(Pageable page);

	public Optional<TenderMain>  findById(Integer theId);
	
	public List<TenderMain> findByDepartment(Integer deptId);
	
	//public List<TenderMain> findByLastDateOfSubmissionGreaterThan(Integer today);
	
	public Page<TenderMain> findByLastDateOfSubmissionGreaterThan(Integer today,Pageable pageable);
	
	public Page<TenderMain> findByIssueDateBetween(Integer start, Integer end,Pageable page);
	
//	public List<TenderMain> findByDepartmentAndLastDateOfSubmissionGreaterThan(Integer deptId, Integer today, Pageable pageable);
	public Page<TenderMain> findByDepartmentAndLastDateOfSubmissionGreaterThan(Integer deptId, Integer today,Pageable pageable);
	
	public Page<TenderMain> findByDetailContainingAndLastDateOfSubmissionGreaterThan(String keyword, Integer today,Pageable pageable);
	
	//public List<TenderMain> findByDetailContainingAndDepartmentAndLastDateOfSubmissionGreaterThan(String keyword,Integer deptId, Integer today);

	public Page<TenderMain> findByDetailContainingAndDepartmentAndLastDateOfSubmissionGreaterThan(String keyword,Integer deptId, Integer today,Pageable pageable);

}