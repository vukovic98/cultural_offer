package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.repositories.GeoLocationRepository;
import com.ftn.kts_nvt.services.GeoLocationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class GeoLocationServiceUnitTest {
	
	@MockBean
	private GeoLocationRepository repository;
	
	@Autowired
	private GeoLocationService service;
	
	@Before
	public void setup() {
		List<GeoLocation> locations = new ArrayList<>();
		GeoLocation location = new GeoLocation(23.23, 23.23, "Novi Sad");
		location.setLocationId(1L);
		locations.add(location);
		
		Pageable pageable = PageRequest.of(0, 2);
		Page<GeoLocation> geoLocationsPage = new PageImpl<>(locations,pageable,1);
		
		//findById 1L
		GeoLocation savedGeoLocation = new GeoLocation(23.23, 23.23, "Novi Sad");
		savedGeoLocation.setLocationId(1L);
		
		//Definisanje ponasanja
		
		given(this.repository.findAll(pageable)).willReturn(geoLocationsPage);
		given(this.repository.findById(1L)).willReturn(java.util.Optional.of(savedGeoLocation));
		given(this.repository.findById(5L)).willReturn(java.util.Optional.empty());
	}
	
	@Test
	public void testFindAllPageable() {
	Pageable pageable = PageRequest.of(0,2);
	Page<GeoLocation> found = this.service.findAll(pageable);
	
	verify(this.repository, times(1)).findAll(pageable);
	assertEquals(1, found.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		GeoLocation found = this.service.findById(1L);
		verify(this.repository,times(1)).findById(1L);
		assertEquals(1L, found.getLocationId());
	}
	
	@Test
	public void testFindByIdNotFound() {
		GeoLocation found = this.service.findById(5L);
		verify(this.repository,times(1)).findById(5L);
		assertNull(found);
	}
	
}
