package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.GradeDTO;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.GradeRepository;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;

@Service
public class GradeService implements ServiceInterface<Grade>{

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private RegisteredUserRepository regUserRepository;
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	@Override
	public List<Grade> findAll() {
		return gradeRepository.findAll();
	}

	@Override
	public Grade findOne(Long id) {
		return gradeRepository.findById(id).orElse(null);
	}

	@Override
	public Grade create(Grade entity) throws Exception {
		System.out.println("adding grade");
		return gradeRepository.save(entity);
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
