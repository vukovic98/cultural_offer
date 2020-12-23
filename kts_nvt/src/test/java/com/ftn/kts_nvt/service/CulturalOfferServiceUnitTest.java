package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;
import com.ftn.kts_nvt.repositories.GeoLocationRepository;
import com.ftn.kts_nvt.services.CulturalOfferService;

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
        
        ArrayList<String> types = new ArrayList<>();
        types.add("Festival");
        
        String exp_name = "Exit";
        String exp_loc = "Novi Sad";
        
        ArrayList<String> types_fail = new ArrayList<>();
        types_fail.add("Muzej");
        
        String exp_name_fail = "SNP";
        String exp_loc_fail = "Beograd"; 
	
        given(this.culturalOfferRepository.findById(1L)).willReturn(java.util.Optional.of(savedCulturalOffer));
        given(this.culturalOfferRepository.findById(2L)).willReturn(java.util.Optional.empty());
        given(this.culturalOfferRepository.save(culturalOffer)).willReturn(savedCulturalOffer);
        
        //Successfull filter
        given(this.culturalOfferRepository.filter(pageable, types)).willReturn(culturalOffersPage);
        given(this.culturalOfferRepository.filter(pageable, exp_name)).willReturn(culturalOffersPage);
        given(this.culturalOfferRepository.filter(pageable, exp_loc)).willReturn(culturalOffersPage);
        given(this.culturalOfferRepository.filter(pageable, exp_name, types)).willReturn(culturalOffersPage);
        given(this.culturalOfferRepository.filter(pageable, exp_loc, types)).willReturn(culturalOffersPage);
        
        //Fail filter
        Page<CulturalOffer> culturalOffersPageFail = new PageImpl<>(new ArrayList<>(),pageable,1);
        given(this.culturalOfferRepository.filter(pageable, types_fail)).willReturn(culturalOffersPageFail);
        given(this.culturalOfferRepository.filter(pageable, exp_name_fail)).willReturn(culturalOffersPageFail);
        given(this.culturalOfferRepository.filter(pageable, exp_loc_fail)).willReturn(culturalOffersPageFail);
        given(this.culturalOfferRepository.filter(pageable, exp_name_fail, types_fail)).willReturn(culturalOffersPageFail);
        given(this.culturalOfferRepository.filter(pageable, exp_loc_fail, types_fail)).willReturn(culturalOffersPageFail);
        
        doNothing().when(culturalOfferRepository).delete(savedCulturalOffer);
        doNothing().when(culturalOfferRepository).deleteById(1L);
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
		verify(culturalOfferRepository, times(1)).findById(2L);
		assertNull(found);
	}

	@Test
	public void testGenerate() {
		CulturalOffer offer = new CulturalOffer("Exit", null, "Novi Sad - Festival");
		GeoLocation g = this.geoRepository.findById(1L).orElse(null);
		CulturalOfferType t = this.typeRepo.findById(1L).orElse(null);
		offer.setLocation(g);
		offer.setType(t);

		CulturalOffer created = this.culturalOfferservice.save(offer);
		
		assertTrue(("Exit").equalsIgnoreCase(created.getName()));
	}
	
	@Test
	public void testFilterName() {
		String exp_name = "Exit";
		
		Pageable pageable = PageRequest.of(0,2);
        Page<CulturalOffer> found = this.culturalOfferservice.filter(pageable, exp_name, new ArrayList<>());
        

        assertEquals(1, found.getNumberOfElements());
        assertTrue(("Exit").equalsIgnoreCase(found.getContent().get(0).getName()));
	}
	
	@Test
	public void testFilterLocation() {
		String exp_loc = "Novi Sad";
		
		Pageable pageable = PageRequest.of(0,2);
        Page<CulturalOffer> found = this.culturalOfferservice.filter(pageable, exp_loc, new ArrayList<>());
        
	
        assertEquals(1, found.getNumberOfElements());
        assertTrue(("Exit").equalsIgnoreCase(found.getContent().get(0).getName()));
	}
	
	@Test
	public void testFilterType() {
		ArrayList<String> types = new ArrayList<>();
        types.add("Festival");
		
		Pageable pageable = PageRequest.of(0,2);
        Page<CulturalOffer> found = this.culturalOfferservice.filter(pageable, "", types);
        
	
        assertEquals(1, found.getNumberOfElements());
        assertTrue(("Exit").equalsIgnoreCase(found.getContent().get(0).getName()));
	}
	
	@Test
	public void testMixedFilter() {
		ArrayList<String> types = new ArrayList<>();
        types.add("Festival");
        String exp_loc = "Novi Sad";
        String exp_name = "Exit";
		
		Pageable pageable = PageRequest.of(0,2);
        Page<CulturalOffer> found = this.culturalOfferservice.filter(pageable, exp_name, types);
        
	
        assertEquals(1, found.getNumberOfElements());
        assertTrue(("Exit").equalsIgnoreCase(found.getContent().get(0).getName()));
        
        found = this.culturalOfferservice.filter(pageable, exp_loc, types);
        
        assertEquals(1, found.getNumberOfElements());
        assertTrue(("Exit").equalsIgnoreCase(found.getContent().get(0).getName()));
	}
	
	@Test
	public void testFailFilter() {
		ArrayList<String> types_fail = new ArrayList<>();
        types_fail.add("Muzej");
        
        String exp_name_fail = "SNP";
        String exp_loc_fail = "Beograd"; 
        
        Pageable pageable = PageRequest.of(0,2);
        
        Page<CulturalOffer> found = this.culturalOfferservice.filter(pageable, exp_name_fail, new ArrayList<>());
        assertEquals(0, found.getNumberOfElements());
        
        found = this.culturalOfferservice.filter(pageable, exp_loc_fail, new ArrayList<>());
        assertEquals(0, found.getNumberOfElements());
        
        found = this.culturalOfferservice.filter(pageable, exp_name_fail, types_fail);
        assertEquals(0, found.getNumberOfElements());
        
        found = this.culturalOfferservice.filter(pageable, exp_loc_fail, types_fail);
        assertEquals(0, found.getNumberOfElements());
        
        found = this.culturalOfferservice.filter(pageable, "", types_fail);
        assertEquals(0, found.getNumberOfElements());
        
	}
}
