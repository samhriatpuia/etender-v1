package com.etender.SecurityRole.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etender.SecurityRole.Entity.TenderDepartmentUser;

@Repository
public interface TenderDepartmentUserRepository extends JpaRepository <TenderDepartmentUser, Integer>{
}
