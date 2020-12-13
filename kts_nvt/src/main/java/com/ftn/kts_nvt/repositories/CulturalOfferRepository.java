package com.ftn.kts_nvt.repositories;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.CulturalOffer;

@Repository
public interface CulturalOfferRepository extends JpaRepository<CulturalOffer, Long> {

	 public ArrayList<CulturalOffer> findByTypeId(Long typeId);

	 @Query(value="select * from cultural_offer co " + 
	 		"inner join geo_location gl " + 
	 		"on co.geo_location_id = gl.location_id " + 
	 		"where lower(co.name) like %:exp% or lower(gl.place) like %:exp%", nativeQuery = true)
	public Page<CulturalOffer> filter(Pageable pageRequest, @Param("exp") String exp);
}
