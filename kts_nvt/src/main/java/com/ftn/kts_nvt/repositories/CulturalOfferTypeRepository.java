package com.ftn.kts_nvt.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.CulturalOfferType;

@Repository
public interface CulturalOfferTypeRepository extends JpaRepository<CulturalOfferType, Long> {

   public ArrayList<CulturalOfferType> findByCategoryId(Long categoryId);
   public CulturalOfferType findByName(String name);
	
}
