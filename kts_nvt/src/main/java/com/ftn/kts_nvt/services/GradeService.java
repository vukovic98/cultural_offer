package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.GradeRepository;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;

@Service
public class GradeService implements ServiceInterface<Grade>{

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private CulturalOfferRepository offerRepository;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
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
			CulturalOffer offer = this.offerRepository.findById(entity.getCulturalOffer().getId()).orElse(null);
			RegisteredUser user = this.userRepository.findById(entity.getUser().getId()).orElse(null);
			
			
			gradeRepository.save(entity);
			
			offer.getGrades().add(entity);
			this.offerRepository.save(offer);
			
			return entity;
		}else {
			grade.setValue(entity.getValue());
			
			this.gradeRepository.save(grade);
			
			return grade;
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
		//System.out.println("delete with id = " + id);
		
		Grade existingGrade = gradeRepository.findById(id).orElse(null);
		//System.out.println("existinggrade = " + existingGrade);
		if (existingGrade == null) {
			throw new Exception("Grade with given id doesn't exist");
		}
		CulturalOffer offer = offerRepository.findById(existingGrade.getCulturalOffer().getId()).orElse(null);
		//System.out.println("offerbycomment = " + offer.getId());
		offer.getGrades().removeIf(obj -> obj.getId() == id);

		offerRepository.save(offer);
		gradeRepository.deleteById(id);
	}
}
