package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.dto.CulturalOfferCategoryDTO;
import com.ftn.kts_nvt.dto.CulturalOfferTypeDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.helper.CulturalOfferCategoryMapper;
import com.ftn.kts_nvt.services.CulturalOfferCategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferCategoryControllerIntegrationTest {

	private String accessToken;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CulturalOfferCategoryService categoryService;

	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
		ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in", dto,
				UserTokenStateDTO.class);

		accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
	}

	@Test
	public void testGetAll() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<List<CulturalOfferCategoryDTO>> responseEntity = restTemplate.exchange(
				"/cultural-offer-categories", HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<CulturalOfferCategoryDTO>>() {
				});
		List<CulturalOfferCategoryDTO> categories = responseEntity.getBody();
		int categoriesSize = this.categoryService.findAll().size();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(categories);
		assertEquals(categoriesSize, categories.size());

	}

	@Test
	public void testFindOne() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange("/cultural-offer-categories/1",
				HttpMethod.GET, httpEntity, CulturalOfferCategoryDTO.class);

		CulturalOfferCategoryDTO cat = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue("Manifestation".equalsIgnoreCase(cat.getName()));
		assertNotNull(cat);
		assertEquals(1L, cat.getId());
	}
	
	@Test
	public void testFindOneFail() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange("/cultural-offer-categories/111",
				HttpMethod.GET, httpEntity, CulturalOfferCategoryDTO.class);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

	}

	@Test
	public void testFindByName() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

		String name = "Institution";
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange(
				"/cultural-offer-categories/name/" + name, HttpMethod.GET, httpEntity, CulturalOfferCategoryDTO.class);

		CulturalOfferCategoryDTO cat = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(name.equalsIgnoreCase(cat.getName()));
		assertNotNull(cat);
		assertEquals(2L, cat.getId());
	}
	
	@Test
	public void testFindByNameFail() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

		String name = "Ivana";
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange(
				"/cultural-offer-categories/name/" + name, HttpMethod.GET, httpEntity, CulturalOfferCategoryDTO.class);


		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	@Transactional
	public void testCreateAndDelete() {

		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		List<CulturalOfferTypeDTO> types = new ArrayList<>();
		CulturalOfferCategoryDTO category = new CulturalOfferCategoryDTO(3L, "New category", types);

		HttpEntity<CulturalOfferCategoryDTO> httpEntity = new HttpEntity<>(category,headers);
		
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange("/cultural-offer-categories", HttpMethod.POST, httpEntity, CulturalOfferCategoryDTO.class);
		
		CulturalOfferCategoryDTO created = responseEntity.getBody();
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(created);
		assertTrue(category.getName().equalsIgnoreCase(created.getName()));
		
		//removing
		HttpEntity<Object> httpEntDelete = new HttpEntity<>(headers);
		ResponseEntity<String> respEntDelete = restTemplate.exchange("/cultural-offer-categories/"+created.getId(),HttpMethod.DELETE,httpEntDelete,String.class);
		
		assertEquals(HttpStatus.OK, respEntDelete.getStatusCode());
	}
	

	@Test
	@Transactional
	public void testCreateFail() {

		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		List<CulturalOfferTypeDTO> types = new ArrayList<>();
		CulturalOfferCategoryDTO category = new CulturalOfferCategoryDTO(3L, "Institution", types);

		HttpEntity<CulturalOfferCategoryDTO> httpEntity = new HttpEntity<>(category,headers);
		
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange("/cultural-offer-categories", HttpMethod.POST, httpEntity, CulturalOfferCategoryDTO.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	
	@Test
	@Transactional
	public void testUpdate() {

		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		CulturalOfferCategory c = this.categoryService.findByName("Institution");
		CulturalOfferCategoryMapper mapper = new CulturalOfferCategoryMapper();
		String oldName = c.getName();
		c.setName("Changed category");
		
		CulturalOfferCategoryDTO category = mapper.toDto(c);
		
		HttpEntity<CulturalOfferCategoryDTO> httpEntity = new HttpEntity<>(category,headers);
		
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange("/cultural-offer-categories/"+category.getId(), HttpMethod.PUT, httpEntity, CulturalOfferCategoryDTO.class);
		
		CulturalOfferCategoryDTO updated = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(updated);
		assertTrue(category.getName().equalsIgnoreCase(updated.getName()));
		
		category.setName(oldName);
		//vracanje
		httpEntity = new HttpEntity<>(category,headers);
		ResponseEntity<CulturalOfferCategoryDTO> respEntDelete = restTemplate.exchange("/cultural-offer-categories/"+category.getId(),HttpMethod.PUT,httpEntity,CulturalOfferCategoryDTO.class);
		CulturalOfferCategoryDTO oldCat = respEntDelete.getBody();
		assertEquals(HttpStatus.OK, respEntDelete.getStatusCode());
		assertTrue(oldName.equalsIgnoreCase(oldCat.getName()));
	}
	
	@Test
	@Transactional
	public void testUpdateFail() {

		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
	
		CulturalOfferCategoryDTO category = new CulturalOfferCategoryDTO(333L, "Ivana", null);
		
		HttpEntity<CulturalOfferCategoryDTO> httpEntity = new HttpEntity<>(category,headers);
		
		ResponseEntity<CulturalOfferCategoryDTO> responseEntity = restTemplate.exchange("/cultural-offer-categories/"+category.getId(), HttpMethod.PUT, httpEntity, CulturalOfferCategoryDTO.class);
		
		
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		
	}
}
