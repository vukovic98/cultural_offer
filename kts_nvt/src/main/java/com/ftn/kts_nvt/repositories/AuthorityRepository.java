package com.ftn.kts_nvt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
