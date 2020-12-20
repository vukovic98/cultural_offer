package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.GradeDTO;

@Component
public class GradeMapper implements MapperInterface<Grade, GradeDTO>{

	@Override
	public Grade toEntity(GradeDTO dto) {
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		return new Grade(dto.getId(), dto.getValue(), dto.getUser(), 
				mapper.toEntity(dto.getCulturalOffer()));
	}

	@Override
	public GradeDTO toDto(Grade entity) {
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		return new GradeDTO(entity.getId(), entity.getValue(), 
				entity.getUser(), mapper.toDto(entity.getCulturalOffer()));
	}

	public List<GradeDTO> toGradeDTOList(List<Grade> grades){
        List<GradeDTO> gradeDTOS = new ArrayList<>();
        for (Grade grade: grades) {
        	gradeDTOS.add(toDto(grade));
        }
        return gradeDTOS;
    }
}
