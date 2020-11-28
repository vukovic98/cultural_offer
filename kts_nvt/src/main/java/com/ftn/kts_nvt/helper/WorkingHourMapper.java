package com.ftn.kts_nvt.helper;

import com.ftn.kts_nvt.beans.WorkingHour;
import com.ftn.kts_nvt.dto.WorkingHourDTO;

public class WorkingHourMapper implements MapperInterface<WorkingHour, WorkingHourDTO>{

	@Override
	public WorkingHour toEntity(WorkingHourDTO dto) {
		return new WorkingHour(dto.getId(), dto.getDay(), dto.getStartTime(), dto.getEndTime());
	}

	@Override
	public WorkingHourDTO toDto(WorkingHour entity) {
		return new WorkingHourDTO(entity.getId(), entity.getDay(), 
								  entity.getStartTime(), entity.getEndTime());
	}

}
