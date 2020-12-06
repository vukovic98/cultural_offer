package com.ftn.kts_nvt.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.GradeDTO;
import com.ftn.kts_nvt.helper.GradeMapper;
import com.ftn.kts_nvt.services.GradeService;

@RestController
@RequestMapping("/grades")
public class GradeController {

	@Autowired
	private GradeService gradeService;

	@Autowired
	private GradeMapper gradeMapper;


	/*
	 * GET http://localhost:8080/grades
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<GradeDTO>> getAll() {
		List<Grade> grades = gradeService.findAll();
		return new ResponseEntity<>(gradeMapper.toGradeDTOList(grades), HttpStatus.OK);
	}

	/*
	 * GET http://localhost:8080/grades/byPage/1
	 */
 	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<Page<GradeDTO>> getAll(@PathVariable int pageNum) {
		Pageable pageRequest = PageRequest.of(pageNum - 1, 10);
		Page<Grade> page = gradeService.findAll(pageRequest);
		List<GradeDTO> gradeDTOS = gradeMapper.toGradeDTOList(page.toList());
		Page<GradeDTO> pageGradeDTOS = new PageImpl<>(gradeDTOS, page.getPageable(), page.getTotalElements());

		return new ResponseEntity<>(pageGradeDTOS, HttpStatus.OK);
	}

	/*
	 * GET http://localhost:8080/grades/1
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<GradeDTO> getGrade(@PathVariable Long id) {
		Grade grade = gradeService.findOne(id);
		if (grade == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(gradeMapper.toDto(grade), HttpStatus.OK);
	}

	/*
	 * GET http://localhost:8080/grades/user/1
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<GradeDTO>> getGradeForUser(@PathVariable Long id) {
		List<Grade> grades = gradeService.findByUser(id);
		if (grades == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(gradeMapper.toGradeDTOList(grades), HttpStatus.OK);
	}

	/*
	 * GET http://localhost:8080/grades/offer/1
	 */
	@RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<GradeDTO>> getGradeForOffer(@PathVariable Long id) {
		List<Grade> grades = gradeService.findByOffer(id);
		if (grades == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(gradeMapper.toGradeDTOList(grades), HttpStatus.OK);
	}

	/*
	 * POST http://localhost:8080/grades { "value" : 5, "user" : { "id" : 1 },
	 * "culturalOffer" : { "id" : 1 } }
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeDTO> createGrade(@Valid @RequestBody GradeDTO gradeDTO) {
		System.out.println("gradeDTO = " + gradeDTO);
		Grade grade;
		try {
			grade = gradeService.create(gradeMapper.toEntity(gradeDTO));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(gradeMapper.toDto(grade), HttpStatus.CREATED);
	}

	/*
	 * PUT http://localhost:8080/grades/1 
	 * { 
	 *	  "value" : 5,
	 * 	  "user" : { 
	 * 	    "id" : 1 
	 * 	  },
	 * 	  "culturalOffer" : {
	 *      "id" : 1 
	 *    } 
	 * }
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeDTO> updateGrade(@RequestBody GradeDTO gradeDTO, @PathVariable Long id) {
		Grade grade;
		try {
			grade = gradeService.update(gradeMapper.toEntity(gradeDTO), id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(gradeMapper.toDto(grade), HttpStatus.OK);
	}

	/*
	 * DELETE http://localhost:8080/grades/1
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
		try {
			gradeService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
