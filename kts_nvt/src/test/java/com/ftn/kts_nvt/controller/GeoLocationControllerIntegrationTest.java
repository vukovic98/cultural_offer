package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

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

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class GeoLocationControllerIntegrationTest {
	private String accessToken;

	@Autowired
	private TestRestTemplate restTemplate;
	
	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
    }
	
	@Test
	public void testFindById() {
		login("vlado@gmail.com","vukovic");		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		
		HttpEntity<GeoLocation> httpEntity = new HttpEntity<GeoLocation>(headers);
		
		ResponseEntity<GeoLocation> responseEntity = restTemplate.exchange("/geolocation/1", HttpMethod.GET,httpEntity,GeoLocation.class);
		
		GeoLocation location = responseEntity.getBody();
		assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
		assertEquals(1L, location.getLocationId());
	}
	
	@Test
	public void testFindAll() {
		login("vlado@gmail.com","vukovic");		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		
		HttpEntity<GeoLocation> httpEntity = new HttpEntity<GeoLocation>(headers);
		
		ResponseEntity<ArrayList<GeoLocation>> responseEntity = restTemplate.
				exchange("/geolocation", HttpMethod.GET,httpEntity, new ParameterizedTypeReference<ArrayList<GeoLocation>>() {
		});
		
		ArrayList<GeoLocation> locations = responseEntity.getBody();
		
		assertNotNull(locations);
		assertTrue(!locations.isEmpty());
		assertEquals(1, locations.size());
	}

	
	@Test
	@Transactional
	@Rollback(value = true)
	public void testCreateAndDelete() {
		login("vlado@gmail.com","vukovic");
		
		GeoLocation location = new GeoLocation(22.2, 22.2, "New Location");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		
		HttpEntity<GeoLocation> httpEntity = new HttpEntity<GeoLocation>(location, headers);
		
		ResponseEntity<GeoLocation> responseEntity = restTemplate.postForEntity("/geolocation", httpEntity, GeoLocation.class);
		
		GeoLocation created = responseEntity.getBody();
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertTrue("New Location".equalsIgnoreCase(created.getPlace()));
		
		//removing
		HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);
		
		ResponseEntity<?> responseEntityDelete =
				restTemplate.exchange("/geolocation/"+created.getLocationId(), HttpMethod.DELETE, httpEntityDelete, Object.class);
		assertEquals(HttpStatus.OK, responseEntityDelete.getStatusCode());
		
	}
	
	@Test
	public void testUpdate() {
		login("vlado@gmail.com","vukovic");
		
		GeoLocation location = new GeoLocation(23.23, 23.23, "Updated Location");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		
		HttpEntity<GeoLocation> httpEntity = new HttpEntity<GeoLocation>(location, headers);
		
		ResponseEntity<GeoLocation> responseEntity = restTemplate.exchange("/geolocation/1", HttpMethod.PUT, httpEntity, GeoLocation.class);
		
		
		GeoLocation updated = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue("Updated Location".equalsIgnoreCase(updated.getPlace()));
		
		//putting attributes back
		GeoLocation loc = new GeoLocation(23.23, 23.23, "Novi Sad");
		
		HttpEntity<GeoLocation> httpEntity2 = new HttpEntity<>(loc,headers);
		
		responseEntity = restTemplate.exchange("/geolocation/1", HttpMethod.PUT, httpEntity2,GeoLocation.class);
		updated = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(updated.getPlace().equalsIgnoreCase("Novi Sad"));
		
	}
}
