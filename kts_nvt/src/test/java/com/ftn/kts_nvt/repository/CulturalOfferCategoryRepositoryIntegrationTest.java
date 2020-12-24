package com.ftn.kts_nvt.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferCategoryRepositoryIntegrationTest {
	
	@Autowired
	private CulturalOfferCategoryRepository repository;
	
	@Test
	public void testGetByName() {
		String name = "Institution";
		String nameFail = "decembar";
	
		
		CulturalOfferCategory category = this.repository.findCulturalOfferCategoryByName(name).orElse(null);
		
		assertNotNull(category);
		assertEquals(category.getName(), "Institution");
		
		CulturalOfferCategory categoryFail = this.repository.findCulturalOfferCategoryByName(nameFail).orElse(null);
		
		assertNull(categoryFail);
	}

}
