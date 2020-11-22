package com.ftn.kts_nvt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.repositories.InstitutionRepository;

@Service
public class InstitutionService {

	@Autowired
	private InstitutionRepository institutionRepository;
}
