package com.ftn.kts_nvt.repository;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RegisteredUserRepositoryIntegrationTest {
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Test
	public void testFindByEmail() {
		
		String email = "a@a";
		RegisteredUser user = this.registeredUserRepository.findByEmail(email);
		
		assertEquals(user.getEmail(), email);
		assertNotNull(user);
		
	}
	
}
