package com.ftn.kts_nvt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{
	
    List<Grade> findGradesByUser_Id(Long id);
    List<Grade> findGradesByCulturalOffer_Id(Long id);
    //@Query("select g from Grade g where g.user_id = ?1 and g.cultural_offer_id = ?2")
    Optional<Grade> findGradeByUser_IdAndCulturalOffer_Id(Long id1, Long id2);
}
