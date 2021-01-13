package com.etender.SecurityRole.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etender.SecurityRole.Entity.DownloadEntity;
import com.etender.SecurityRole.Repository.DownloadRepository;



@Service
public class DownloadServiceImpl implements DownloadService {

	private DownloadRepository downloadRepository;
	
	@Autowired
	public DownloadServiceImpl(DownloadRepository theDownloadRepository) {
		downloadRepository = theDownloadRepository;
	}
	
	public DownloadEntity findById(int theId) {
     
		System.out.println("what the what");

        DownloadEntity result = downloadRepository.findById(theId);
		
        
	
		return result;
	}
}
