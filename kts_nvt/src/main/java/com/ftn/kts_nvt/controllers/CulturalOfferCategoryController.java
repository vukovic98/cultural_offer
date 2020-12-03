package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.CulturalOfferCategoryDTO;
import com.ftn.kts_nvt.dto.GradeDTO;
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

    /*
     * GET
     * http://localhost:8080/cultural-offer-categories
     * */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CulturalOfferCategoryDTO>> getAll() {
        List<CulturalOfferCategory> list = service.findAll();
        return new ResponseEntity<>(mapper.toDTOList(list), HttpStatus.OK);
    }
    
    /*
     * GET
     * http://localhost:8080/cultural-offer-categories/by-page
     * */
 	@GetMapping(value = "/by-page/{pageNum}")
 	public ResponseEntity<Page<CulturalOfferCategoryDTO>> getAll(@PathVariable int pageNum) {
 		Pageable pageable = PageRequest.of(pageNum-1, 10);
 		Page<CulturalOfferCategory> page = service.findAll(pageable);
 		List<CulturalOfferCategoryDTO> offerDTOS = mapper.toDTOList(page.toList());
 		Page<CulturalOfferCategoryDTO> pageOfferDTOS = new PageImpl<>(offerDTOS ,
										 				page.getPageable(), 
										 				page.getTotalElements());

 		return new ResponseEntity<>(pageOfferDTOS, HttpStatus.OK);
 	}
 	
    /*
     * GET
     * http://localhost:8080/cultural-offer-categories/2
     * */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<CulturalOfferCategoryDTO> getCategory(@PathVariable Long id){
        CulturalOfferCategory category = service.findOne(id);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mapper.toDto(category), HttpStatus.OK);
    }
    
    /*
     * GET
     * http://localhost:8080/cultural-offer-categories/name/categoryname
     * */
    @RequestMapping(value="/name/{name}", method=RequestMethod.GET)
    public ResponseEntity<CulturalOfferCategoryDTO> getCategoryByName(@PathVariable String name){
        CulturalOfferCategory category = service.findByName(name);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mapper.toDto(category), HttpStatus.OK);
    }
    
    /*
     * POST
     * http://localhost:8080/cultural-offer-categories
     * {
    	"name": "categoryname" 
	   }*/
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

    /*
     * PUT
     * http://localhost:8080/cultural-offer-categories/2
     * {
	     "name": "newcategoryname" 
	   }*/
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

    /*
     * DELETE
     * http://localhost:8080/cultural-offer-categories/2
     * */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        try {
            service.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
