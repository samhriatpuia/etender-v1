package com.etender.SecurityRole.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etender.SecurityRole.Entity.Role;

@Repository

public interface RoleRepository  extends JpaRepository<Role, String>{
 
}
