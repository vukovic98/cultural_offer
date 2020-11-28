package com.ftn.kts_nvt.helper;

import com.ftn.kts_nvt.beans.Institution;
import com.ftn.kts_nvt.dto.InstitutionDTO;

public class InstitutionMapper implements MapperInterface<Institution, InstitutionDTO>{

	@Override
	public Institution toEntity(InstitutionDTO dto) {
		return new Institution();
	}

	@Override
	public InstitutionDTO toDto(Institution entity) {
		// TODO Auto-generated method stub
		return new InstitutionDTO(entity.getId(), entity.getName(), entity.getImages(), entity.get, description, workingHours);
	}
	
}

}
