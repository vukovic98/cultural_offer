package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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

import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.dto.CulturalOfferTypeDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.services.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ImageControllerIntegrationTest {

	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 @Autowired
	 private ImageService service;
	 
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
		 ResponseEntity<ArrayList<Image>> responseEntity = 
					restTemplate.exchange("/image",
											HttpMethod.GET, null,
											new ParameterizedTypeReference<ArrayList<Image>>() {});
	     
	     ArrayList<Image> types = responseEntity.getBody();
			
	     assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
	     assertEquals(2, types.size());
	 }
	 
	 @Test
	 public void testFindById() {
		 login("a2@a", "vukovic");
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", this.accessToken);
		 HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
		 
		 ResponseEntity<Image> responseEntity =
	                restTemplate.exchange("/image/1",
	                						HttpMethod.GET,
	                						httpEntity,
					                		Image.class);
		 Image img = responseEntity.getBody();
		 assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
		 assertEquals(Long.valueOf(1L), img.getId());
	 }
	 
	 @Test
	 public void testCreateAndDelete() {
		 int sizeBefore = service.findAll().size();
		 
		 login("a@a", "vukovic");
		 
		 Image img = new Image("5".getBytes());
		 
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", this.accessToken);
		 HttpEntity<Image> httpEntity = new HttpEntity<>(img, headers);
		 
		 ResponseEntity<Image> responseEntity =
	                restTemplate.exchange("/image",
	                						HttpMethod.POST,
	                						httpEntity,
					                		Image.class);
		 Image created = responseEntity.getBody();
		 assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		 int sizeAfter = service.findAll().size();
		 assertEquals(sizeBefore+1, sizeAfter);
		 
		 responseEntity = restTemplate.exchange("/image/"+created.getId(),
	                						HttpMethod.DELETE,
	                						httpEntity,
					                		Image.class);
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 int sizeAfterDelete = service.findAll().size();
		 assertEquals(sizeBefore, sizeAfterDelete);
	 }
	 
	 @Test
	 public void testUpdate() {
		 login("a@a", "vukovic");		//ROLE_USER
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", this.accessToken);
		 Image img = new Image("10".getBytes());
		 HttpEntity<Image> httpEntity = new HttpEntity<Image>(img, headers);
		 ResponseEntity<Object> responseEntity = restTemplate.exchange("/image/1",
					HttpMethod.PUT,
					httpEntity,
					Object.class);
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 @Test
	 public void testDeleteFail() {
		 login("a2@a", "vukovic");	//ROLE_USER
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", this.accessToken);
		 HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		 ResponseEntity<Object> responseEntity = restTemplate.exchange("/image/1",
					HttpMethod.DELETE,
					httpEntity,
					Object.class);
		 assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
		 
		 login("a@a", "vukovic");		//ROLE_USER
		 headers = new HttpHeaders();
		 headers.add("Authorization", this.accessToken);
		 httpEntity = new HttpEntity<Object>(headers);
		 responseEntity = restTemplate.exchange("/image/10",
					HttpMethod.DELETE,
					httpEntity,
					Object.class);
		 assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	 }
}
