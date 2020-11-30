package com.ftn.kts_nvt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;

@Service
public class CulturalOfferCategoryService {
	
	@Autowired
	private CulturalOfferCategoryRepository culturalOfferCategoryRepository;

}
