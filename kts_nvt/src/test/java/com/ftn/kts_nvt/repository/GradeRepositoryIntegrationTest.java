package com.ftn.kts_nvt.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.repositories.GradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class GradeRepositoryIntegrationTest {

	@Autowired
	private GradeRepository repository;
	
	@Test
	public void findByUserId() {
		List<Grade> grades = repository.findGradesByUser_Id(1L);
		assertEquals(2, grades.size());
		for(Grade g : grades) {
			assertEquals(Long.valueOf(1), g.getUser().getId());
		}
	}
	
	@Test
	public void findByUserIdFail() {
		List<Grade> grades = repository.findGradesByUser_Id(10L);
		assertEquals(0, grades.size());
	}
	
	@Test
	public void findByOfferId() {
		List<Grade> grades = repository.findGradesByCulturalOffer_Id(1L);
		assertEquals(2, grades.size());
		for(Grade g : grades) {
			assertEquals(Long.valueOf(1), g.getCulturalOffer().getId());
		}
	}
	
	@Test
	public void findByOfferIdFail() {
		List<Grade> grades = repository.findGradesByCulturalOffer_Id(10L);
		assertEquals(0, grades.size());
	}
	
	@Test
	public void testFindByUserAndCulturalOffer() {
		Grade found = repository.findGradeByUser_IdAndCulturalOffer_Id(1L, 1L).orElse(null);
		assertNotNull(found);
		assertEquals(5, found.getValue());
	}
	
	@Test
	public void testFindByUserAndCulturalOfferFail() {
		Grade found = repository.findGradeByUser_IdAndCulturalOffer_Id(100L, 140L).orElse(null);
		assertNull(found);
	}
}
