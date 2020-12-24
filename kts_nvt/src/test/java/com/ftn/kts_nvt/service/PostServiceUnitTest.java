package com.ftn.kts_nvt.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;
import com.ftn.kts_nvt.repositories.GeoLocationRepository;
import com.ftn.kts_nvt.repositories.PostRepository;
import com.ftn.kts_nvt.services.PostService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class PostServiceUnitTest {

	@Autowired
	private PostService service;
	
	@MockBean
	private PostRepository repository;
	
	@Autowired
	private GeoLocationRepository geoRepository;
	
	@Autowired
	private CulturalOfferTypeRepository typeRepo;
	
	@Before
	public void setup() {
		List<Post> posts = new ArrayList<>();
		Post p1 = new Post("Post1", "This is post1.",Instant.now());
		p1.setPostId(1L);
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);	
		CulturalOfferType t = this.typeRepo.findById(1L).orElse(null);
        CulturalOffer culturalOffer = new CulturalOffer("Exit", null, "Novi Sad - Festival");
        culturalOffer.setLocation(g);
        culturalOffer.setType(t);
        p1.setOffer(culturalOffer);
    	posts.add(p1);
    	
    	Pageable pageable = PageRequest.of(0, 5);
		Page<Post> postsPage = new PageImpl<>(posts, pageable, 1);
		
		//findById 1L
		Post savedPost = new Post("Post1", "This is post1.",Instant.now());
		savedPost.setOffer(culturalOffer);
		savedPost.setPostId(1L);
		
		// Definisanje ponasanja
		given(this.repository.findAll(pageable)).willReturn(postsPage);
		given(this.repository.findById(1L)).willReturn(java.util.Optional.of(savedPost));
        given(this.repository.findById(5L)).willReturn(java.util.Optional.empty());
		
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Post> f = this.service.findAll(pageable);
		verify(this.repository,times(1)).findAll(pageable);
		assertEquals(1, f.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		Post p = this.service.findOne(1L);
		verify(this.repository,times(1)).findById(1L);
		assertEquals(1L, p.getPostId());
	}
	
	@Test
	public void testFindByIdNotFound() {
		Post p = this.service.findOne(5L);
		verify(this.repository,times(1)).findById(5L);
		assertNull(p);
	}
}
