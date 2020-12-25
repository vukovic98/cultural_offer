package com.ftn.kts_nvt.helper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.Admin;
import com.ftn.kts_nvt.beans.Authority;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.repositories.AuthorityRepository;

@Component
public class AdminMapper implements MapperInterface<Admin, UserDTO>{

	@Autowired
	AuthorityRepository authRepository;
	
	@Override
	public Admin toEntity(UserDTO dto) {
		Admin a = new Admin();
		
		a.setEmail(dto.getEmail());
		a.setPassword(dto.getPassword());
		Authority auth = authRepository.findByName("ROLE_ADMIN");
		ArrayList<Authority> aL = new ArrayList<>();
		aL.add(auth);
		a.setAuthorities(aL);
		a.setCulturalOffers(new ArrayList<>());
		a.setFirstName(dto.getFirstName());
		a.setLastName(dto.getLastName());
		
		return a;
	}

	@Override
	public UserDTO toDto(Admin entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
