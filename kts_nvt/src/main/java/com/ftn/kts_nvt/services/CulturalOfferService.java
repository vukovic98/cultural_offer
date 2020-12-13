package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;

@Service
public class CulturalOfferService {

	@Autowired
	private CulturalOfferRepository culturalOfferRepository;

	public CulturalOffer save(CulturalOffer c) {
		return this.culturalOfferRepository.save(c);
	}

	public ArrayList<CulturalOffer> findAll() {
		return (ArrayList<CulturalOffer>) this.culturalOfferRepository.findAll();
	}

	public Page<CulturalOffer> findAll(Pageable pageable) {
		return (Page<CulturalOffer>) this.culturalOfferRepository.findAll(pageable);
	}

	public CulturalOffer findById(long id) {
		Optional<CulturalOffer> found = this.culturalOfferRepository.findById(id);

		if (found.isPresent()) {
			return found.get();
		} else
			return null;
	}

	public boolean delete(CulturalOffer c) {
		boolean exists = this.culturalOfferRepository.existsById(c.getId());

		if (exists)
			this.culturalOfferRepository.delete(c);

		return exists;
	}

	public boolean deleteById(long id) {
		boolean exists = this.culturalOfferRepository.existsById(id);

		if (exists)
			this.culturalOfferRepository.deleteById(id);

		return exists;
	}

	public CulturalOffer update(CulturalOffer changedOffer, long id) {
		Optional<CulturalOffer> foundOptional = this.culturalOfferRepository.findById(id);

		if (foundOptional.isPresent()) {
			CulturalOffer found = foundOptional.get();
			found.setName(changedOffer.getName());
			found.setDescription(changedOffer.getDescription());
			found.setImages(changedOffer.getImages());
			found.setLocation(changedOffer.getLocation());

			return this.culturalOfferRepository.save(found);
		} else
			return null;
	}

	public Page<CulturalOffer> filter(Pageable pageRequest, String exp) {
		return this.culturalOfferRepository.filter(pageRequest, exp);
	}
}
