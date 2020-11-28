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

import com.ftn.kts_nvt.beans.Institution;
import com.ftn.kts_nvt.dto.InstitutionDTO;
import com.ftn.kts_nvt.helper.InstitutionMapper;
import com.ftn.kts_nvt.services.InstitutionService;

@RestController
@RequestMapping("/institution")
public class InstitutionController {

	@Autowired
	private InstitutionService institutionService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Institution> findById(@PathVariable("id") long id) {
		Institution found = this.institutionService.findById(id);

		if (found != null)
			return new ResponseEntity<Institution>(found, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping
	public ResponseEntity<ArrayList<Institution>> findAll() {
		ArrayList<Institution> institutions = this.institutionService.findAll();
		if (!institutions.isEmpty())
			return new ResponseEntity<ArrayList<Institution>>(institutions, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody InstitutionDTO institution) {
		// TODO provera poslate institucije
		InstitutionMapper mapper = new InstitutionMapper();
		
		Institution i = mapper.toEntity(institution);
		
		Institution created = this.institutionService.save(i);
		
		if (created != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> delete(@RequestBody InstitutionDTO institution) {

		Institution i = this.institutionService.findById(institution.getId());

		if (i != null) {
			boolean deleted = this.institutionService.delete(i);

			if (deleted)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		boolean deleted = this.institutionService.deleteById(id);

		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody InstitutionDTO institution) {
		// TODO provera unete institucije
		InstitutionMapper mapper = new InstitutionMapper();

		Institution i = mapper.toEntity(institution);

		Institution updatedInstitution = this.institutionService.update(i, id);

		if (updatedInstitution != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
