package com.ftn.kts_nvt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;

@Repository
public interface CulturalOfferCategoryRepository extends JpaRepository<CulturalOfferCategory, Long> {
	
    Optional<CulturalOfferCategory> findCulturalOfferCategoryByName(String name);

}
