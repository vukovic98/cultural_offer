package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.services.GeoLocationService;

@RestController
@RequestMapping("/geolocation")
public class GeoLocationController {

	@Autowired
	private GeoLocationService geoLocationService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<GeoLocation> findById(@PathVariable("id") long id) {
		GeoLocation found = this.geoLocationService.findById(id);

		if (found != null)
			return new ResponseEntity<GeoLocation>(found, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<ArrayList<GeoLocation>> findAll() {
		ArrayList<GeoLocation> geoLocations = this.geoLocationService.findAll();

		if (!geoLocations.isEmpty())
			return new ResponseEntity<ArrayList<GeoLocation>>(geoLocations, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="/by-page")
	public ResponseEntity<Page<GeoLocation>> findAll(Pageable pageable) {
		Page<GeoLocation> page = this.geoLocationService.findAll(pageable);
		
		List<GeoLocation> locations = page.toList();
		Page<GeoLocation> pageLocations = new PageImpl<>(locations, page.getPageable(), page.getTotalElements());

		return new ResponseEntity<>(pageLocations, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody GeoLocation geoLocation) {
		//TODO provera poslate lokacije
		GeoLocation created = this.geoLocationService.save(geoLocation);
		if (created != null)
			// TODO proveriti da li su ispravni podaci poslati
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> delete(@RequestBody GeoLocation geoLocation) {
		boolean deleted = this.geoLocationService.delete(geoLocation);

		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		boolean deleted = this.geoLocationService.deleteById(id);

		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody GeoLocation geoLocation) {
		//TODO provera unete lokacije
		GeoLocation updatedGeoLocation = this.geoLocationService.update(geoLocation, id);
		if (updatedGeoLocation != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
