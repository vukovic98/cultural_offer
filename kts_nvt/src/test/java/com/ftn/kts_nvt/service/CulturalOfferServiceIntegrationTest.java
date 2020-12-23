package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.dto.CulturalOfferAddDTO;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;
import com.ftn.kts_nvt.repositories.GeoLocationRepository;
import com.ftn.kts_nvt.services.CulturalOfferService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferServiceIntegrationTest {

	@Autowired
	private CulturalOfferService culturalOfferService;
	
	@Autowired
	private GeoLocationRepository geoRepository;
	
	@Autowired
	private CulturalOfferTypeRepository typeRepository;
	
	@Test
	public void testSaveAndDeleteEntity() {
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		CulturalOfferType t = this.typeRepository.findById(1L).orElse(null);
		CulturalOffer found;
		
		CulturalOffer c = new CulturalOffer("CO_1", null, "This is cultural offer");
		c.setType(t);
		c.setLocation(g);
		
		CulturalOffer saved = this.culturalOfferService.save(c);
		found = this.culturalOfferService.findById(saved.getId());
		
		assertTrue(c.getName().equalsIgnoreCase(saved.getName()));
		assertEquals(found.getId(), saved.getId());
		
		boolean ok = this.culturalOfferService.deleteById(saved.getId());
		found = this.culturalOfferService.findById(saved.getId());
		
		assertTrue(ok);
		assertNull(found);
	}
	
	@Test
	public void testSaveAndDeleteDTO() {
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		CulturalOfferType t = this.typeRepository.findById(1L).orElse(null);
		CulturalOffer found;
		
		CulturalOfferAddDTO dto = new CulturalOfferAddDTO("CO_1", "This is CO", t.getId(), g, null);
		
		CulturalOffer saved = this.culturalOfferService.save(dto);
		found = this.culturalOfferService.findById(saved.getId());
		
		assertTrue(dto.getName().equalsIgnoreCase(saved.getName()));
		assertEquals(found.getId(), saved.getId());
		
		boolean ok = this.culturalOfferService.deleteById(saved.getId());
		found = this.culturalOfferService.findById(saved.getId());
		
		assertTrue(ok);
		assertNull(found);
	}
	
	@Test
	public void testFindAll() {
		ArrayList<CulturalOffer> offers = this.culturalOfferService.findAll();
		
		assertNotNull(offers);
		assertEquals(1, offers.size());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 2);
		
		Page<CulturalOffer> offersPage = this.culturalOfferService.findAll(pageable);
		
		assertEquals(1, offersPage.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		long id = 1;
		long id_fail = 2;
		
		CulturalOffer c = this.culturalOfferService.findById(id);
		
		assertNotNull(c);
		assertEquals(1L, c.getId());
		
		c = this.culturalOfferService.findById(id_fail);
		assertNull(c);
	}
	
	@Test
	public void testUpdate() {
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		CulturalOfferType t = this.typeRepository.findById(1L).orElse(null);
		CulturalOffer found;
		
		CulturalOffer c = new CulturalOffer("CO_1", null, "This is cultural offer");
		c.setType(t);
		c.setLocation(g);
		
		CulturalOffer saved = this.culturalOfferService.save(c);
		
		saved.setName("C_2_W");
		
		CulturalOffer updated = this.culturalOfferService.update(saved, saved.getId());
		
		assertNotNull(updated);
		assertTrue(saved.getName().equalsIgnoreCase(updated.getName()));
		assertEquals(saved.getId(), updated.getId());
		
		boolean ok = this.culturalOfferService.deleteById(saved.getId());
		found = this.culturalOfferService.findById(saved.getId());
		
		assertTrue(ok);
		assertNull(found);
	}
	
	@Test
	public void testFilter() {
		Page<CulturalOffer> offerPage;
		Pageable pageable = PageRequest.of(0, 2);
		
		String name = "Exit";
		String name_fail = "Miletic";
		
		String city = "Novi Sad";
		String city_fail = "Beograd";
		
		ArrayList<String> types = new ArrayList<>();
		types.add("Festival");
		ArrayList<String> types_fail = new ArrayList<>();
		types_fail.add("Muzej");
		
		offerPage = this.culturalOfferService.filter(pageable, name, new ArrayList<>());
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(name.equalsIgnoreCase(offerPage.getContent().get(0).getName()));
		
		offerPage = this.culturalOfferService.filter(pageable, name_fail, new ArrayList<>());
		assertEquals(0, offerPage.getNumberOfElements());
		
		offerPage = this.culturalOfferService.filter(pageable, city, new ArrayList<>());
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(city.equalsIgnoreCase(offerPage.getContent().get(0).getLocation().getPlace()));
		
		offerPage = this.culturalOfferService.filter(pageable, city_fail, new ArrayList<>());
		assertEquals(0, offerPage.getNumberOfElements());
		
		offerPage = this.culturalOfferService.filter(pageable, "", types);
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));
		
		offerPage = this.culturalOfferService.filter(pageable, "", types_fail);
		assertEquals(0, offerPage.getNumberOfElements());
		
		offerPage = this.culturalOfferService.filter(pageable, name, types);
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(name.equalsIgnoreCase(offerPage.getContent().get(0).getName()));
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));
		
		offerPage = this.culturalOfferService.filter(pageable, city, types);
		assertEquals(1, offerPage.getNumberOfElements());
		assertTrue(city.equalsIgnoreCase(offerPage.getContent().get(0).getLocation().getPlace()));
		assertTrue(types.get(0).equalsIgnoreCase(offerPage.getContent().get(0).getType().getName()));
		
		offerPage = this.culturalOfferService.filter(pageable, name_fail, types);
		assertEquals(0, offerPage.getNumberOfElements());
		
		offerPage = this.culturalOfferService.filter(pageable, city_fail, types);
		assertEquals(0, offerPage.getNumberOfElements());
	}
}
