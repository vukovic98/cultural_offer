package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.repositories.GradeRepository;

@Service
public class GradeService implements ServiceInterface<Grade>{

	@Autowired
	private GradeRepository gradeRepository;
	
	@Override
	public List<Grade> findAll() {
		return gradeRepository.findAll();
	}

	public Page<Grade> findAll(Pageable pagable) {
		return gradeRepository.findAll(pagable);
	}
	
	public List<Grade> findByUser(Long id){
		return gradeRepository.findGradesByUser_Id(id);
	}
	
	public List<Grade> findByOffer(Long id){
		return gradeRepository.findGradesByCulturalOffer_Id(id);
	}
	
	@Override
	public Grade findOne(Long id) {
		return gradeRepository.findById(id).orElse(null);
	}

	@Override
	public Grade create(Grade entity) throws Exception {
		Grade grade = gradeRepository.findGradeByUser_IdAndCulturalOffer_Id(entity.getUser().getId(),
				entity.getCulturalOffer().getId()).orElse(null);
		
		if(grade == null) {
			return gradeRepository.save(entity);			
		}else {
			System.out.println(grade.getUser().getId() + " " + 
					grade.getCulturalOffer().getId() + " " + 
					grade.getValue());
			throw new Exception("Grade already exist");
		}
	}
	
	@Override
	public Grade update(Grade entity, Long id) throws Exception {
		Grade existingGrade = gradeRepository.findById(id).orElse(null);
		if (existingGrade == null) {
			throw new Exception("Grade with given id doesn't exist");
		}
		existingGrade.setValue(entity.getValue());
		return gradeRepository.save(existingGrade);
	}

	@Override
	public void delete(Long id) throws Exception {
		Grade existingGrade = gradeRepository.findById(id).orElse(null);
		if (existingGrade == null) {
			throw new Exception("Grade with given id doesn't exist");
		}
		gradeRepository.delete(existingGrade);		
	}
}
