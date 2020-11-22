package com.ftn.kts_nvt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.kts_nvt.beans.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
