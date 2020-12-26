package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;
import com.ftn.kts_nvt.repositories.UserRepository;
import com.ftn.kts_nvt.services.RegisteredUserService;
import com.ftn.kts_nvt.services.VerificationCodeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class VerificationCodeServiceIntegrationTest {

	@Autowired
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
	@Test
	public void testFindById() {
		
		long id = 1L;
		
		VerificationCode code = verificationCodeService.findCodeById(id);
		
		assertNotNull(code);
		assertEquals(id, code.getId());
		
	}
	
	@Test
	public void testFindByUserId() {
		
		long id = 1L;
		
		VerificationCode code = verificationCodeService.findCodeByUser(id);
		
		assertNotNull(code);
		assertEquals(id, code.getUser().getId());
		
	}
	
	@Test
	@Transactional
	public void testCreateDeleteForUser() {
		
		RegisteredUser user = userRepository.findById(4L).orElse(null);
		
		VerificationCode code = new VerificationCode(user);
		
		boolean created = verificationCodeService.create(code);
		
		assertEquals(true, created);
		
		VerificationCode found = verificationCodeService.findCodeByUser(4L);
		
		assertEquals(code.getCode(), found.getCode());
		
		boolean deleted = verificationCodeService.deleteCodeForUser(1L);
		
		assertEquals(true, deleted);
		
		
	}
	
}
