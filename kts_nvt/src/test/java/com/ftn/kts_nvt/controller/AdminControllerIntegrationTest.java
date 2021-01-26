package com.ftn.kts_nvt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

import com.ftn.kts_nvt.SSLUtils;
import com.ftn.kts_nvt.beans.Admin;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.services.AdminService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AdminControllerIntegrationTest {

	private String accessToken;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CulturalOfferRepository offerRepository;

	@Autowired
	private AdminService adminService;

	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
		ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in", dto,
				UserTokenStateDTO.class);

		accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
	}

	@BeforeAll
	public void turnOffSSL() {
		try {
			SSLUtils.turnOffSslChecking();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterAll
	public void turnOnSSL() {
		try {
			SSLUtils.turnOnSslChecking();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		login("ad@ad", "madzarevic");
		System.out.println(accessToken);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		ResponseEntity<ArrayList<UserDTO>> responseEntity = this.restTemplate.exchange("/admin", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<ArrayList<UserDTO>>() {
				});

		ArrayList<UserDTO> users = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(5, users.size());
		// assertTrue(users.get(0).getEmail().equals("ad@ad"));

	}

	@Test
	public void testFindById() {
		login("ad@ad", "madzarevic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("/admin/5", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<UserDTO>() {
				});

		UserDTO found = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(5L, found.getId());
	}

	@Test
	public void testFindByEmail() {
		login("ad@ad", "madzarevic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("/admin/byEmail/ad@ad", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<UserDTO>() {
				});

		UserDTO found = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(5L, found.getId());
		assertEquals("ad@ad", found.getEmail());
	}

	@Test
	public void testCreateDelete() {
		login("ad@ad", "madzarevic");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		UserDTO u = new UserDTO(1L, "Test", "Testic", "test@test.com", "1234");
		int size = this.adminService.findAll().size();

		HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(u, headers);

		ResponseEntity<?> responseEntity = restTemplate.postForEntity("/admin", entity, UserDTO.class);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(size + 1, this.adminService.findAll().size());

		Admin saved = this.adminService.findOneByEmail("test@test.com");

		responseEntity = restTemplate.exchange("/admin/" + saved.getId(), HttpMethod.DELETE, new HttpEntity<>(headers),
				Object.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(size, this.adminService.findAll().size());

	}

}
