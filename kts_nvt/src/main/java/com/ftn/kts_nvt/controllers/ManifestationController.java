package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.Manifestation;
import com.ftn.kts_nvt.dto.ManifestationDTO;
import com.ftn.kts_nvt.helper.ManifestationMapper;
import com.ftn.kts_nvt.services.ManifestationService;

@RestController
@RequestMapping("/manifestation")
public class ManifestationController {

	@Autowired
	private ManifestationService manifestationService;
	
	@GetMapping
	public ResponseEntity<ArrayList<Manifestation>> findAll() {
		ArrayList<Manifestation> found = this.manifestationService.findAll();
		
		if(!found.isEmpty()) 
			return new ResponseEntity<ArrayList<Manifestation>>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Manifestation> findById(@PathVariable("id") long id) {
		Manifestation found = this.manifestationService.findById(id);
		
		if(found != null)
			return new ResponseEntity<Manifestation>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody ManifestationDTO dto) {
		ManifestationMapper mapper = new ManifestationMapper();
		
		Manifestation manifestation = mapper.toEntity(dto);
		
		Manifestation ok = this.manifestationService.save(manifestation);
		
		if(ok != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteEntity(@RequestBody ManifestationDTO dto) {
		Manifestation m = this.manifestationService.findById(dto.getId());
		
		if(m != null) {
			boolean ok = this.manifestationService.delete(m);
			
			if(ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		boolean ok = this.manifestationService.deleteById(id);
		
		if(ok)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(path="/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody ManifestationDTO dto) {
		ManifestationMapper mapper = new ManifestationMapper();
		
		Manifestation m = mapper.toEntity(dto);
		
		Manifestation ok = this.manifestationService.update(m, id);
		
		if(ok != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
