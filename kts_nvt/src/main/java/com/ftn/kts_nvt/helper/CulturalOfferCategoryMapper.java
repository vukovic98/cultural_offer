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
		CulturalOfferTypeMapper m = new CulturalOfferTypeMapper();
		return new CulturalOfferCategory(dto.getId(), dto.getName(), m.dtoToList(dto.getTypes()));
	}

	@Override
	public CulturalOfferCategoryDTO toDto(CulturalOfferCategory entity) {
		CulturalOfferTypeMapper m = new CulturalOfferTypeMapper();
		return new CulturalOfferCategoryDTO(entity.getId(), entity.getName(), m.listToDto(entity.getTypes()));
	}
	
	public List<CulturalOfferCategoryDTO> toDTOList(List<CulturalOfferCategory> list){
        List<CulturalOfferCategoryDTO> categoryDTOS = new ArrayList<>();
        for (CulturalOfferCategory c: list) {
        	categoryDTOS.add(toDto(c));
        }
        return categoryDTOS;
	}
}
