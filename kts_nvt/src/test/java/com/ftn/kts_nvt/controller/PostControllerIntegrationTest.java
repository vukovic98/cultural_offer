package com.ftn.kts_nvt.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
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

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.dto.PostDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.helper.CulturalOfferMapper;
import com.ftn.kts_nvt.helper.PostMapper;
import com.ftn.kts_nvt.services.CulturalOfferService;
import com.ftn.kts_nvt.services.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class PostControllerIntegrationTest {

	private String accessToken;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostService postService;

	@Autowired
	private CulturalOfferService offerService;

	public void login(String username, String password) {
		UserLoginDTO dto = new UserLoginDTO(username, password);
		ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in", dto,
				UserTokenStateDTO.class);

		accessToken = "Bearer " + responseEntity.getBody().getAuthenticationToken();
	}

	@Test
	public void testFindAll() {
		login("vlado@gmail.com", "vukovic");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<PostDTO> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<List<PostDTO>> responseEntity = restTemplate.exchange("/posts", HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<PostDTO>>() {
				});
		List<PostDTO> posts = responseEntity.getBody();
		int postsSize = this.postService.findAll().size();

		assertNotNull(posts);
		assertEquals(postsSize, posts.size());
	}

	@Test
	public void testFindById() {
		login("vlado@gmail.com", "vukovic");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);

		HttpEntity<PostDTO> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<PostDTO> responseEntity = restTemplate.exchange("/posts/5", HttpMethod.GET, httpEntity,
				PostDTO.class);

		PostDTO post = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(post);
		assertEquals(5L, post.getId());
	}

	@Test
	@Transactional
	public void testCreateAndDelete() {
		login("vlado@gmail.com", "vukovic");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		long id = 1;
		PostDTO post = new PostDTO(1L, "New post", "Content", Instant.now());
		CulturalOfferMapper offerMapper = new CulturalOfferMapper();
		CulturalOffer o = this.offerService.findById(id);
		if (o != null) {
			CulturalOfferDTO offer = offerMapper.toDto(o);
			post.setOffer(offer);
		} else
			post.setOffer(null);

		HttpEntity<PostDTO> httpEntity = new HttpEntity<PostDTO>(post, headers);

		ResponseEntity<PostDTO> responseEntity = restTemplate.exchange("/posts", HttpMethod.POST, httpEntity,
				PostDTO.class);

		PostDTO created = responseEntity.getBody();

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(created);
		assertTrue("New post".equalsIgnoreCase(created.getTitle()));

		// removing
		HttpEntity<Object> httpEntityDelete = new HttpEntity<>(headers);

		ResponseEntity<?> respEntDelete = restTemplate.exchange("/posts/" + created.getId(), HttpMethod.DELETE,
				httpEntityDelete, Object.class);
		assertEquals(HttpStatus.OK, respEntDelete.getStatusCode());

	}

	@Test
	@Transactional
	public void testUpdate() {
		login("vlado@gmail.com", "vukovic");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", this.accessToken);
		//get Post
		Post p = this.postService.findOne(5L);
		PostMapper postMapper = new PostMapper();
		PostDTO post = postMapper.toDto(p);
		post.setTitle("Updated title");
		System.out.println(post.getTitle());
		
		/*
		 * CulturalOfferMapper offerMapper = new CulturalOfferMapper(); CulturalOffer o
		 * = this.offerService.findById(1L); if (o != null) { CulturalOfferDTO offer =
		 * offerMapper.toDto(o); post.setOffer(offer); } else post.setOffer(null);
		 */

		HttpEntity<PostDTO> httpEntity = new HttpEntity<PostDTO>(post, headers);

		ResponseEntity<PostDTO> responseEntity = restTemplate.exchange("/posts/" + p.getPostId(), HttpMethod.PUT, httpEntity,
				PostDTO.class);

		PostDTO updated = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(updated);
		assertTrue("Updated title".equalsIgnoreCase(updated.getTitle()));

		//vrati na staro
		updated.setTitle("This is post5");
		HttpEntity<PostDTO> httpEntity2 = new HttpEntity<PostDTO>(updated, headers);

		ResponseEntity<PostDTO> responseEntity2 = restTemplate.exchange("/posts/"+post.getId(), HttpMethod.PUT, httpEntity2,
				PostDTO.class);
		assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());

	}

}
