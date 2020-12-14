package com.ftn.kts_nvt.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

	public RegisteredUser findByEmail(String email);
	
	
}
