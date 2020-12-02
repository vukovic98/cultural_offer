package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.GradeDTO;

public class GradeMapper implements MapperInterface<Grade, GradeDTO>{

	@Override
	public Grade toEntity(GradeDTO dto) {
		return new Grade(dto.getId(), dto.getValue(), dto.getUser(), dto.getCulturalOffer());
	}

	@Override
	public GradeDTO toDto(Grade entity) {
		return new GradeDTO(entity.getId(), entity.getValue(), 
				entity.getUser(), entity.getCulturalOffer());
	}

	public List<GradeDTO> toGradeDTOList(List<Grade> grades){
        List<GradeDTO> gradeDTOS = new ArrayList<>();
        for (Grade grade: grades) {
        	gradeDTOS.add(toDto(grade));
        }
        return gradeDTOS;
    }
}
