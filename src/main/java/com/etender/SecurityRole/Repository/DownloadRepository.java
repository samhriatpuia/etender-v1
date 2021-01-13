package com.etender.SecurityRole.Repository;
/*
 * 
 * created by Lalthanpuia Chhangte 28 July 19
 * 
 * 
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.etender.SecurityRole.Entity.DownloadEntity;




public interface DownloadRepository extends JpaRepository<DownloadEntity, Integer> {
     
	//public DownloadEntity findById(int theId);
	//public DownloadEntity  findById(int theId);
	
	//NEW ADDED
	public DownloadEntity  findById(int theId);


}
