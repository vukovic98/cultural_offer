package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.controllers.AuthenticationController.PasswordChanger;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.dto.VerificationCodeDTO;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;
import com.ftn.kts_nvt.repositories.VerificationCodeRepository;
import com.ftn.kts_nvt.security.TokenUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AuthenticationControllerIntegrationTest {

	private String accessToken;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Autowired
	private VerificationCodeRepository verificationCodeRepository;
	
	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
    }
	
	@Test
	public void loginUserTest() {
		
		UserLoginDTO dto = new UserLoginDTO("vlado@gmail.com", "vukovic");
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        assertNotNull(responseEntity.getBody().getAuthenticationToken());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("vlado@gmail.com", tokenUtils.getUsernameFromToken(responseEntity.getBody().getAuthenticationToken()));
	}
	
	@Test
	public void loginAdminTest() {
		
		UserLoginDTO dto = new UserLoginDTO("ad@ad", "madzarevic");
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        assertNotNull(responseEntity.getBody().getAuthenticationToken());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("ad@ad", tokenUtils.getUsernameFromToken(responseEntity.getBody().getAuthenticationToken()));
        
	}
	
	@Test
	public void loginWrongPasswordTest() {
		
		UserLoginDTO dto = new UserLoginDTO("a@a", "wrongpass");
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		
	}
	
	@Test
	public void loginWrongEmailTest() {
		
		UserLoginDTO dto = new UserLoginDTO("asd@aad", "vukovic");
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		
	}
	
	
	@Test
	public void testSignUpVerify() {
		
		UserDTO dto = new UserDTO(0L, "RegisterName", "RegisterLastname", "register@test.com", "testpass");
		
		ResponseEntity<?> responseEntity =
        		restTemplate.postForEntity("/auth/sign-up",
                dto, Object.class);
	
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		RegisteredUser saved = registeredUserRepository.findByEmail("register@test.com");
		
		VerificationCode code = verificationCodeRepository.findByUserId(saved.getId());
		
		VerificationCodeDTO dtoVer = new VerificationCodeDTO(code.getCode(), "register@test.com");
		ResponseEntity<?> responseEntityCode =
        		restTemplate.postForEntity("/auth/verify",
                dtoVer, Object.class);
		
		assertEquals(HttpStatus.OK, responseEntityCode.getStatusCode());
		
		RegisteredUser found = registeredUserRepository.findByEmail("register@test.com");
		assertTrue(found.isVerified());
		
		registeredUserRepository.delete(found);
		
		
		
	}
	
	@Test
	@Transactional
	public void testChangePassword() {
		login("a3@a", "vukovic");
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);
        
        PasswordChanger pc = new PasswordChanger();
        pc.oldPassword = "vukovic";
        pc.newPassword = "987654321";
		
       
        
        ResponseEntity<?> responseEntity =
        		restTemplate.exchange("/auth/change-password", HttpMethod.POST, new HttpEntity<PasswordChanger>(pc,headers), Object.class);
        
        RegisteredUser user = registeredUserRepository.findByEmail("a3@a");
        
        
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        assertTrue(enc.matches(pc.newPassword, user.getPassword()));
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        
        pc.oldPassword = "987654321";
        pc.newPassword = "vukovic";
        
        ResponseEntity<?> responseEntity2 =
        		restTemplate.exchange("/auth/change-password", HttpMethod.POST, new HttpEntity<PasswordChanger>(pc,headers), Object.class);
        
        user = registeredUserRepository.findByEmail("a@a");

        assertEquals(HttpStatus.ACCEPTED, responseEntity2.getStatusCode());
        
        
	}
	
}
