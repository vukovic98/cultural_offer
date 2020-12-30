package com.ftn.kts_nvt.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.repositories.PostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class PostRepositoryIntegrationTest {
	
	@Autowired
	private PostRepository repository;
	
	@Test
	public void testFindPostsForOffer() {
		ArrayList<Post> postsForOffer = this.repository.findPostsForOffer(1L);
		assertNotNull(postsForOffer);
		assertEquals(4, postsForOffer.size());
	}
	
	@Test
	public void testFindPostsForOfferFail() {
		ArrayList<Post> postsForOffer = this.repository.findPostsForOffer(77L);
		assertEquals(0, postsForOffer.size());
	}
}
