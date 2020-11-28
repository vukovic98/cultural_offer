package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.WorkingHour;
import com.ftn.kts_nvt.repositories.WorkingHourRepository;

@Service
public class WorkingHourService implements ServiceInterface<WorkingHour>{

	@Autowired
	private WorkingHourRepository repository;
	
	@Override
	public List<WorkingHour> findAll() {
        return repository.findAll();
	}

	@Override
	public WorkingHour findOne(Long id) {
        return repository.findById(id).orElse(null);
	}

	@Override
	public WorkingHour create(WorkingHour entity) throws Exception {
        return repository.save(entity);
	}

	@Override
	public WorkingHour update(WorkingHour entity, Long id) throws Exception {
		WorkingHour existing =  repository.findById(id).orElse(null);
        if(existing == null){
            throw new Exception("WorkingHour with given id doesn't exist");
        }
        existing.setDay(entity.getDay());
        existing.setStartTime(entity.getStartTime());
        existing.setEndTime(entity.getEndTime());      
        return repository.save(existing);	
	}

	@Override
	public void delete(Long id) throws Exception {
		WorkingHour existing = repository.findById(id).orElse(null);
        if(existing == null){
            throw new Exception("WorkingHour with given id doesn't exist");
        }
        repository.delete(existing);		
	}

}
