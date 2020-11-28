package com.ftn.kts_nvt.helper;

import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		return null;
	}

}
