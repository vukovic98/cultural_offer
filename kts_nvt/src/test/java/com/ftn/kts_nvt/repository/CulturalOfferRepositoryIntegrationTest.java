package com.ftn.kts_nvt.repository;

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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferRepositoryIntegrationTest {

	@Autowired
	private CulturalOfferRepository culturalOfferRepository;

	@Test
	public void testFindByTypeId() {
		long typeId = 1;
		ArrayList<CulturalOffer> offers = this.culturalOfferRepository.findByTypeId(typeId);

		assertEquals(2, offers.size());
		assertEquals(1L, offers.get(0).getType().getId());
		assertEquals(1L, offers.get(1).getType().getId());
	}

	@Test
	public void testFindByTypeIdFail() {
		long typeId = 1999;
		ArrayList<CulturalOffer> offers = this.culturalOfferRepository.findByTypeId(typeId);

		assertEquals(0, offers.size());
	}

	@Test
	public void testFindByName() {
		String name = "Name1";

		List<CulturalOffer> offers = this.culturalOfferRepository.findByName(name);

		assertEquals(1, offers.size());
		assertTrue(name.equalsIgnoreCase(offers.get(0).getName()));

	}

	@Test
	public void testFindByNameFail() {
		String name_fail = "Miletic";
		List<CulturalOffer> offers = this.culturalOfferRepository.findByName(name_fail);
		assertEquals(0, offers.size());
	}

	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<CulturalOffer> offersPage = this.culturalOfferRepository.findAll(pageable);

		assertEquals(4, offersPage.getNumberOfElements());
	}

	@Test
	public void testFiler() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String name = "Name1";
		String name_fail = "Miletic";

		String city = "Novi Sad";
		String city_fail = "Beograd";

		ArrayList<String> types = new ArrayList<>();
		types.add("Festival");
		ArrayList<String> types_fail = new ArrayList<>();
		types_fail.add("Muzej");

		offerPage = this.culturalOfferRepository.filter(pageable, name);
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(name.equalsIgnoreCase(offerPage.getContent().get(0).getName()));

		offerPage = this.culturalOfferRepository.filter(pageable, name_fail);
		assertEquals(0, offerPage.getNumberOfElements());

		offerPage = this.culturalOfferRepository.filter(pageable, city);
		assertEquals(4, offerPage.getNumberOfElements());
		assertTrue(city.equalsIgnoreCase(offerPage.getContent().get(0).getLocation().getPlace()));

		offerPage = this.culturalOfferRepository.filter(pageable, city_fail);
		assertEquals(0, offerPage.getNumberOfElements());

		offerPage = this.culturalOfferRepository.filter(pageable, types);
		assertEquals(2, offerPage.getNumberOfElements());
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));

		offerPage = this.culturalOfferRepository.filter(pageable, types_fail);
		assertEquals(0, offerPage.getNumberOfElements());

		offerPage = this.culturalOfferRepository.filter(pageable, name, types);
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(name.equalsIgnoreCase(offerPage.getContent().get(0).getName()));
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));

		offerPage = this.culturalOfferRepository.filter(pageable, city, types);
		assertEquals(2, offerPage.getNumberOfElements());
		assertTrue(city.equalsIgnoreCase(offerPage.getContent().get(0).getLocation().getPlace()));
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));

		offerPage = this.culturalOfferRepository.filter(pageable, name_fail, types);
		assertEquals(0, offerPage.getNumberOfElements());

		offerPage = this.culturalOfferRepository.filter(pageable, city_fail, types);
		assertEquals(0, offerPage.getNumberOfElements());
	}

	@Test
	public void testFilterName() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String name = "Name1";

		offerPage = this.culturalOfferRepository.filter(pageable, name);
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(name.equalsIgnoreCase(offerPage.getContent().get(0).getName()));

	}

	@Test
	public void testFilterNameFail() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String name_fail = "Miletic";

		offerPage = this.culturalOfferRepository.filter(pageable, name_fail);
		assertEquals(0, offerPage.getNumberOfElements());
	}

	@Test
	public void testFilterCity() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String city = "Novi Sad";

		offerPage = this.culturalOfferRepository.filter(pageable, city);
		assertEquals(4, offerPage.getNumberOfElements());
		assertTrue(city.equalsIgnoreCase(offerPage.getContent().get(0).getLocation().getPlace()));

	}

	@Test
	public void testFilterCityFail() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String city_fail = "Beograd";

		offerPage = this.culturalOfferRepository.filter(pageable, city_fail);
		assertEquals(0, offerPage.getNumberOfElements());

	}

	@Test
	public void testFilterType() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		ArrayList<String> types = new ArrayList<>();
		types.add("Festival");

		offerPage = this.culturalOfferRepository.filter(pageable, types);
		assertEquals(2, offerPage.getNumberOfElements());
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));

	}

	@Test
	public void testFilterTypeFail() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		ArrayList<String> types_fail = new ArrayList<>();
		types_fail.add("Muzej");

		offerPage = this.culturalOfferRepository.filter(pageable, types_fail);
		assertEquals(0, offerPage.getNumberOfElements());

	}

	@Test
	public void testFilterNameTypes() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String name = "Name1";

		ArrayList<String> types = new ArrayList<>();
		types.add("Festival");

		offerPage = this.culturalOfferRepository.filter(pageable, name, types);
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(name.equalsIgnoreCase(offerPage.getContent().get(0).getName()));
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));

	}

	@Test
	public void testFilterCityTypes() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String city = "Novi Sad";

		ArrayList<String> types = new ArrayList<>();
		types.add("Festival");

		offerPage = this.culturalOfferRepository.filter(pageable, city, types);
		assertEquals(2, offerPage.getNumberOfElements());
		assertTrue(city.equalsIgnoreCase(offerPage.getContent().get(0).getLocation().getPlace()));
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));

	}

	@Test
	public void testFilterCityFailTypes() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String city_fail = "Beograd";

		ArrayList<String> types = new ArrayList<>();
		types.add("Festival");

		offerPage = this.culturalOfferRepository.filter(pageable, city_fail, types);
		assertEquals(0, offerPage.getNumberOfElements());

	}

	@Test
	public void testFilterNameFailTypes() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 10);

		String name_fail = "Miletic";

		ArrayList<String> types = new ArrayList<>();
		types.add("Festival");

		offerPage = this.culturalOfferRepository.filter(pageable, name_fail, types);
		assertEquals(0, offerPage.getNumberOfElements());

	}

	@Test
	public void testGetOfferByComment() {
		long comment_id = 1; // from database

		CulturalOffer c = this.culturalOfferRepository.getOfferByComment(comment_id);

		assertNotNull(c);
		assertEquals(1L, c.getId());

	}

	@Test
	public void testGetOfferByCommentFail() {
		long comment_id_fail = 999;
		CulturalOffer c = this.culturalOfferRepository.getOfferByComment(comment_id_fail);
		assertNull(c);
	}
}
