package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;

@Service
public class CulturalOfferTypeService {

	@Autowired
	private CulturalOfferTypeRepository culturalOfferTypeRepository;

	@Autowired 
	private CulturalOfferRepository culturalOfferRepository;
	
	@Autowired 
	private CulturalOfferCategoryRepository culturalOfferCategoryRepository;
	
	
	public ArrayList<CulturalOfferType> findAll(long categoryId) {
		return this.culturalOfferTypeRepository.findByCategoryId(categoryId);

	}

	public Page<CulturalOfferType> findAll(Pageable pageable) {
		return this.culturalOfferTypeRepository.findAll(pageable);
	}

	public CulturalOfferType findById(long culturalOfferTypeId) {
		return this.culturalOfferTypeRepository.findById(culturalOfferTypeId).orElse(null);
	}

	public CulturalOfferType findByName(String name) {
		return this.culturalOfferTypeRepository.findByName(name);
	}

	public boolean delete(CulturalOfferType culturalOfferType) throws Exception {
		boolean exists = this.culturalOfferTypeRepository.existsById(culturalOfferType.getId());

		if (exists) {
			ArrayList<CulturalOffer> list = culturalOfferRepository.findByTypeId(culturalOfferType.getId());
			for(CulturalOffer c : list) {
				System.out.println("for = " + c.getName());
			}
			if(list.size() != 0) {
				throw new Exception("CulturalOfferType with given id exist CulturalOffer");
			}else {
				this.culturalOfferTypeRepository.delete(culturalOfferType);			
			}
		}
		return exists;
	}

	public boolean deleteById(long id) throws Exception {
		System.out.println("delte by id = " + id);
		boolean exists = this.culturalOfferTypeRepository.existsById(id);

		if (exists) {
			ArrayList<CulturalOffer> list = culturalOfferRepository.findByTypeId(id);
			for(CulturalOffer c : list) {
				System.out.println("for = " + c.getName());
			}
			System.out.println("size = " + list.size());
			if(list.size() != 0) {
				throw new Exception("CulturalOfferType with given id exist CulturalOffer");
			}else {
				CulturalOfferType type = culturalOfferTypeRepository.findById(id).orElse(null);
				CulturalOfferCategory category = culturalOfferCategoryRepository.findById(type.getCategory().getId()).orElse(null);
				boolean delCatType = category.getTypes().remove(type);
				System.out.println("deleted type from category list = " + delCatType);
				
				culturalOfferCategoryRepository.save(category);
				this.culturalOfferTypeRepository.deleteById(id);
			}
		}
		return exists;
	}

	public CulturalOfferType save(CulturalOfferType culturalOfferType) {
		CulturalOfferType exist = culturalOfferTypeRepository.findByName(culturalOfferType.getName());
		if(exist == null) {
			CulturalOfferType addedType = this.culturalOfferTypeRepository.save(culturalOfferType);
			
			System.out.println("exist == null");
			System.out.println("id = " + culturalOfferType.getCategory().getId());
			CulturalOfferCategory category = culturalOfferCategoryRepository.findById(culturalOfferType.getCategory().getId()).orElse(null);
			System.out.println("found category = " + category.getName() + " " + category.getTypes().size());
			category.getTypes().add(addedType);
			System.out.println("size =  " + category.getTypes().size());
			culturalOfferCategoryRepository.save(category);
			return addedType;			
		}else {
			return null;
		}
	}

	public CulturalOfferType update(CulturalOfferType changedCulturalOfferType, long id) {

		CulturalOfferType found = this.culturalOfferTypeRepository.findById(id).orElse(null);
		if (found != null) {
			CulturalOfferType nameExist = culturalOfferTypeRepository.findByName(changedCulturalOfferType.getName());
			if(nameExist == null) {
				found.setCategory(changedCulturalOfferType.getCategory());
				found.setName(changedCulturalOfferType.getName());
				return this.culturalOfferTypeRepository.save(found);				
			}
			return null;
		} else
			return null;
	}

	public ArrayList<CulturalOfferType> findAll() {
		// TODO Auto-generated method stub
		return (ArrayList<CulturalOfferType>) this.culturalOfferTypeRepository.findAll();
	}

}
