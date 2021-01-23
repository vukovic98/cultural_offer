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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.dto.CommentDTO;
import com.ftn.kts_nvt.dto.CommentUserDTO;
import com.ftn.kts_nvt.dto.NewCommentDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.helper.PageImplementation;
import com.ftn.kts_nvt.services.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CommentControllerIntegrationTest {

	private String accessToken;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CommentService commentService;

	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
		ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in", dto,
				UserTokenStateDTO.class);

		accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
	}

	@Test
	public void testFindAll() {
		ResponseEntity<ArrayList<CommentUserDTO>> responseEntity = this.restTemplate.exchange("/comments",
				HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<CommentUserDTO>>() {
				});

		ArrayList<CommentUserDTO> comments = responseEntity.getBody();

		assertNotNull(comments);
		assertTrue(!comments.isEmpty());
		assertEquals(2, comments.size());
	}
	
	@Test
	public void testFindAllPageable() {
		login("vlado@gmail.com", "vukovic");

		ResponseEntity<PageImplementation<CommentUserDTO>> responseEntity = this.restTemplate.exchange("/comments/by-page/1",
				HttpMethod.GET, null, new ParameterizedTypeReference<PageImplementation<CommentUserDTO>>() {
				});
		
		PageImplementation<CommentUserDTO> comments = responseEntity.getBody();
		
		assertNotNull(comments);
		assertTrue(!comments.isEmpty());
		assertEquals(2, comments.getNumberOfElements());
	}

	@Test
	public void testFindById() {
		login("vlado@gmail.com", "vukovic");

		ResponseEntity<CommentDTO> responseEntity = restTemplate.getForEntity("/comments/1", CommentDTO.class);

		CommentDTO dto = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1L, dto.getId());
	}

	@Test
	public void testFindByIdFail() {
		login("vlado@gmail.com", "vukovic");

		ResponseEntity<CommentDTO> responseEntity = restTemplate.getForEntity("/comments/222", CommentDTO.class);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

	}

	@Test
	@Transactional
	public void testCreateAndDelete() {
		login("a@a", "vukovic");

		NewCommentDTO dto = new NewCommentDTO(1L, "Brand new comment", null);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<NewCommentDTO> httpEntity = new HttpEntity<>(dto, headers);

		ResponseEntity<CommentDTO> responseEntity = restTemplate.postForEntity("/comments", httpEntity,
				CommentDTO.class);

		CommentDTO created = responseEntity.getBody();

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		assertTrue("Brand new comment".equalsIgnoreCase(created.getContent()));

		HttpEntity<Object> httpEntityDelete = new HttpEntity<Object>(headers);

		ResponseEntity<?> responseEntityDelete = restTemplate.exchange("/comments/" + created.getId(),
				HttpMethod.DELETE, httpEntityDelete, Object.class);

		assertEquals(HttpStatus.OK, responseEntityDelete.getStatusCode());

	}

	@Test
	public void testUpdate() {
		login("vlado@gmail.com", "vukovic");

		CommentUserDTO dto = new CommentUserDTO(1L, "a@a", "Vladimir", "This is changed comment", null, "Name1");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<CommentUserDTO> httpEntity = new HttpEntity<>(dto, headers);

		ResponseEntity<CommentDTO> responseEntity = restTemplate.exchange("/comments/1", HttpMethod.PUT, httpEntity,
				CommentDTO.class);

		CommentDTO updated = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(updated.getContent().equalsIgnoreCase("This is changed comment"));

		dto = new CommentUserDTO(1L, "a@a", "Vladimir", "This is comment", null, "Name1");

		httpEntity = new HttpEntity<>(dto, headers);

		responseEntity = restTemplate.exchange("/comments/1", HttpMethod.PUT, httpEntity, CommentDTO.class);

		updated = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(updated.getContent().equalsIgnoreCase("This is comment"));
	}

	@Test
	public void testApprove() {
		login("vlado@gmail.com", "vukovic");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<CommentUserDTO> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<CommentDTO> responseEntity = restTemplate.exchange("/comments/approve/2", HttpMethod.PUT,
				httpEntity, CommentDTO.class);

		CommentDTO dto = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Comment found = this.commentService.findById(dto.getId());

		assertTrue(found.isApproved());

		found.setApproved(false);
		this.commentService.save(found);
	}
	
	@Test
	public void testApproveFail() {
		login("vlado@gmail.com", "vukovic");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<CommentUserDTO> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<CommentDTO> responseEntity = restTemplate.exchange("/comments/approve/222", HttpMethod.PUT,
				httpEntity, CommentDTO.class);


		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	
	}
}
