package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;
import com.ftn.kts_nvt.services.CulturalOfferCategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferCategoryServiceIntegrationTest {

	@Autowired
	private CulturalOfferCategoryService service;

	@Autowired
	private CulturalOfferTypeRepository typeRepository;

	@Test
	public void testFindAll() {
		List<CulturalOfferCategory> categories = this.service.findAll();
		assertNotNull(categories);
		assertEquals(2, categories.size());

	}

	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<CulturalOfferCategory> categoriesPage = this.service.findAll(pageable);
		assertEquals(2, categoriesPage.getNumberOfElements());

	}

	@Test
	public void testFindOne() {
		long id = 1;

		CulturalOfferCategory c1 = this.service.findOne(id);
		assertNotNull(c1);
		assertEquals(id, c1.getId());

	}

	@Test
	public void testFindOneFail() {
		long idFail = 5;
		CulturalOfferCategory c2 = this.service.findOne(idFail);
		assertNull(c2);
	}

	@Test
	public void testFindByName() {
		String name = "Manifestation";
		CulturalOfferCategory c1 = this.service.findByName(name);
		assertNotNull(c1);
		assertEquals("Manifestation", c1.getName());
	}

	@Test
	public void testFindByNameFail() {
		String nameFail = "Ivana";
		CulturalOfferCategory c2 = this.service.findByName(nameFail);
		assertNull(c2);

	}

	@Test
	public void testCreateAndDelete() throws Exception {
		CulturalOfferCategory category = new CulturalOfferCategory(6L, "New Category", null);

		CulturalOfferCategory saved = this.service.save(category);
		CulturalOfferCategory found = this.service.findOne(saved.getId());

		assertTrue(category.getName().equalsIgnoreCase(saved.getName()));
		assertEquals(found.getId(), saved.getId());

		this.service.delete(saved.getId());
		CulturalOfferCategory deleted = this.service.findOne(saved.getId());
		assertNull(deleted);

	}

	@Test
	public void testCreateFail() throws Exception {
		CulturalOfferCategory category = new CulturalOfferCategory(6L, "Institution", null);
		CulturalOfferCategory saved;

		try {
			saved = this.service.create(category);
			assertNull(saved);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testUpdateAndDelete() throws Exception {
		CulturalOfferCategory category = new CulturalOfferCategory(65L, "New Category", null);

		CulturalOfferCategory saved = this.service.save(category);
		saved.setName("Changed");
		CulturalOfferCategory updated = this.service.update(saved, saved.getId());

		assertNotNull(updated);
		assertTrue(saved.getName().equalsIgnoreCase(updated.getName()));
		assertEquals(saved.getId(), updated.getId());

		// delete category
		this.service.delete(saved.getId());
		CulturalOfferCategory deleted = this.service.findOne(saved.getId());
		assertNull(deleted);

	}

	@Test
	public void testUpdateFail() throws Exception {

		CulturalOfferCategory category = this.service.findByName("Institution");
		category.setName("Manifestation");

		try {
			CulturalOfferCategory updated = this.service.update(category, category.getId());
			// "CulturalOfferCategory with given name already exist"
			assertNull(updated);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test
	public void testDeleteFail() throws Exception {
		try {
			this.service.delete(67L);
		} catch (Exception e) {
			//Category with this ID doesn't exists
			e.printStackTrace();
		}

	}
}
