package com.ftn.kts_nvt.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.dto.CulturalOfferTypeDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;
import com.ftn.kts_nvt.services.CulturalOfferTypeService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferTypeControllerIntegrationTest {

	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 @Autowired
	 private CulturalOfferTypeService service;
	 
	 @Autowired
	 private CulturalOfferCategoryRepository categoryRepository;
	 
	 private String accessToken;
		
	 public void login(String username, String password) {
		 UserLoginDTO dto = new UserLoginDTO(username, password);
	     ResponseEntity<UserTokenStateDTO> responseEntity =
	        		restTemplate.postForEntity("/auth/log-in",
	                dto, UserTokenStateDTO.class);
	        
	     accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
	 }
	 
	 @Test
	 public void testFindAll() {
		 ResponseEntity<ArrayList<String>> responseEntity = 
					restTemplate.exchange("/cultural-offer-types/getAll",
											HttpMethod.GET, null,
											new ParameterizedTypeReference<ArrayList<String>>() {});
	     
	     ArrayList<String> types = responseEntity.getBody();
			
	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertEquals(4, types.size());
	     assertEquals("Festival", types.get(0));
	 }
	 
	 @Test
	 public void testFindAllByCategory() {	    
	     ResponseEntity<CulturalOfferTypeDTO[]> responseEntity =
	                restTemplate.getForEntity("/cultural-offer-types/byCategory/1",
	                		CulturalOfferTypeDTO[].class);

	     CulturalOfferTypeDTO[] types = responseEntity.getBody();
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertEquals(2, types.length);
	     assertEquals("Festival", types[0].getName());
	     assertEquals("Type2", types[1].getName());
	 }
	 
	 @Test
	 public void testFindById() {
		 ResponseEntity<CulturalOfferTypeDTO> responseEntity =
	                restTemplate.exchange("/cultural-offer-types/1",
	                						HttpMethod.GET,
					                		null,
					                		CulturalOfferTypeDTO.class);
		 CulturalOfferTypeDTO dto = responseEntity.getBody();
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 assertEquals(1L, dto.getId());
		 assertEquals("Festival", dto.getName());		 
	 }
	 
	 @Test
	 public void testFindByIdFail() {
		 ResponseEntity<CulturalOfferTypeDTO> responseEntity =
				 restTemplate.getForEntity("/cultural-offer-types/10",
						 CulturalOfferTypeDTO.class);
		 assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	 @Test
	 public void testCreateAndDelete() {
		 int sizeBefore = service.findAll().size();
		 
		 login("a@a", "vukovic");
		 CulturalOfferCategory category = categoryRepository.findById(2L).orElse(null);
		 
		 CulturalOfferTypeDTO typeDTO = new CulturalOfferTypeDTO();
		 typeDTO.setName("Museum");
		 typeDTO.setCategoryId(category.getId());
		 typeDTO.setCategoryName(category.getName());
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", this.accessToken);
	     HttpEntity<CulturalOfferTypeDTO> httpEntity = new HttpEntity<>(typeDTO, headers);
	     
	     ResponseEntity<CulturalOfferType> responseEntity =
	                restTemplate.postForEntity("/cultural-offer-types",
	                							httpEntity, 
	                							CulturalOfferType.class);
	     CulturalOfferType addedType = responseEntity.getBody();
	     assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	     assertNotNull(addedType);
	     int sizeAfter = service.findAll().size();
	     assertEquals(sizeBefore+1, sizeAfter);
	     
	     HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);
	     ResponseEntity<?> responseEntityDelete =
	                restTemplate.exchange("/cultural-offer-types/"+addedType.getId(),
	                						HttpMethod.DELETE,
	                						httpEntityDelete,
	                						Object.class);
			
	     assertEquals(HttpStatus.OK, responseEntityDelete.getStatusCode());
	     int sizeAfterDelete = service.findAll().size();
	     assertEquals(sizeBefore, sizeAfterDelete);
	     
	     CulturalOfferType d = service.findById(addedType.getId());
	     assertNull(d);
	 }
	 
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testUpdate() {
		login("a@a", "vukovic");
		CulturalOfferCategory category = categoryRepository.findById(2L).orElse(null);
		 
		CulturalOfferTypeDTO typeDTO = new CulturalOfferTypeDTO();
		typeDTO.setId(1L);
		typeDTO.setName("Type1");
		typeDTO.setCategoryId(category.getId());
		typeDTO.setCategoryName(category.getName());
		 
		//System.out.println("send dto = " + typeDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
	        
	    HttpEntity<CulturalOfferTypeDTO> httpEntity = new HttpEntity<>(typeDTO, headers);
	        
		ResponseEntity<CulturalOfferType> responseEntity =
	                restTemplate.exchange("/cultural-offer-types/"+typeDTO.getId(),
	                						HttpMethod.PUT,
	                						httpEntity,
						                    CulturalOfferType.class);
		
		CulturalOfferType changedType = responseEntity.getBody();
		System.out.println("changed = " + changedType.getName());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue("Type1".equalsIgnoreCase(changedType.getName()));
		
		//rollback
		category = categoryRepository.findById(1L).orElse(null);
		typeDTO.setId(1L);
		typeDTO.setName("Festival");
		typeDTO.setCategoryId(category.getId());
		typeDTO.setCategoryName(category.getName());
		httpEntity = new HttpEntity<>(typeDTO, headers);
        
		responseEntity = restTemplate.exchange("/cultural-offer-types/"+typeDTO.getId(),
	                						HttpMethod.PUT,
	                						httpEntity,
						                    CulturalOfferType.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 @Test
	 public void testDeleteFailRole() {
		 login("a2@a", "vukovic");
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", this.accessToken);
	     
		 HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);
	     ResponseEntity<?> responseEntityDelete =
	                restTemplate.exchange("/cultural-offer-types/2",
	                						HttpMethod.DELETE,
	                						httpEntityDelete,
	                						Object.class);
			
	     assertEquals(HttpStatus.FORBIDDEN, responseEntityDelete.getStatusCode());
	 }
	 
	 @Test
	 public void testDeleteFail() {
		 login("a@a", "vukovic");
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", this.accessToken);
	     
		 HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);
	     ResponseEntity<?> responseEntityDelete =
	                restTemplate.exchange("/cultural-offer-types/10",
	                						HttpMethod.DELETE,
	                						httpEntityDelete,
	                						Object.class);
			
	     assertEquals(HttpStatus.BAD_REQUEST, responseEntityDelete.getStatusCode());
	 }
}
