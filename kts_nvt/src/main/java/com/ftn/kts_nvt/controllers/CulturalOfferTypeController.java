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

import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.dto.CulturalOfferTypeDTO;
import com.ftn.kts_nvt.helper.CulturalOfferTypeMapper;
import com.ftn.kts_nvt.services.CulturalOfferTypeService;

@RestController
@RequestMapping("/culturalOfferTypes")
public class CulturalOfferTypeController {

	@Autowired
	private CulturalOfferTypeService culturalOfferTypeService;

	private CulturalOfferTypeMapper mapper;

	public CulturalOfferTypeController() {
		mapper = new CulturalOfferTypeMapper();
	}

	// GET http://localhost:8080/culturalOfferTypes/byCategory/{categoryId}
	@GetMapping(path = "/byCategory/{categoryId}")
	public ResponseEntity<List<CulturalOfferTypeDTO>> findAll(@PathVariable("categoryId") long categoryId) {
		ArrayList<CulturalOfferType> types = this.culturalOfferTypeService.findAll(categoryId);
		if (!types.isEmpty())
			return new ResponseEntity<List<CulturalOfferTypeDTO>>(this.mapper.listToDto(types), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// GET http://localhost:8080/culturalOfferTypes/byPage/1
	@GetMapping(path = "/byPage/{pageNum}")
	public ResponseEntity<Page<CulturalOfferTypeDTO>> findAll(@PathVariable int pageNum) {

		Pageable pageRequest = PageRequest.of(pageNum - 1, 10);

		Page<CulturalOfferType> page = this.culturalOfferTypeService.findAll(pageRequest);
		List<CulturalOfferTypeDTO> culturalOfferTypeDTOS = this.mapper.listToDto(page.toList());

		Page<CulturalOfferTypeDTO> pageCulturalOfferTypeDTOS = new PageImpl<>(culturalOfferTypeDTOS, page.getPageable(),
				page.getTotalElements());

		return new ResponseEntity<>(pageCulturalOfferTypeDTOS, HttpStatus.OK);
	}

	// GET http://localhost:8080/culturalOfferTypes/1
	@GetMapping(path = "/{id}")
	public ResponseEntity<CulturalOfferTypeDTO> findById(@PathVariable("id") long id) {
		CulturalOfferType found = this.culturalOfferTypeService.findById(id);
		if (found != null)
			return new ResponseEntity<CulturalOfferTypeDTO>(this.mapper.toDto(found), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	// POST http://localhost:8080/culturalOfferTypes
	// RequestBody CulturalOfferTypeDTO
	/*
	 * { "id":"1", "name":"Exit", "categoryId":"1", "categroyName":"Manifestation" }
	 */
	@PostMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody CulturalOfferTypeDTO dto) {
		CulturalOfferType type = this.mapper.toEntity(dto);
		CulturalOfferType saved = this.culturalOfferTypeService.save(type);
		if (saved != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	// DELETE http://localhost:8080/culturalOfferType/1
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		boolean deleted;
		try {
			deleted = this.culturalOfferTypeService.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/*
	 * DELETE http://localhost:8080/culturalOfferType RequestBody
	 * CulturalOfferTypeDTO
	 * 
	 * { "id":"1", "name":"Exit", "categoryId":"1", "categroyName":"Manifestation" }
	 */

	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteEntity(@RequestBody CulturalOfferTypeDTO dto) {
		CulturalOfferType type = this.mapper.toEntity(dto);
		boolean deleted;
		try {
			deleted = this.culturalOfferTypeService.delete(type);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// PUT http://localhost:8080/culturalOfferType/1
	// RequestBody CulturalOfferTypeDTO
	/*
	 * { "id":"1", "name":"Nishvil", "categoryId":"1", "categroyName":"Manifestation"
	 * }
	 */
	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody CulturalOfferTypeDTO dto) {
		CulturalOfferType type = this.mapper.toEntity(dto);
		CulturalOfferType changedType = this.culturalOfferTypeService.update(type, type.getId());

		if (changedType != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
