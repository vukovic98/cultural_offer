package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.dto.CulturalOfferCategoryDTO;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.dto.GradeDTO;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.helper.PageImplementation;
import com.ftn.kts_nvt.services.CulturalOfferService;
import com.ftn.kts_nvt.services.GradeService;
import com.ftn.kts_nvt.services.RegisteredUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GradeControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	 
	@Autowired
	private GradeService service;
	 	 
	@Autowired
	private RegisteredUserService userService;
	
	@Autowired
	private CulturalOfferService offerService;
	
	private String accessToken;
	
	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
	    ResponseEntity<UserTokenStateDTO> responseEntity =
	        		restTemplate.postForEntity("/auth/log-in",
	                dto, UserTokenStateDTO.class);
	        
	    accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
	}
	
	@Test
	public void a_testFindAll() {
		login("a@a", "vukovic");		//ROLE_USER
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", this.accessToken); 
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		
		ResponseEntity<ArrayList<GradeDTO>> responseEntity = 
				restTemplate.exchange("/grades",
										HttpMethod.GET,
										httpEntity,
										new ParameterizedTypeReference<ArrayList<GradeDTO>>() {});
     
		ArrayList<GradeDTO> grades = responseEntity.getBody();
    		
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals(4, grades.size());
	}
	
	@Test
	public void b_testFindAllPageable() {
		login("a@a", "vukovic");	//ROLE_USER
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", this.accessToken); 
	    
	    HttpEntity<GradeDTO> httpEntity = new HttpEntity<GradeDTO>(headers);
		ResponseEntity<PageImplementation<GradeDTO>> responseEntity = 
				this.restTemplate.exchange("/grades/by-page/1",
											HttpMethod.GET,
											httpEntity,
											new ParameterizedTypeReference<PageImplementation<GradeDTO>>() {});
		
		PageImplementation<GradeDTO> grades = responseEntity.getBody();
    		
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals(4, grades.getNumberOfElements());
	    assertTrue(grades.isLast());
	}
	
	@Test
	public void c_testFindById() {		
		ResponseEntity<GradeDTO> responseEntity =
                restTemplate.exchange("/grades/1",
                						HttpMethod.GET,
                						null,
                						GradeDTO.class);
		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());

		login("a@a", "vukovic");	//ROLE_USER
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", this.accessToken); 
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		responseEntity = restTemplate.exchange("/grades/1",
                						HttpMethod.GET,
                						httpEntity,
                						GradeDTO.class);
		GradeDTO grade = responseEntity.getBody();		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(5, grade.getValue());
		assertEquals(Long.valueOf(1), grade.getId());
		assertEquals(1, grade.getCulturalOffer().getId());
		assertEquals(Long.valueOf(1), grade.getUser().getId());
		
		responseEntity = restTemplate.exchange("/grades/10",
                						HttpMethod.GET,
                						httpEntity,
                						GradeDTO.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void d_testGetGradeForUser() {
		login("a@a", "vukovic");		//ROLE_USER
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", this.accessToken); 
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		ResponseEntity<ArrayList<GradeDTO>> responseEntity = restTemplate.exchange("/grades/user/1",
							HttpMethod.GET,
							httpEntity,
							new ParameterizedTypeReference<ArrayList<GradeDTO>>() {});
		
		ArrayList<GradeDTO> grades = responseEntity.getBody();		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, grades.size());
		
		responseEntity = restTemplate.exchange("/grades/user/10",
							HttpMethod.GET,
							httpEntity,
							new ParameterizedTypeReference<ArrayList<GradeDTO>>() {});
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void e_testGetGradeForOffer() {
		login("a@a", "vukovic");		//ROLE_USER
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", this.accessToken); 
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		ResponseEntity<ArrayList<GradeDTO>> responseEntity = restTemplate.exchange("/grades/offer/1",
							HttpMethod.GET,
							httpEntity,
							new ParameterizedTypeReference<ArrayList<GradeDTO>>() {});
		
		ArrayList<GradeDTO> grades = responseEntity.getBody();		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(3, grades.size());
		
		responseEntity = restTemplate.exchange("/grades/offer/10",
							HttpMethod.GET,
							httpEntity,
							new ParameterizedTypeReference<ArrayList<GradeDTO>>() {});
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void x_testCreateAndDelete() {
		int sizeBefore = service.findAll().size();
		System.out.println("sizebefore = " + sizeBefore);
		login("a@a", "vukovic");	//ROLE_USER
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", this.accessToken); 
		
		RegisteredUser user = this.userService.findOne(4L);
		
		CulturalOfferDTO offer = new CulturalOfferDTO();
		offer.setId(2L);
		GradeDTO grade = new GradeDTO();
		grade.setValue(5);
		grade.setUser(new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword()));
		grade.setCulturalOffer(offer);
		
		HttpEntity<GradeDTO> httpEntity = new HttpEntity<>(grade, headers);
        
		ResponseEntity<GradeDTO> responseEntity =
                restTemplate.postForEntity("/grades",
                							httpEntity, 
                							GradeDTO.class);
		GradeDTO createdGrade = responseEntity.getBody();
		System.out.println("cretedGrade id = " + createdGrade.getId());
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(createdGrade);
		assertEquals(5, createdGrade.getValue());
		int sizeAfter = service.findAll().size();
		System.out.println("sizeAfter = " + sizeAfter);
		assertEquals(sizeBefore+1, sizeAfter);
		
		HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);
	    ResponseEntity<?> responseEntityDelete =
	                restTemplate.exchange("/grades/"+createdGrade.getId(),
	                						HttpMethod.DELETE,
	                						httpEntityDelete,
	                						Object.class);
	    
	    assertEquals(HttpStatus.OK, responseEntityDelete.getStatusCode());
	    int sizeAfterDelete = service.findAll().size();
	    assertEquals(sizeBefore, sizeAfterDelete);
	     
	    Grade d = service.findOne(createdGrade.getId());
	    assertNull(d);
	}
	
	@Test
	public void f_testUpdate() {
		login("a@a", "vukovic");		//ROLE_USER
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", this.accessToken); 
	    
	    RegisteredUser user = this.userService.findOne(1L);
	
		CulturalOfferDTO offer = new CulturalOfferDTO();
		offer.setId(1L);
		GradeDTO grade = new GradeDTO();
		grade.setId(1L);
		grade.setValue(1);
		grade.setUser(new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword()));
		grade.setCulturalOffer(offer);
		
		HttpEntity<GradeDTO> httpEntity = new HttpEntity<>(grade, headers);
        
		ResponseEntity<GradeDTO> responseEntity =
	                restTemplate.exchange("/grades/"+grade.getId(),
	                						HttpMethod.PUT,
	                						httpEntity,
	                						GradeDTO.class);
		GradeDTO changedGrade = responseEntity.getBody();
		//System.out.println("changed = " + changedGrade.getValue());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1, changedGrade.getValue());
		assertEquals(1, changedGrade.getCulturalOffer().getId());
		assertEquals(Long.valueOf(1), changedGrade.getUser().getId());
		
		//rollback
		grade.setValue(5);
		httpEntity = new HttpEntity<>(grade, headers);
		responseEntity = restTemplate.exchange("/grades/"+grade.getId(),
	                						HttpMethod.PUT,
	                						httpEntity,
	                						GradeDTO.class);
		//id error 
		responseEntity = restTemplate.exchange("/grades/10",
                						HttpMethod.PUT,
                						httpEntity,
                						GradeDTO.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
}
