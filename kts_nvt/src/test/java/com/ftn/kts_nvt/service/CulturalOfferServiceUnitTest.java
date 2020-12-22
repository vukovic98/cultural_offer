package com.ftn.kts_nvt.service;

import java.util.ArrayList;
import java.util.List;

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

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.dto.CulturalOfferAddDTO;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;
import com.ftn.kts_nvt.repositories.GeoLocationRepository;
import com.ftn.kts_nvt.services.CulturalOfferService;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferServiceUnitTest {
	
	@Autowired
	private CulturalOfferService culturalOfferservice;
	
	@Autowired
	private GeoLocationRepository geoRepository;
	
	@Autowired
	private CulturalOfferTypeRepository typeRepo;
	
	@MockBean
	private CulturalOfferRepository culturalOfferRepository;
	
	@Before
	public void setup() {
		List<CulturalOffer> offers = new ArrayList<CulturalOffer>();
		offers.add(new CulturalOffer("Exit", null, "Novi Sad - Festival"));
		
		Pageable pageable = PageRequest.of(0,2);
        Page<CulturalOffer> culturalOffersPage = new PageImpl<>(offers,pageable,1);
        
        //Definisanje ponasanja
        
        given(this.culturalOfferRepository.findAll()).willReturn(offers);
        given(this.culturalOfferRepository.findAll(pageable)).willReturn(culturalOffersPage);
        
        GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		CulturalOfferType t = this.typeRepo.findById(1L).orElse(null);
		
        CulturalOffer culturalOffer = new CulturalOffer("Exit", null, "Novi Sad - Festival");
        culturalOffer.setLocation(g);
        culturalOffer.setType(t);
        
        CulturalOffer savedCulturalOffer = new CulturalOffer("Exit", null, "Novi Sad - Festival");
        savedCulturalOffer.setLocation(g);
        savedCulturalOffer.setType(t);
        savedCulturalOffer.setId(1L);
	
        given(this.culturalOfferRepository.findById(1L)).willReturn(java.util.Optional.of(savedCulturalOffer));
        given(this.culturalOfferRepository.findById(2L)).willReturn(java.util.Optional.empty());
        given(this.culturalOfferRepository.save(culturalOffer)).willReturn(savedCulturalOffer);
        
        doNothing().when(culturalOfferRepository).delete(savedCulturalOffer);
	}

	@Test
	public void testFindAll() {
		List<CulturalOffer> found = this.culturalOfferservice.findAll();
		
		verify(this.culturalOfferRepository, times(1)).findAll();
		assertEquals(1, found.size());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0,2);
        Page<CulturalOffer> found = this.culturalOfferservice.findAll(pageable);
	
        verify(this.culturalOfferRepository, times(1)).findAll(pageable);
        assertEquals(1, found.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		CulturalOffer found = this.culturalOfferservice.findById(1L);
		
		verify(culturalOfferRepository, times(1)).findById(1L);
		assertEquals(1L, found.getId());
	}
	
	@Test
	public void testFindByIdNotFound() {
		CulturalOffer found = this.culturalOfferservice.findById(2L);
		//System.out.println("find" + found);
		verify(culturalOfferRepository, times(1)).findById(2L);
		assertNull(found);
	}

	@Test
	public void testGenerate() {
		CulturalOffer offer = new CulturalOffer("Exit", null, "Novi Sad - Festival");
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		CulturalOfferType t = this.typeRepo.findById(1L).orElse(null);
		System.out.println(g.getLatitude() + " - " + t.getName());
		offer.setLocation(g);
		offer.setType(t);
		
		CulturalOfferAddDTO cA = new CulturalOfferAddDTO();
		cA.setDescription(offer.getDescription());
		cA.setLocation(g);
		cA.setName(offer.getName());
		cA.setType(1L);
		
//		CulturalOffer c = new CulturalOffer();
//		c.setName("Exit");
//		c.setDescription("Novi Sad - Festival");
//		c.setComments(new ArrayList<>());
//		c.setGrades(new ArrayList<>());
//		c.setPosts(new ArrayList<>());
//		c.setSubscribedUsers(new ArrayList<>());
//		c.setLocation(g);		
//		c.setType(t);
//		
		CulturalOffer created = this.culturalOfferservice.save(cA);
		
//		verify(this.culturalOfferRepository, times(1)).save(offer);
		//System.out.println(created);
		assertEquals("Exit", created.getName());
	}
}
