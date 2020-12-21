package com.ftn.kts_nvt.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.CulturalOffer;

@Repository
public interface CulturalOfferRepository extends JpaRepository<CulturalOffer, Long> {

	public ArrayList<CulturalOffer> findByTypeId(Long typeId);
	
	public List<CulturalOffer> findByName(String name);

	@Query(value = "select * from cultural_offer co inner join geo_location gl on co.geo_location_id = gl.location_id where lower(co.name) like %:exp% or lower(gl.place) like %:exp%", nativeQuery = true)
	public Page<CulturalOffer> filter(Pageable pageRequest, @Param("exp") String exp);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM post WHERE offer_id = ?1", nativeQuery = true)
	public void deletePostsForOffer(Long offer_id);

	@Query(value = "select * from cultural_offer co inner join geo_location gl on co.geo_location_id = gl.location_id inner join cultural_offer_type cot on co.cultural_offer_type_id = cot.cultural_offer_type_id where (lower(co.name) like %:exp% or lower(gl.place) like %:exp%) and (cot.name in :types)", nativeQuery = true)
	public Page<CulturalOffer> filter(Pageable pageRequest, @Param("exp") String exp, @Param("types") ArrayList<String> types);

	@Query(value = "select * from cultural_offer co inner join cultural_offer_type cot on co.cultural_offer_type_id = cot.cultural_offer_type_id where cot.name in :types", nativeQuery = true)
	public Page<CulturalOffer> filter(Pageable pageRequest, @Param("types") ArrayList<String> types);
}
