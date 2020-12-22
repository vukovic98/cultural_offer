package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;

@Service
public class CulturalOfferCategoryService implements ServiceInterface<CulturalOfferCategory>{
	
	@Autowired
	private CulturalOfferCategoryRepository repository;

	@Autowired
	private CulturalOfferTypeRepository culturalOfferRepository;
	
	@Override
	public List<CulturalOfferCategory> findAll() {
		return repository.findAll();
	}

	public Page<CulturalOfferCategory> findAll(Pageable pagable) {
		return repository.findAll(pagable);
	}
	
	@Override
	public CulturalOfferCategory findOne(Long id) {
		return repository.findById(id).orElse(null);
	}

	public CulturalOfferCategory findByName(String name) {
		return repository.findCulturalOfferCategoryByName(name).orElse(null);
	}
	
	@Override
	public CulturalOfferCategory create(CulturalOfferCategory entity) throws Exception {
		CulturalOfferCategory c = repository.findCulturalOfferCategoryByName(entity.getName()).orElse(null);
		if(c != null && c.getName().contentEquals(entity.getName())) {
			throw new Exception("CulturalOfferCategory with given name already exist");
		}
		return repository.save(entity);
	}
	
	public CulturalOfferCategory save(CulturalOfferCategory c) {
		return this.repository.save(c);
	}

	@Override
	public CulturalOfferCategory update(CulturalOfferCategory entity, Long id) throws Exception {
		CulturalOfferCategory existingCategory = repository.findById(id).orElse(null);
		if (existingCategory == null) {
			throw new Exception("CulturalOfferCategory with given id doesn't exist");
		}
		
		CulturalOfferCategory byName = repository.findCulturalOfferCategoryByName(entity.getName()).orElse(null);
		if(byName != null && byName.getName().contentEquals(entity.getName())) {
			throw new Exception("CulturalOfferCategory with given name already exist");
		}
		
		existingCategory.setName(entity.getName());
		return repository.save(existingCategory);	}

	@Override
	public void delete(Long id) throws Exception {	
		CulturalOfferCategory existingCategory = repository.findById(id).orElse(null);
		if (existingCategory == null) {
			throw new Exception("CulturalOfferCategory with given id doesn't exist");
		}
		ArrayList<CulturalOfferType> list = culturalOfferRepository.findByCategoryId(id);
		if(list.size() != 0) {
			throw new Exception("CulturalOfferCategory is used in CulturalOfferType");
		}
		repository.delete(existingCategory);
	}
	
	
}
