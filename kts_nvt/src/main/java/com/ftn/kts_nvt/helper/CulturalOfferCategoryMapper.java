package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.dto.CulturalOfferCategoryDTO;

@Component
public class CulturalOfferCategoryMapper implements MapperInterface<CulturalOfferCategory, CulturalOfferCategoryDTO>{

	@Override
	public CulturalOfferCategory toEntity(CulturalOfferCategoryDTO dto) {
		return new CulturalOfferCategory(dto.getId(), dto.getName(), dto.getTypes());
	}

	@Override
	public CulturalOfferCategoryDTO toDto(CulturalOfferCategory entity) {
		return new CulturalOfferCategoryDTO(entity.getId(), entity.getName(), entity.getTypes());
	}
	
	public List<CulturalOfferCategoryDTO> toDTOList(List<CulturalOfferCategory> list){
        List<CulturalOfferCategoryDTO> categoryDTOS = new ArrayList<>();
        for (CulturalOfferCategory c: list) {
        	categoryDTOS.add(toDto(c));
        }
        return categoryDTOS;
	}
}
