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

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.dto.CulturalOfferCategoryDTO;
import com.ftn.kts_nvt.helper.CulturalOfferCategoryMapper;
import com.ftn.kts_nvt.services.CulturalOfferCategoryService;

@RestController
@RequestMapping("/cultural-offer-categories")
public class CulturalOfferCategoryController {
	
	@Autowired
	private CulturalOfferCategoryService service;
	
	private CulturalOfferCategoryMapper mapper;
	
    public CulturalOfferCategoryController() {
        mapper = new CulturalOfferCategoryMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CulturalOfferCategoryDTO>> getAll() {
        List<CulturalOfferCategory> list = service.findAll();
        return new ResponseEntity<>(toDTOList(list), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<CulturalOfferCategoryDTO> getCategory(@PathVariable Long id){
        CulturalOfferCategory category = service.findOne(id);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mapper.toDto(category), HttpStatus.OK);
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferCategoryDTO> create(@RequestBody CulturalOfferCategoryDTO categoryDTO){
        CulturalOfferCategory category;
        try {
        	category = service.create(mapper.toEntity(categoryDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(mapper.toDto(category), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferCategoryDTO> update(@RequestBody CulturalOfferCategoryDTO categoryDTO,
    															@PathVariable Long id){
        CulturalOfferCategory category;
        try {
        	category = service.update(mapper.toEntity(categoryDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(mapper.toDto(category), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        try {
            service.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    private List<CulturalOfferCategoryDTO> toDTOList(List<CulturalOfferCategory> list){
        List<CulturalOfferCategoryDTO> categoryDTOS = new ArrayList<>();
        for (CulturalOfferCategory c: list) {
        	categoryDTOS.add(mapper.toDto(c));
        }
        return categoryDTOS;
    }
}
