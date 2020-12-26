package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Admin;
import com.ftn.kts_nvt.repositories.AdminRepository;

@Service
public class AdminService implements ServiceInterface<Admin>{
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin save(Admin a) {
		Admin saved = this.adminRepository.save(a);
		
		return saved;
	}

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	public Page<Admin> findAll(Pageable pageable){
		return adminRepository.findAll(pageable);
	}
	
	@Override
	public Admin findOne(Long id) {
		return adminRepository.findById(id).orElse(null);

	}
	
	public Admin findOneByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	@Override
	public Admin create(Admin entity) throws Exception {
		return adminRepository.save(entity);
	}

	@Override
	public Admin update(Admin entity, Long id) throws Exception {
		Admin user = adminRepository.findById(id).orElse(null);

		if (user != null) {
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());

		} else {
			throw new Exception("User with given id doesn't exist.");
		}

		return adminRepository.save(user);
	}

	@Override
	public void delete(Long id) throws Exception {
		Admin user = adminRepository.findById(id).orElse(null);

		if (user != null) {
			adminRepository.delete(user);
		} else {
			throw new Exception("User with given id doesn't exist.");
		}
	}
}
