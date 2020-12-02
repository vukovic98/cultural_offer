package com.ftn.kts_nvt.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.kts_nvt.beans.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

	

	
}
