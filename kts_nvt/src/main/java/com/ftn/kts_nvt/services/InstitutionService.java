package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Institution;
import com.ftn.kts_nvt.repositories.InstitutionRepository;

@Service
public class InstitutionService {

	@Autowired
	private InstitutionRepository institutionRepository;

	public Institution save(Institution i) {
		return this.institutionRepository.save(i);
	}

	public boolean deleteById(Long id) {
		boolean exists = this.institutionRepository.existsById(id);

		if (exists) {
			this.institutionRepository.deleteById(id);
		}
		return exists;
	}

	public boolean delete(Institution i) {
		boolean exists = this.institutionRepository.existsById(i.getId());

		if (exists) {
			this.institutionRepository.delete(i);
		}
		return exists;
	}

	public ArrayList<Institution> findAll() {
		return (ArrayList<Institution>) this.institutionRepository.findAll();
	}

	public Institution findById(Long id) {
		Optional<Institution> found = this.institutionRepository.findById(id);

		if (found.isPresent()) {
			Institution institution = found.get();
			return institution;
		} else
			return null;
	}

	public Institution update(Institution changedInstitution, long id) {

		Optional<Institution> found = this.institutionRepository.findById(id);

		if (found.isPresent()) {
			Institution oldInstitution = found.get();

			oldInstitution.setDescription(changedInstitution.getDescription());
			oldInstitution.setImages(changedInstitution.getImages());
			oldInstitution.setName(changedInstitution.getName());
			oldInstitution.setLocation(changedInstitution.getLocation());
			oldInstitution.setWorkingHours(changedInstitution.getWorkingHours());

			return this.institutionRepository.save(oldInstitution);
		} else
			return null;
	}

}
