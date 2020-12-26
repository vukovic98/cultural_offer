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

import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.repositories.VerificationCodeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class VerificationCodeRepositoryIntegrationTest {

	@Autowired
	private VerificationCodeRepository codeRepository;
	
	@Test
	public void testFindByUserId() {
		
		long userId = 1L;
		
		VerificationCode found = codeRepository.findByUserId(userId);
		
		assertNotNull(found);
		assertEquals(userId, found.getUser().getId());
		
	}
	
}
