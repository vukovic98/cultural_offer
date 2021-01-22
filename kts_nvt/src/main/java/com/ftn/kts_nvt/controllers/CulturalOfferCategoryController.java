package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.dto.CulturalOfferCategoryDTO;
import com.ftn.kts_nvt.dto.CulturalOfferTypeDTO;
import com.ftn.kts_nvt.helper.CulturalOfferCategoryMapper;
import com.ftn.kts_nvt.helper.CulturalOfferTypeMapper;
import com.ftn.kts_nvt.helper.PageImplMapper;
import com.ftn.kts_nvt.helper.PageImplementation;
import com.ftn.kts_nvt.services.CulturalOfferCategoryService;
import com.ftn.kts_nvt.services.CulturalOfferTypeService;

@RestController
@RequestMapping("/cultural-offer-categories")
public class CulturalOfferCategoryController {

	@Autowired
	private CulturalOfferCategoryService service;

	//@Autowired
	//private CulturalOfferTypeService typeService;

	@Autowired
	private CulturalOfferCategoryMapper mapper;

	@Autowired
	private CulturalOfferTypeMapper typeMapper;

	/*
	 * GET http://localhost:8080/cultural-offer-categories
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CulturalOfferCategoryDTO>> getAll() {
		List<CulturalOfferCategory> list = service.findAll();
		return new ResponseEntity<>(mapper.toDTOList(list), HttpStatus.OK);
	}

	/*
	 * GET http://localhost:8080/cultural-offer-categories/by-page
	 */
	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<CulturalOfferCategoryDTO>> getAll(@PathVariable int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, 10);
		Page<CulturalOfferCategory> page = service.findAll(pageable);
		List<CulturalOfferCategoryDTO> offerDTOS = mapper.toDTOList(page.toList());
		Page<CulturalOfferCategoryDTO> pageOfferDTOS = new PageImpl<>(offerDTOS, page.getPageable(),
				page.getTotalElements());
		PageImplMapper<CulturalOfferCategoryDTO> pageMapper = new PageImplMapper<>();
		PageImplementation<CulturalOfferCategoryDTO> pageImpl = pageMapper.toPageImpl(pageOfferDTOS);

		return new ResponseEntity<>(pageImpl, HttpStatus.OK);
	}

	/*
	 * GET http://localhost:8080/cultural-offer-categories/2
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CulturalOfferCategoryDTO> getCategory(@PathVariable Long id) {
		CulturalOfferCategory category = service.findOne(id);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(mapper.toDto(category), HttpStatus.OK);
	}

	/*
	 * GET http://localhost:8080/cultural-offer-categories/name/categoryname
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<CulturalOfferCategoryDTO> getCategoryByName(@PathVariable String name) {
		CulturalOfferCategory category = service.findByName(name);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println("got category by name = " + category.getName());
		return new ResponseEntity<>(mapper.toDto(category), HttpStatus.OK);
	}

	/*
	 * POST http://localhost:8080/cultural-offer-categories { "name": "categoryname"
	 * }
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CulturalOfferCategoryDTO> create(@Valid @RequestBody CulturalOfferCategoryDTO categoryDTO)
			throws Exception {
		CulturalOfferCategory category = mapper.toEntityNoTypes(categoryDTO);
		System.out.println("adding category = " + category);
		CulturalOfferCategory exist = service.findByName(category.getName());
		if(exist != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		CulturalOfferCategory saved = service.save(category);
		return new ResponseEntity<>(mapper.toDtoNoTypes(saved) ,HttpStatus.CREATED);
	}
	/*public ResponseEntity<CulturalOfferCategoryDTO> create(@Valid @RequestBody CulturalOfferCategoryDTO categoryDTO)
			throws Exception {
		CulturalOfferCategory category, saved;
		try {
			category = mapper.toEntityNoTypes(categoryDTO);
			saved = this.service.create(category);
						
			for (CulturalOfferTypeDTO t : categoryDTO.getTypes()) {
				t.setCategoryId(saved.getId());
			}

			List<CulturalOfferType> types = this.typeMapper.dtoToList(categoryDTO.getTypes());
			
			ArrayList<CulturalOfferType> savedTypes = new ArrayList<>();
			for (CulturalOfferType t : types) {
				savedTypes.add(this.typeService.save(t));
			}
			saved.setTypes(savedTypes);
			this.service.save(saved);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(mapper.toDto(saved) ,HttpStatus.CREATED);
	}*/

	/*
	 * PUT http://localhost:8080/cultural-offer-categories/2 { "name":
	 * "newcategoryname" }
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CulturalOfferCategoryDTO> update(@Valid @RequestBody CulturalOfferCategoryDTO categoryDTO,
			@PathVariable Long id) {
		CulturalOfferCategory category = new CulturalOfferCategory(id, categoryDTO.getName(), null);
		try {
			category = service.update(category, id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(this.mapper.toDto(category), HttpStatus.OK);
	}

	/*
	 * DELETE http://localhost:8080/cultural-offer-categories/2
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>("OK",HttpStatus.OK);
	}

}
