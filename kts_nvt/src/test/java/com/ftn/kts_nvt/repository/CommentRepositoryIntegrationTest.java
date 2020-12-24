package com.ftn.kts_nvt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.repositories.CommentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CommentRepositoryIntegrationTest {

	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	public void testFindUnapprovedCommentsPageable() {
		Pageable pageable = PageRequest.of(0, 2);
		Page<Comment> comments = this.commentRepository.findAllPendingComments(pageable);
		
		assertEquals(1, comments.getNumberOfElements());
	}
	
	@Test
	public void testFindCommentsForOffer() {
		ArrayList<Comment> list = this.commentRepository.findCommentsForOffer(1L);
		
		assertEquals(2, list.size());
	}
}
