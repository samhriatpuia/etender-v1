package com.etender.SecurityRole.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etender.SecurityRole.Entity.TenderSupplierUser;


@Repository
public interface TenderSupplierUserRepository extends JpaRepository<TenderSupplierUser, Integer> {


}
