package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.GradeDTO;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.services.CulturalOfferService;
import com.ftn.kts_nvt.services.RegisteredUserService;

@Component
public class GradeMapper implements MapperInterface<Grade, GradeDTO>{

	@Autowired
	private RegisteredUserService userService;
	
	@Autowired
	private CulturalOfferService culturalOfferService;
	
	@Override
	public Grade toEntity(GradeDTO dto) {
		
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		return new Grade(dto.getId(), dto.getValue(),
						userService.findOne(dto.getUser().getId()), 
						culturalOfferService.findById(dto.getCulturalOffer().getId()));
	}

	@Override
	public GradeDTO toDto(Grade entity) {
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		return new GradeDTO(entity.getId(), entity.getValue(), 
				new UserDTO(entity.getUser().getId(), entity.getUser().getFirstName(), entity.getUser().getLastName(),
						entity.getUser().getEmail(), ""), mapper.toDto(entity.getCulturalOffer()));
	}

	public List<GradeDTO> toGradeDTOList(List<Grade> grades){
        List<GradeDTO> gradeDTOS = new ArrayList<>();
        for (Grade grade: grades) {
        	gradeDTOS.add(toDto(grade));
        }
        return gradeDTOS;
    }
}
