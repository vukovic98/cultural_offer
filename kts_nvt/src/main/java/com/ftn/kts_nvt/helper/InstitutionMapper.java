package com.ftn.kts_nvt.helper;

import java.util.ArrayList;

import com.ftn.kts_nvt.beans.Institution;
import com.ftn.kts_nvt.dto.InstitutionDTO;

public class InstitutionMapper implements MapperInterface<Institution, InstitutionDTO>{

	@Override
	public Institution toEntity(InstitutionDTO dto) {

		Institution institution = new Institution();
		institution.setId(dto.getId());
		institution.setComments(new ArrayList<>());
		institution.setDescription(dto.getDescription());
		institution.setImages(dto.getImages());
		institution.setLocation(dto.getGeoLocation());
		institution.setName(dto.getName());
		institution.setPosts(new ArrayList<>());
		institution.setSubscribedUsers(new ArrayList<>());
		institution.setWorkingHours(dto.getWorkingHours());
		
		return institution;
	}

	@Override
	public InstitutionDTO toDto(Institution entity) {
		return new InstitutionDTO(entity.getId(), entity.getName(), entity.getImages(), entity.getLocation(), entity.getDescription(), entity.getWorkingHours());
	}
	


}
