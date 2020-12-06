package com.ftn.kts_nvt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.kts_nvt.beans.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{

	public VerificationCode findByUser(long id);

	@Query(
			value = "SELECT * FROM verification_code WHERE user_id = ?1",
			nativeQuery = true)
	public VerificationCode findByUserId(Long id);
	
}
