package com.ftn.kts_nvt.helper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.Authority;
import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.repositories.AuthorityRepository;

@Component
public class RegisteredUserMapper implements MapperInterface<RegisteredUser, UserDTO> {

	@Autowired
	AuthorityRepository authRepository;
	
	@Override
	public RegisteredUser toEntity(UserDTO dto) {
		RegisteredUser r = new RegisteredUser();
		
		r.setEmail(dto.getEmail());
		r.setPassword(dto.getPassword());
		Authority auth = authRepository.findByName("ROLE_USER");
		ArrayList<Authority> aL = new ArrayList<>();
		aL.add(auth);
		r.setAuthorities(aL);
		r.setComments(new ArrayList<Comment>());
		r.setCulturalOffers(new ArrayList<>());
		r.setFirstName(dto.getFirstName());
		r.setLastName(dto.getLastName());
		
		return r;
	}

	@Override
	public UserDTO toDto(RegisteredUser entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
