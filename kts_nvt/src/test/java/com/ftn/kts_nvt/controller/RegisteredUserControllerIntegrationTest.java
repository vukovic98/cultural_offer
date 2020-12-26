package com.ftn.kts_nvt.controller;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;


import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.dto.Users;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.services.RegisteredUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RegisteredUserControllerIntegrationTest {

	private String accessToken;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Autowired
	private CulturalOfferRepository offerRepository;
	
	@Autowired
	private RegisteredUserService userService;
	
	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
        ResponseEntity<UserTokenStateDTO> responseEntity =
        		restTemplate.postForEntity("/auth/log-in",
                dto, UserTokenStateDTO.class);
        
        accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
    }
	
	@Test
	public void testFindAll() {
		login("vlado@gmail.com", "vukovic");
		System.out.println(accessToken);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);

        
		ResponseEntity<ArrayList<UserDTO>> responseEntity = 
		this.restTemplate.exchange("/registeredUser", HttpMethod.GET, new HttpEntity<>(headers),
				new ParameterizedTypeReference<ArrayList<UserDTO>>() {
				});
		
		ArrayList<UserDTO> users = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(3, users.size());
		//assertTrue(users.get(0).getEmail().equals("a@a"));
		
	}
	
	@Test
	public void testFindById() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);
		
		ResponseEntity<UserDTO> responseEntity =
				restTemplate.exchange("/registeredUser/1", HttpMethod.GET, new HttpEntity<>(headers),new ParameterizedTypeReference<UserDTO>() {
				});
		
		UserDTO found = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1L, found.getId());
	}
	
	@Test
	public void testFindByEmail() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);
		
		ResponseEntity<UserDTO> responseEntity =
				restTemplate.exchange("/registeredUser/byEmail/a2@a", HttpMethod.GET, new HttpEntity<>(headers),new ParameterizedTypeReference<UserDTO>() {
				});
		
		UserDTO found = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1L, found.getId());
		assertEquals("a2@a", found.getEmail());
	}
	
	@Test
	public void testCreateDelete() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);
        
        UserDTO u = new UserDTO(1L, "Test", "Testic", "test@test.com", "1234");
		int size = this.userService.findAll().size();
        
        HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(u,headers);
        
        
        
		ResponseEntity<?> responseEntity=
				restTemplate.postForEntity("/registeredUser", entity, UserDTO.class);
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(size + 1, this.userService.findAll().size());
		
		RegisteredUser saved = this.userService.findOneByEmail("test@test.com");
		
		responseEntity=
				restTemplate.exchange("/registeredUser/"+saved.getId(), HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(size,this.userService.findAll().size());
		
		
		
	}
	
	@Test
	@Transactional
	public void testSubscribeUnsubscribe() {
		login("vlado@gmail.com", "vukovic");
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.accessToken);

		
		int userSize = this.userService.findOneByEmail("a2@a").getCulturalOffers().size();
		int offerSize = this.offerRepository.findById(1L).orElse(null).getSubscribedUsers().size();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/registeredUser/subscribe")
				.queryParam("offer_id", 1L);
		
		ResponseEntity<?> responseEntity = 
				restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, new HttpEntity<>(headers), Object.class);
		
		RegisteredUser find = this.userService.findOneByEmail("a2@a");
		
		System.out.println(find.getCulturalOffers());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		//assertEquals(userSize + 1, find.getCulturalOffers().size());
		//assertEquals(offerSize + 1, this.offerRepository.findById(1L).orElse(null).getSubscribedUsers().size());
		
		builder = UriComponentsBuilder.fromUriString("/registeredUser/unsubscribe")
				.queryParam("offer_id", 1L);
		
		responseEntity =
				restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(userSize, this.userService.findOneByEmail("a2@a").getCulturalOffers().size());
		assertEquals(offerSize, this.offerRepository.findById(1L).orElse(null).getSubscribedUsers().size());

		
	}
	
}
