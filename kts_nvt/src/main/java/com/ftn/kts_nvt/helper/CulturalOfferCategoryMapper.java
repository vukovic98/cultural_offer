package com.ftn.kts_nvt.helper;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.dto.CulturalOfferCategoryDTO;

public class CulturalOfferCategoryMapper implements MapperInterface<CulturalOfferCategory, CulturalOfferCategoryDTO>{

	@Override
	public CulturalOfferCategory toEntity(CulturalOfferCategoryDTO dto) {
		return new CulturalOfferCategory(dto.getId(), dto.getName(), dto.getTypes());
	}

	@Override
	public CulturalOfferCategoryDTO toDto(CulturalOfferCategory entity) {
		return new CulturalOfferCategoryDTO(entity.getId(), entity.getName(), entity.getTypes());
	}

}
