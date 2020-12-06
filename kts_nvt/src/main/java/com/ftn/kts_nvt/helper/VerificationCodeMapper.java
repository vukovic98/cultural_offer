package com.ftn.kts_nvt.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.dto.VerificationCodeDTO;
import com.ftn.kts_nvt.services.RegisteredUserService;

@Component
public class VerificationCodeMapper implements MapperInterface<VerificationCode, VerificationCodeDTO> {

	@Autowired
	private RegisteredUserService registeredUserService;
	
	@Override
	public VerificationCode toEntity(VerificationCodeDTO dto) {
		return null;
	}

	@Override
	public VerificationCodeDTO toDto(VerificationCode entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
