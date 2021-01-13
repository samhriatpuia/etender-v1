package com.etender.SecurityRole.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;
import com.etender.SecurityRole.Repository.TenderDepartmentRepository;


@Service
public class TenderDepartmentServiceImpl implements TenderDepartmentService {
	
	private TenderDepartmentRepository tenderDepartmentRepository;
	
	@Autowired
	public TenderDepartmentServiceImpl (TenderDepartmentRepository theTenderDepartmentRepository) {
		tenderDepartmentRepository = theTenderDepartmentRepository;
	}

	public List<TenderDepartmentEntity> findAll() {
		
		System.out.println("Service Impl");
		return tenderDepartmentRepository.findAll();
	}

	public TenderDepartmentEntity findById(int theId) {

		Optional<TenderDepartmentEntity> result = tenderDepartmentRepository.findById(theId);
		TenderDepartmentEntity theTenderDepartmentEntity = null;
		
		if (result.isPresent())
			theTenderDepartmentEntity = result.get();
		else 
			throw new RuntimeException ("Did not find employee id-" + theId);
		
		return theTenderDepartmentEntity;
	}

	public TenderDepartmentEntity findByName(String dept) {

		return tenderDepartmentRepository.findByName(dept);
	}

	public List<TenderDepartmentEntity> findAllByOrderByNameAsc() {
	
		return tenderDepartmentRepository.findAllByOrderByNameAsc();
	}

	@Override
	public List<TenderDepartmentEntity> findAllByOrderByNameDesc() {

		return tenderDepartmentRepository.findAllByOrderByNameDesc();
	}
	
	
}
