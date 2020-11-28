package com.ftn.kts_nvt.helper;

import java.util.ArrayList;

import com.ftn.kts_nvt.beans.Monument;
import com.ftn.kts_nvt.dto.MonumentDTO;

public class MonumentMapper implements MapperInterface<Monument, MonumentDTO>{

	@Override
    public Monument toEntity(MonumentDTO dto) {
		Monument offer = new Monument();
		
		offer.setId(dto.getId());
		offer.setComments(new ArrayList<>());
		offer.setDescription(dto.getDescription());
		offer.setImages(dto.getImages());
		offer.setLocation(dto.getGeoLocation());
		offer.setName(dto.getName());
		offer.setPosts(new ArrayList<>());
		offer.setSubscribedUsers(new ArrayList<>());
		offer.setYear(dto.getYear());
		
        return offer;
    }

    @Override
    public MonumentDTO toDto(Monument entity) {
        return new MonumentDTO(entity.getId(), entity.getName(), entity.getImages(),
        		entity.getLocation(), entity.getDescription(), entity.getYear());
    }
}
