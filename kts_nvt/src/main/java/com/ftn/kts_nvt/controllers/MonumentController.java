package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.Monument;
import com.ftn.kts_nvt.dto.MonumentDTO;
import com.ftn.kts_nvt.helper.MonumentMapper;
import com.ftn.kts_nvt.services.MonumentService;

@RestController
@RequestMapping("/monuments")
public class MonumentController {

	@Autowired
	private MonumentService monumentService;
	
	private MonumentMapper monumentMapper;
	
    public MonumentController() {
    	monumentMapper = new MonumentMapper();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MonumentDTO>> getAllMonuments() {
        List<Monument> monuments = monumentService.findAll();
    		
		if(!monuments.isEmpty()) {
			return new ResponseEntity<>(toMonumentDTOList(monuments), HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
		
    }
    
    @GetMapping(path="/{id}")
	public ResponseEntity<MonumentDTO> findById(@PathVariable("id") long id) {
    	Monument monument = this.monumentService.findOne(id);
    	if(monument == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monumentMapper.toDto(monument), HttpStatus.OK);
        
	}
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MonumentDTO> create(@RequestBody MonumentDTO dto) {
		Monument monument;
        try {
        	monument = monumentService.create(monumentMapper.toEntity(dto));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(monumentMapper.toDto(monument), HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonumentDTO> updateMonument(@RequestBody MonumentDTO monumentDTO,
    												@PathVariable Long id){
        Monument monument;
        try {
            monument = monumentService.update(monumentMapper.toEntity(monumentDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(monumentMapper.toDto(monument), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMonument(@PathVariable Long id){
        try {
            monumentService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    //---------------------------------------------------------------------
    
    private List<MonumentDTO> toMonumentDTOList(List<Monument> monuments){
        List<MonumentDTO> monumentDTOS = new ArrayList<>();
        for (Monument monument: monuments) {
        	monumentDTOS.add(monumentMapper.toDto(monument));
        }
        return monumentDTOS;
    }
}
