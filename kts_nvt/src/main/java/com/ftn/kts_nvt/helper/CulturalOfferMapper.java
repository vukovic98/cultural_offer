package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;

public class CulturalOfferMapper implements MapperInterface<CulturalOffer, CulturalOfferDTO> {

	@Override
	public CulturalOffer toEntity(CulturalOfferDTO dto) {
		
		CulturalOffer offer = new CulturalOffer();
		
		offer.setId(dto.getId());
		offer.setComments(new ArrayList<>());
		offer.setDescription(dto.getDescription());
		offer.setImages(dto.getImages());
		offer.setLocation(dto.getLocation());
		offer.setName(dto.getName());
		offer.setPosts(new ArrayList<>());
		offer.setSubscribedUsers(new ArrayList<>());
		
		return offer;
	}

	@Override
	public CulturalOfferDTO toDto(CulturalOffer entity) {
		CulturalOfferDTO offer = new CulturalOfferDTO();
		
		offer.setId(entity.getId());
		offer.setDescription(entity.getDescription());
		offer.setImages(entity.getImages());
		offer.setLocation(entity.getLocation());
		offer.setName(entity.getName());
		
		return offer;
	}
	
	public List<CulturalOfferDTO> listToDTO(List<CulturalOffer> offers) {
		List<CulturalOfferDTO> dtos = new ArrayList<CulturalOfferDTO>();
		
		for(CulturalOffer c : offers) {
			dtos.add(toDto(c));
		}
		
		return dtos;
	}

}
