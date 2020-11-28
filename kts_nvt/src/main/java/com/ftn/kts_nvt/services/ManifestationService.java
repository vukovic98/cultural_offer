package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Manifestation;
import com.ftn.kts_nvt.repositories.ManifestationRepository;

@Service
public class ManifestationService {
	
	@Autowired
	private ManifestationRepository manifestationRepository;
	
	public Manifestation save(Manifestation m) {
		return this.manifestationRepository.save(m);
	}
	
	public ArrayList<Manifestation> findAll() {
		return (ArrayList<Manifestation>) this.manifestationRepository.findAll();
	}
	
	public Manifestation findById(long id) {
		Optional<Manifestation> found = this.manifestationRepository.findById(id);
		
		if(found.isPresent()) {
			return found.get();
		} else
			return null;
	}
	
	public boolean delete(Manifestation m) {
		boolean exists = this.manifestationRepository.existsById(m.getId());
		
		if(exists)
			this.manifestationRepository.delete(m);
		
		return exists;
	}
	
	public boolean deleteById(long id) {
		boolean exists = this.manifestationRepository.existsById(id);
		
		if(exists)
			this.manifestationRepository.deleteById(id);
		
		return exists;
	}
	
	public Manifestation update(Manifestation changedManifestation, long id) {
		Optional<Manifestation> foundOptional = this.manifestationRepository.findById(id);
		
		if(foundOptional.isPresent()) {
			Manifestation found = foundOptional.get();
			
			found.setName(changedManifestation.getName());
			found.setDescription(changedManifestation.getDescription());
			found.setImages(changedManifestation.getImages());
			found.setLocation(changedManifestation.getLocation());
			found.setEndDate(changedManifestation.getEndDate());
			found.setStartDate(changedManifestation.getStartDate());
			
			return found;
		} else
			return null;
	}
}
