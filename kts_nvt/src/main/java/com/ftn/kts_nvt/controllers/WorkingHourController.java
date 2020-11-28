package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.WorkingHour;
import com.ftn.kts_nvt.dto.WorkingHourDTO;
import com.ftn.kts_nvt.helper.WorkingHourMapper;
import com.ftn.kts_nvt.services.WorkingHourService;

@RestController
@RequestMapping("/workingHours")
public class WorkingHourController {

	@Autowired
	private WorkingHourService workingHourService;
	
    private WorkingHourMapper workingHourMapper;
    
    public WorkingHourController() {
    	workingHourMapper = new WorkingHourMapper();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<WorkingHourDTO>> getAll() {
        List<WorkingHour> workingHours = workingHourService.findAll();       
        return new ResponseEntity<>(toWorkingHourDTOList(workingHours), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<WorkingHourDTO> getWorkingHour(@PathVariable Long id){
        WorkingHour workingHour = workingHourService.findOne(id);
        if(workingHour == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(workingHourMapper.toDto(workingHour), HttpStatus.OK);
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> create(@RequestBody WorkingHourDTO workingHourDTO){
        WorkingHour workingHour;
        try {
        	workingHour = workingHourService.create(workingHourMapper.toEntity(workingHourDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(workingHourMapper.toDto(workingHour), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> update(@RequestBody WorkingHourDTO workingHoursDTO,
    												@PathVariable Long id){
        WorkingHour workingHour;
        try {
        	workingHour = workingHourService.update(workingHourMapper.toEntity(workingHoursDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(workingHourMapper.toDto(workingHour), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            workingHourService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    private List<WorkingHourDTO> toWorkingHourDTOList(List<WorkingHour> workingHours){
        List<WorkingHourDTO> workingHourDTOS = new ArrayList<>();
        for (WorkingHour w: workingHours) {
        	workingHourDTOS.add(workingHourMapper.toDto(w));
        }
        return workingHourDTOS;
    }
    
}
