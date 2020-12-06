package com.ftn.kts_nvt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.kts_nvt.beans.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{

	public VerificationCode findByUser(long id);
	
}
