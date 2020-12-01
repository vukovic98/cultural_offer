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

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.GradeDTO;
import com.ftn.kts_nvt.helper.GradeMapper;
import com.ftn.kts_nvt.services.GradeService;

@RestController
@RequestMapping("/grades")
public class GradeController {

	@Autowired
	private GradeService gradeService;
	
	private GradeMapper gradeMapper;
	
    public GradeController() {
        gradeMapper = new GradeMapper();
    }

    //http://localhost:8080/grades
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GradeDTO>> getAll() {
        List<Grade> grades = gradeService.findAll();
        for(Grade u : grades) {
        	System.out.println(u);
        }
        return new ResponseEntity<>(toGradeDTOList(grades), HttpStatus.OK);
    }
    
    //http://localhost:8080/grades/1
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Long id){
        Grade grade = gradeService.findOne(id);
        if(grade == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(gradeMapper.toDto(grade), HttpStatus.OK);
    }
    
    /*
     * POST 
     * http://localhost:8080/grades
     {
	    "value" : 5,
	    "user" : {
	        "id" : 1
	    },
	    "culturalOffer" : {
	        "id" : 1
	    }
	 }*/
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO){
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
     * PUT
     * http://localhost:8080/grades/1
     * {
    	 "value" : 10
	   }
     */
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeDTO> updateGrade(@RequestBody GradeDTO gradeDTO, @PathVariable Long id){
        Grade grade;
        try {
            grade = gradeService.update(gradeMapper.toEntity(gradeDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gradeMapper.toDto(grade), HttpStatus.OK);
    }

    /*
     * DELETE
     * http://localhost:8080/grades/1
     * */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id){
        try {
            gradeService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    private List<GradeDTO> toGradeDTOList(List<Grade> grades){
        List<GradeDTO> gradeDTOS = new ArrayList<>();
        for (Grade grade: grades) {
        	gradeDTOS.add(gradeMapper.toDto(grade));
        }
        return gradeDTOS;
    }
}
