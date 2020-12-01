package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;

@Service
public class CulturalOfferCategoryService implements ServiceInterface<CulturalOfferCategory>{
	
	@Autowired
	private CulturalOfferCategoryRepository repository;

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
		return repository.save(entity);
	}

	@Override
	public CulturalOfferCategory update(CulturalOfferCategory entity, Long id) throws Exception {
		CulturalOfferCategory existingCategory = repository.findById(id).orElse(null);
		if (existingCategory == null) {
			throw new Exception("CulturalOfferCategory with given id doesn't exist");
		}
		existingCategory.setName(entity.getName());
		return repository.save(existingCategory);	}

	@Override
	public void delete(Long id) throws Exception {	
		CulturalOfferCategory existingUser = repository.findById(id).orElse(null);
		if (existingUser == null) {
			throw new Exception("CulturalOfferCategory with given id doesn't exist");
		}
		repository.delete(existingUser);
	}
	
	
}
