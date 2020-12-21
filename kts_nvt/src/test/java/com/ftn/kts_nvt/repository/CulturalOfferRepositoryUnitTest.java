package com.ftn.kts_nvt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class CulturalOfferRepositoryUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CulturalOfferRepository culturalOfferRepository;

	@Before
	public void setup() {
		this.entityManager.persist(new CulturalOffer("Exit", null, "Novi Sad - Festival"));
	}
	
	@Test
	public void testFindByName() {
		CulturalOffer c = this.culturalOfferRepository.findByName("Exit").get(0);
		
		assertEquals("Exit", c.getName());
	}
}
