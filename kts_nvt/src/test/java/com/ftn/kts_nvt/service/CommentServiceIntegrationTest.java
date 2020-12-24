package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;
import com.ftn.kts_nvt.services.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CommentServiceIntegrationTest {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CulturalOfferRepository offerRepository;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
	@Test
	public void testFindAll() {
		ArrayList<Comment> list = this.commentService.findAll();
		
		assertNotNull(list);
		assertEquals(2, list.size());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 5);
		
		Page<Comment> commentsPage = this.commentService.findAll(pageable);
		
		assertEquals(2, commentsPage.getNumberOfElements());
		assertTrue(commentsPage.isLast());
	}
	
	@Test
	public void findById() {
		long id = 1;
		long id_fail = 999;
		
		Comment c = this.commentService.findById(id);
		
		assertNotNull(c);
		assertEquals(1L, c.getCommentId());
		
		c = this.commentService.findById(id_fail);
		assertNull(c);
		
	}
	
	@Test
	public void testCreateAndDelete() {
		CulturalOffer c = this.offerRepository.findById(1L).orElse(null);
		RegisteredUser u = this.userRepository.findById(1L).orElse(null);
		
		if(c != null && u != null) {
			Comment com = new Comment("This is another comment", null, u);
			
			
			Comment saved = this.commentService.save(com);
			
			ArrayList<Comment> offerComments = this.commentService.findCommentsForOffer(c.getId());
			offerComments.add(saved);
			
			c.setComments(offerComments);
			CulturalOffer savedOffer = this.offerRepository.save(c);
			
			assertNotNull(saved);
			assertNotNull(this.commentService.findById(saved.getCommentId()));
			
			offerComments = this.commentService.findCommentsForOffer(c.getId());
			assertNotEquals(-1, offerComments.indexOf(saved));
			
			boolean ok = this.commentService.deleteById(saved.getCommentId());
			
			assertTrue(ok);
			assertNull(this.commentService.findById(saved.getCommentId()));
			
			offerComments = this.commentService.findCommentsForOffer(c.getId());
			assertEquals(-1, offerComments.indexOf(saved));
		}
	}
	
	@Test
	public void testUpdate() {
		Comment c = new Comment();
		String content = "This is updated content";
		String old = "This is comment";
		c.setContent(content);
		
		Comment changed = this.commentService.update(c, 1L);
		
		Comment found = this.commentService.findById(1L);
		
		assertTrue(content.equalsIgnoreCase(found.getContent()));
		
		c.setContent(old);
		this.commentService.update(c, 1L);
	}
	
	@Test 
	public void findAllPendingComments() {
		Pageable pageable = PageRequest.of(0, 2);
		
		Page<Comment> commentsPage = this.commentService.findAllPendingComments(pageable);
		
		assertEquals(1, commentsPage.getNumberOfElements());
		assertTrue(commentsPage.isLast());
	}
	
	@Test
	public void testApprove() {
		Comment c = this.commentService.approve(2L);
		
		assertTrue(c.isApproved());
		
		c.setApproved(false);
		this.commentService.save(c);
	}
}
