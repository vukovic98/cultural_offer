package com.ftn.kts_nvt.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.CulturalOfferType;

@Repository
public interface CulturalOfferRepository extends JpaRepository<CulturalOffer, Long> {

	 public ArrayList<CulturalOffer> findByTypeId(Long typeId);
}
