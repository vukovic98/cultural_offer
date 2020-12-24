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

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.services.GeoLocationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class GeoLocationServiceIntegrationTest {
	
	@Autowired
	private GeoLocationService service;
	
	@Test
	public void testFindAll() {
		ArrayList<GeoLocation> locations = this.service.findAll();
		assertNotNull(locations);
		assertEquals(1, locations.size());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 2);
		Page<GeoLocation> locationsPage = this.service.findAll(pageable);
		assertEquals(1, locationsPage.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		long id = 1;
		long idFail = 89;
		
		GeoLocation loc = this.service.findById(id);
		assertNotNull(loc);
		assertEquals(1L, loc.getLocationId());
		
		GeoLocation locF = this.service.findById(idFail);
		assertNull(locF);
	}
	
	@Test
	public void testCreateAndDeleteById() {
		GeoLocation gl = new GeoLocation(22.2, 11.11, "Backa Palanka");
		GeoLocation saved = this.service.save(gl);
		GeoLocation found = this.service.findById(saved.getLocationId());
	
		assertTrue(gl.getPlace().equalsIgnoreCase(saved.getPlace()));
		assertEquals(found.getLocationId(), saved.getLocationId());
		
		boolean deleted = this.service.deleteById(found.getLocationId());
		found = this.service.findById(saved.getLocationId());
		
		assertTrue(deleted);
		assertNull(found);
	}
	
	@Test
	public void testUpdateAndDelete() {
		GeoLocation gl = new GeoLocation(22.2, 11.11, "Backa Palanka");
		GeoLocation saved = this.service.save(gl);
	
		saved.setPlace("Gajdobra");	
		
		GeoLocation updated = this.service.update(saved, saved.getLocationId());
		assertNotNull(updated);
		assertTrue(saved.getPlace().equalsIgnoreCase(updated.getPlace()));
		assertEquals(saved.getLocationId(), updated.getLocationId());
		
		boolean deleted = this.service.deleteById(updated.getLocationId());
		GeoLocation found = this.service.findById(saved.getLocationId());
		
		assertTrue(deleted);
		assertNull(found);
	}


}
