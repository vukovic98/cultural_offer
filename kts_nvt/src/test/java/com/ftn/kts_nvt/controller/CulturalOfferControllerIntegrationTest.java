package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.dto.CulturalOfferAddDTO;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.dto.CulturalOfferDetailsDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.helper.PageImplementation;
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
        assertEquals(4, offers.size());
	}
	
	@Test
	public void testFindAllPageable() {
		ResponseEntity<PageImplementation<CulturalOfferDTO>> responseEntity = 
				this.restTemplate.exchange("/culturalOffers/by-page/1", HttpMethod.GET, null,
						new ParameterizedTypeReference<PageImplementation<CulturalOfferDTO>>() {
						});
		
		PageImplementation<CulturalOfferDTO> offers = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(4, offers.getNumberOfElements());
        assertTrue(offers.isLast());
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
	                restTemplate.getForEntity("/culturalOffers/999",
	                        CulturalOfferDTO.class);
		 
		 assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void testCreateAndDelete() {
		login("vlado@gmail.com", "vukovic");
		
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		
		CulturalOfferAddDTO dto = new CulturalOfferAddDTO("C_O", "description", 1L, g, null);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);
        
        HttpEntity<CulturalOfferAddDTO> httpEntity = new HttpEntity<>(dto, headers);
        
		
		ResponseEntity<CulturalOffer> responseEntity =
                restTemplate.postForEntity("/culturalOffers", httpEntity, CulturalOffer.class);
		
		CulturalOffer created = responseEntity.getBody();
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(created);
		
		HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);
		
		ResponseEntity<?> responseEntityDelete =
                restTemplate.exchange("/culturalOffers/"+created.getId(), HttpMethod.DELETE,
                		httpEntityDelete, Object.class);
		
		assertEquals(HttpStatus.OK, responseEntityDelete.getStatusCode());
		
	}
	
	@Test
	public void testFindDetails() {
		ResponseEntity<CulturalOfferDetailsDTO> responseEntity =
                restTemplate.getForEntity("/culturalOffers/detail/2",
                		CulturalOfferDetailsDTO.class);
		
		CulturalOfferDetailsDTO dto = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, dto.getId());
		assertTrue("Name2".equalsIgnoreCase(dto.getName()));
	}
	
	@Test
	public void testUpdate() {
		login("vlado@gmail.com", "vukovic");
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		CulturalOfferDTO dto = new CulturalOfferDTO(0, "Offer", null, g, "Desc", 3, 0);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);
        
        HttpEntity<CulturalOfferDTO> httpEntity = new HttpEntity<>(dto, headers);
        
		ResponseEntity<CulturalOfferDTO> responseEntity =
                restTemplate.exchange("/culturalOffers/1", HttpMethod.PUT,
                        httpEntity,
                        CulturalOfferDTO.class);
		
		CulturalOfferDTO changed = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertTrue("Offer".equalsIgnoreCase(changed.getName()));
		
		dto = new CulturalOfferDTO(0, "Name1", null, g, "Description", 3, 0);
        
        httpEntity = new HttpEntity<>(dto, headers);
        
		responseEntity =
                restTemplate.exchange("/culturalOffers/1", HttpMethod.PUT,
                        httpEntity,
                        CulturalOfferDTO.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
