package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.PostRepository;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;
import com.ftn.kts_nvt.services.PostService;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostServiceIntegrationTest {

	@Autowired
	private PostService postService;

	@Autowired
	private CulturalOfferRepository offerRepository;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Rule
	public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.ALL);

	@Test
	@Transactional
	public void x_testCreateAndDeleteEntity() throws Exception {
		Post post = new Post("Post7", "This is post.", Instant.now());
		post.setPostId(7L);
		CulturalOffer offer = this.offerRepository.getOne(1L);
		post.setOffer(offer);

		Post saved = this.postService.create(post);
		Post found = this.postService.findOne(saved.getPostId());

		assertTrue(post.getTitle().equalsIgnoreCase(saved.getTitle()));
		assertEquals(found.getPostId(), saved.getPostId());

		//removing
		this.postService.delete(saved.getPostId());
		Post deleted = this.postService.findOne(saved.getPostId());
		assertNull(deleted);
	}

	@Test
	public void testFindAll() {
		List<Post> posts = this.postService.findAll();
		assertNotNull(posts);
		assertEquals(4, posts.size());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 7);
		Page<Post> postsPage = this.postService.findAll(pageable);
		assertEquals(4, postsPage.getNumberOfElements());
		
	}
	
	@Test
	public void testFindOne() {
		long id = 5;
		long idFail = 55;
		
		//pass
		Post post = this.postService.findOne(id);
		
		assertNotNull(post);
		assertEquals(5L, post.getPostId());
		
		//fail
		Post postFail = this.postService.findOne(idFail);
		
		assertNull(postFail);
	}
	
	
	@Test
	@Transactional
	public void x_testUpdate() throws Exception {
		Post post = new Post("Post7", "This is post.", Instant.now());
		post.setPostId(7L);
		CulturalOffer offer = this.offerRepository.getOne(1L);
		post.setOffer(offer);

		Post saved = this.postService.create(post);
		saved.setTitle("Title changed");
		
		Post updated = this.postService.update(saved, saved.getPostId());
		assertNotNull(updated);
		assertTrue(saved.getContent().equalsIgnoreCase(updated.getContent()));
		assertEquals(saved.getPostId(), updated.getPostId());
		
		//removing
		Post found = this.postService.findOne(saved.getPostId());

		this.postService.delete(saved.getPostId());
		Post deleted = this.postService.findOne(saved.getPostId());
		assertNull(deleted);
	}
	
	@Test
	public void testSendMailToSubscribed() throws MessagingException {
		
		long id = 1L;
		RegisteredUser user = userRepository.findById(id).orElse(null);
		CulturalOffer offer = this.offerRepository.findById(1L).orElse(null);
		Post post = this.postRepository.findById(1L).orElse(null);
		
	    GreenMailUtil.sendTextEmailTest(user.getEmail(), "mrs.isa2020@gmail.com", offer.getName() + " : New post is here!", postService.createMailBody(user, offer, post.getTitle()));
	    MimeMessage[] emails = greenMail.getReceivedMessages();
	    
	    assertEquals(1, emails.length);
	    assertEquals("Name1 : New post is here!", emails[0].getSubject());
	    assertEquals("<h2>Vladimir, new post for offer you are subscribed to has arrived!</h2><br><br><h3>This is post</h3> <br><br><h4>Go check it out <a href='http://localhost:4200/offer-details/1'>here!</a></h4>", GreenMailUtil.getBody(emails[0]));
	    
	    
	}

}
