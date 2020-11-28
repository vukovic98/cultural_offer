package com.ftn.kts_nvt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Admin;
import com.ftn.kts_nvt.repositories.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin save(Admin a) {
		Admin saved = this.adminRepository.save(a);
		
		return saved;
	}
}
