package com.ftn.kts_nvt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{
	
    List<Grade> findGradesByUser_Id(Long id);
    List<Grade> findGradesByCulturalOffer_Id(Long id);

}
