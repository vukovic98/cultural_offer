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

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.helper.CulturalOfferMapper;
import com.ftn.kts_nvt.services.CulturalOfferService;

@RestController
@RequestMapping("/culturalOffers")
public class CulturalOfferController {

	@Autowired
	private CulturalOfferService culturalOfferService;
	
	//GET: http://localhost:8080/culturalOffers
	@GetMapping
	public ResponseEntity<ArrayList<CulturalOffer>> findAll() {
		ArrayList<CulturalOffer> offers = this.culturalOfferService.findAll();
		
		if(!offers.isEmpty())
			return new ResponseEntity<ArrayList<CulturalOffer>>(offers, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<CulturalOffer> findById(@PathVariable("id") long id) {
		CulturalOffer found = this.culturalOfferService.findById(id);
		
		if(found != null)
			return new ResponseEntity<CulturalOffer>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody CulturalOfferDTO offerDto) {
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		
		CulturalOffer offer = mapper.toEntity(offerDto);
		
		CulturalOffer ok = this.culturalOfferService.save(offer);
		
		if(ok != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteEntity(@RequestBody CulturalOfferDTO dto) {
		
		CulturalOffer offer = this.culturalOfferService.findById(dto.getId());
		
		if(offer != null) {
			boolean ok = this.culturalOfferService.delete(offer);
			if(ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		boolean ok = this.culturalOfferService.deleteById(id);
		
		if(ok)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(path="/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody CulturalOfferDTO dto) {
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		
		CulturalOffer offer = mapper.toEntity(dto);
		
		CulturalOffer changed = this.culturalOfferService.update(offer, id);
		
		if(changed != null) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
