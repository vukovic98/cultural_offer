package com.ftn.kts_nvt.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;

@Service
public class CulturalOfferTypeService {

	@Autowired
	private CulturalOfferTypeRepository culturalOfferTypeRepository;

	@Autowired 
	CulturalOfferRepository culturalOfferRepository;
	
	public ArrayList<CulturalOfferType> findAll(long categoryId) {
		return this.culturalOfferTypeRepository.findByCategoryId(categoryId);

	}

	public Page<CulturalOfferType> findAll(Pageable pageable) {
		return this.culturalOfferTypeRepository.findAll(pageable);
	}

	public CulturalOfferType findById(long culturalOfferTypeId) {
		return this.culturalOfferTypeRepository.findById(culturalOfferTypeId).orElse(null);
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
		boolean exists = this.culturalOfferTypeRepository.existsById(id);

		if (exists) {
			ArrayList<CulturalOffer> list = culturalOfferRepository.findByTypeId(id);
			for(CulturalOffer c : list) {
				System.out.println("for = " + c.getName());
			}
			if(list.size() != 0) {
				throw new Exception("CulturalOfferType with given id exist CulturalOffer");
			}else {
				this.culturalOfferTypeRepository.deleteById(id);
			}
		}
		return exists;
	}

	public CulturalOfferType save(CulturalOfferType culturalOfferType) {

		return this.culturalOfferTypeRepository.save(culturalOfferType);

	}

	public CulturalOfferType update(CulturalOfferType changedCulturalOfferType, long id) {

		CulturalOfferType found = this.culturalOfferTypeRepository.findById(id).orElse(null);

		if (found != null) {
			found.setCategory(changedCulturalOfferType.getCategory());
			found.setName(changedCulturalOfferType.getName());
			return this.culturalOfferTypeRepository.save(found);
		} else
			return null;
	}

}
