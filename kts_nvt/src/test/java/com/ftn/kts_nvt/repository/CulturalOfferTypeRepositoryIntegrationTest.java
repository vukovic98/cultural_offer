package com.ftn.kts_nvt.repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferTypeRepositoryIntegrationTest {

	@Autowired
	private CulturalOfferTypeRepository repository;
	
	@Test
	public void testFindByCategoryId() {
		ArrayList<CulturalOfferType> found = repository.findByCategoryId(2L);
		assertNotNull(found);
		assertEquals(2, found.size());
	}
	
	@Test
	public void testFindByCategoryIdFail() {
		ArrayList<CulturalOfferType> found = repository.findByCategoryId(10L);
		assertEquals(0, found.size());
	}
	
	@Test
	public void testFindByName() {
		CulturalOfferType found = repository.findByName("Type1");
		assertNotNull(found);
		assertEquals("Type1", found.getName());
		assertEquals(Long.valueOf(1), found.getId());
	}
	
	@Test
	public void testFindByNameFail() {
		CulturalOfferType found = repository.findByName("aldkas,");
		assertNull(found);
	}
}
