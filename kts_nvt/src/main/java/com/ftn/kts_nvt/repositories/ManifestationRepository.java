package com.ftn.kts_nvt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.Manifestation;

@Repository
public interface ManifestationRepository extends JpaRepository<Manifestation, Long> {

}
