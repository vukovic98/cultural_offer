package com.ftn.kts_nvt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.kts_nvt.beans.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	public Admin findByEmail(String email);
	
}
