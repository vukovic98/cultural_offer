package com.ftn.kts_nvt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.repositories.VerificationCodeRepository;

@Service
public class VerificationCodeService {
	
	@Autowired
	private VerificationCodeRepository verificationCodeRepository;
	
	public VerificationCode findCodeById(long id) {
		return this.verificationCodeRepository.findById(id).orElse(null);
	}
	
	public VerificationCode findCodeByUser(long id) {
		return this.verificationCodeRepository.findByUser(id);
	}
}
