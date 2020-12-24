package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.dto.CulturalOfferAddDTO;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.repositories.GeoLocationRepository;
import com.ftn.kts_nvt.services.CulturalOfferService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferControllerIntegrationTest {

	private String accessToken;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private CulturalOfferService culturalOfferService;
	
	@Autowired
	private GeoLocationRepository geoRepository;
	
	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
    }
	
	@Test
	public void testFindAll() {
		ResponseEntity<ArrayList<CulturalOfferDTO>> responseEntity = 
				this.restTemplate.exchange("/culturalOffers", HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<CulturalOfferDTO>>() {
						});
		ArrayList<CulturalOfferDTO> offers = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, offers.size());
        assertTrue(offers.get(0).getName().equalsIgnoreCase("Exit"));
	}
	
	@Test
	public void testFindById() {
		 ResponseEntity<CulturalOfferDTO> responseEntity =
	                restTemplate.getForEntity("/culturalOffers/1",
	                        CulturalOfferDTO.class);
		 
		 CulturalOfferDTO found = responseEntity.getBody();
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 assertEquals(1L, found.getId());
	}
	
	@Test
	public void testFindByIdFail() {
		 ResponseEntity<CulturalOfferDTO> responseEntity =
	                restTemplate.getForEntity("/culturalOffers/2",
	                        CulturalOfferDTO.class);
		 
		 assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	@Transactional
	public void testCreateAndDelete() {
		login("vlado@gmail.com", "vukovic");
		
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		
		CulturalOfferAddDTO dto = new CulturalOfferAddDTO("C_O", "description", 1L, g, null);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);
        
        HttpEntity<CulturalOfferAddDTO> httpEntity = new HttpEntity<>(dto, headers);
        
        
		int size = this.culturalOfferService.findAll().size();
		
		ResponseEntity<?> responseEntity =
                restTemplate.postForEntity("/culturalOffers", httpEntity, Object.class);
		
		HttpStatus s = responseEntity.getStatusCode();
		
		assertEquals(HttpStatus.CREATED, s);
		assertEquals(size + 1, this.culturalOfferService.findAll().size());
		
		List<CulturalOffer> created = this.culturalOfferService.findByName("C_O");
		
		assertNotNull(created);
		assertEquals(1, created.size());
		
		HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);
		
		responseEntity =
                restTemplate.exchange("/culturalOffers/"+created.get(0).getId(), HttpMethod.DELETE,
                		httpEntityDelete, Object.class);
		
		s = responseEntity.getStatusCode();
		assertEquals(HttpStatus.OK, s);
		assertEquals(size, this.culturalOfferService.findAll().size());
		
		created = this.culturalOfferService.findByName("C_O");
		assertEquals(0, created.size());
		
	}
}
