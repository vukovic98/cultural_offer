package com.ftn.kts_nvt.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;

@Service
public class RegisteredUserService implements ServiceInterface<RegisteredUser> {

	@Autowired
	private RegisteredUserRepository registeredUserRepository;

	@Override
	public List<RegisteredUser> findAll() {
		return registeredUserRepository.findAll();
	}
	
	public Page<RegisteredUser> findAll(Pageable pageable){
		return registeredUserRepository.findAll(pageable);
	}

	@Override
	public RegisteredUser findOne(Long id) {
		return registeredUserRepository.findById(id).orElse(null);
	}
	
	public boolean unsubscribe(RegisteredUser u, Long offer_id) {
		for(CulturalOffer c : u.getCulturalOffers()) {
			if(c.getId() == offer_id) {
				System.out.println("Udje"+ c.getName());
				u.getCulturalOffers().remove(c);
				this.registeredUserRepository.save(u);
				return true;
			}
		}
		return false;
	}
	
	public RegisteredUser findOneByEmail(String email) {
		return this.registeredUserRepository.findByEmail(email);
	}

	@Override
	public RegisteredUser create(RegisteredUser entity) throws Exception {
		return registeredUserRepository.save(entity);
	}

	public RegisteredUser save(RegisteredUser e) {
		return this.registeredUserRepository.save(e);
	}
	
	@Override
	public RegisteredUser update(RegisteredUser entity, Long id) throws Exception {
		RegisteredUser user = registeredUserRepository.findById(id).orElse(null);

		if (user != null) {
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());

		} else {
			throw new Exception("User with given id doesn't exist.");
		}

		return registeredUserRepository.save(user);
	}

	@Override
	public void delete(Long id) throws Exception {
		RegisteredUser user = registeredUserRepository.findById(id).orElse(null);

		if (user != null) {
			registeredUserRepository.delete(user);
		} else {
			throw new Exception("User with given id doesn't exist.");
		}

	}
}
