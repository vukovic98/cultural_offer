package com.ftn.kts_nvt.service;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.CommentRepository;
import com.ftn.kts_nvt.services.CommentService;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CommentServiceUnitTest {

	@Autowired
	private CommentService commentService;
	
	@MockBean
	private CommentRepository commentRepository;
	
	@Before
	public void setup() {
		RegisteredUser user = new RegisteredUser("Vladimir", "Vukovic", "vladimirvukovic98@hotmail.rs", "vukovic", new ArrayList<>(), new ArrayList<>(), true);
		ArrayList<Comment> comments = new ArrayList<>();
		Comment c = new Comment("Ovo je komentar", null, user);
		c.setCommentId(1L);
		c.setApproved(true);
		user.getComments().add(c);
		comments.add(c);
		
		ArrayList<Comment> unapprovedComments = new ArrayList<>();
		Comment c2 = new Comment("unapproved", null, user);
		c2.setCommentId(3L);
		user.getComments().add(c2);
		unapprovedComments.add(c2);
		
		Pageable pageable = PageRequest.of(0,2);
        Page<Comment> commentsPage = new PageImpl<>(comments,pageable,1);
        Page<Comment> unapprovedPage = new PageImpl<>(unapprovedComments, pageable, 1);
        
        given(this.commentRepository.findAll()).willReturn(comments);
        given(this.commentRepository.findAll(pageable)).willReturn(commentsPage);
        given(this.commentRepository.findById(1L)).willReturn(java.util.Optional.of(c));
        given(this.commentRepository.findById(2L)).willReturn(java.util.Optional.empty());
        given(this.commentRepository.save(c)).willReturn(c);
        given(this.commentRepository.findAllPendingComments(pageable)).willReturn(unapprovedPage);
		
	}
	
	@Test
	public void testFindAll() {
		ArrayList<Comment> list = this.commentService.findAll();
		
		verify(this.commentRepository, times(1)).findAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0,2);
		Page<Comment> cList = this.commentService.findAll(pageable);
		
		verify(this.commentRepository, times(1)).findAll(pageable);
		assertEquals(1, cList.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		Comment c = this.commentService.findById(1L);
		
		verify(this.commentRepository, times(1)).findById(1L);
		assertEquals(1L, c.getCommentId());
	}
	
	@Test
	public void testFindByIdNotFound() {
		Comment c = this.commentService.findById(2L);
		
		verify(this.commentRepository, times(1)).findById(2L);
		assertNull(c);
	}
	
	@Test
	public void testFindPendingComments() {
		Pageable pageable = PageRequest.of(0,2);
		Page<Comment> list = this.commentService.findAllPendingComments(pageable);
		
		verify(this.commentRepository, times(1)).findAllPendingComments(pageable);
		assertEquals(1, list.getNumberOfElements());
		assertEquals(3L, list.getContent().get(0).getCommentId());
	}
	
	@Test
	public void testCreate() {
		RegisteredUser user = new RegisteredUser("Vladimir", "Vukovic", "vladimirvukovic98@hotmail.rs", "vukovic", new ArrayList<>(), new ArrayList<>(), true);
		Comment c = new Comment("Ovo je komentar", null, user);
		c.setCommentId(1L);
		user.getComments().add(c);
		
		Comment saved = this.commentService.save(c);
		System.out.println(saved);
		assertEquals(1L, saved.getCommentId());
	}
}
