package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	private CulturalOfferMapper mapper = new CulturalOfferMapper();
	
	//GET: http://localhost:8080/culturalOffers
	@GetMapping
	public ResponseEntity<ArrayList<CulturalOfferDTO>> findAll() {
		ArrayList<CulturalOffer> offers = this.culturalOfferService.findAll();
		
		ArrayList<CulturalOfferDTO> dtos = (ArrayList<CulturalOfferDTO>) this.mapper.listToDTO(offers);
		
		if(!offers.isEmpty())
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

	//GET: http://localhost:8080/culturalOffers/by-page/{pageNum}
	@GetMapping(path="/by-page/{pageNum}")
	
	public ResponseEntity<Page<CulturalOfferDTO>> findAll(@PathVariable int pageNum) {
	
		Pageable pageRequest = PageRequest.of(pageNum-1, 10);
	
		Page<CulturalOffer> page = this.culturalOfferService.findAll(pageRequest);
		
		List<CulturalOfferDTO> offersDTOS = this.mapper.listToDTO(page.toList());
		Page<CulturalOfferDTO> pageOffersDTOS = new PageImpl<>(offersDTOS, page.getPageable(), page.getTotalElements());

		return new ResponseEntity<>(pageOffersDTOS, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<CulturalOfferDTO> findById(@PathVariable("id") long id) {
		CulturalOffer found = this.culturalOfferService.findById(id);
		
		if(found != null)
			return new ResponseEntity<>(this.mapper.toDto(found), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody CulturalOfferDTO offerDto) {
		
		CulturalOffer offer = this.mapper.toEntity(offerDto);
		
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
		
		
		CulturalOffer offer = this.mapper.toEntity(dto);
		
		CulturalOffer changed = this.culturalOfferService.update(offer, id);
		
		if(changed != null) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
