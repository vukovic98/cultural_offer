package com.ftn.kts_nvt.helper;

import java.util.ArrayList;

import com.ftn.kts_nvt.beans.Manifestation;
import com.ftn.kts_nvt.dto.ManifestationDTO;

public class ManifestationMapper implements MapperInterface<Manifestation, ManifestationDTO> {

	@Override
	public Manifestation toEntity(ManifestationDTO dto) {
		
		Manifestation offer = new Manifestation();
		
		offer.setId(dto.getId());
		offer.setComments(new ArrayList<>());
		offer.setDescription(dto.getDescription());
		offer.setImages(dto.getImages());
		offer.setLocation(dto.getLocation());
		offer.setName(dto.getName());
		offer.setPosts(new ArrayList<>());
		offer.setSubscribedUsers(new ArrayList<>());
		offer.setEndDate(dto.getEndDate());
		offer.setStartDate(dto.getStartDate());
		
		return offer;
	}

	@Override
	public ManifestationDTO toDto(Manifestation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
