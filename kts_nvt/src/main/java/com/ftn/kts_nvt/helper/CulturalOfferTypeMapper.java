package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.dto.CulturalOfferTypeDTO;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;

@Component
public class CulturalOfferTypeMapper implements MapperInterface<CulturalOfferType, CulturalOfferTypeDTO> {

	@Autowired
	private CulturalOfferCategoryRepository culturalOfferCategoryRepository;

	@Override
	public CulturalOfferType toEntity(CulturalOfferTypeDTO dto) {
		CulturalOfferType culturalOfferType = new CulturalOfferType();

		culturalOfferType.setId(dto.getId());
		culturalOfferType.setCategory(this.culturalOfferCategoryRepository.findById(dto.getCategoryId()).orElse(null));
		culturalOfferType.setName(dto.getName());

		return culturalOfferType;
	}

	@Override
	public CulturalOfferTypeDTO toDto(CulturalOfferType entity) {
		CulturalOfferTypeDTO dto = new CulturalOfferTypeDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCategoryId(entity.getCategory().getId());
		dto.setCategoryName(entity.getCategory().getName());
		return dto;
	}

	public List<CulturalOfferTypeDTO> listToDto(List<CulturalOfferType> types) {
		List<CulturalOfferTypeDTO> dtos = new ArrayList<>();
		for (CulturalOfferType t : types) {
			dtos.add(toDto(t));
		}
		return dtos;
	}
}
