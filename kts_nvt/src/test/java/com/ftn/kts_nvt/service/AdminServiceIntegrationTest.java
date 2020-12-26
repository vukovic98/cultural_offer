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

import com.ftn.kts_nvt.beans.Admin;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.services.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AdminServiceIntegrationTest {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CulturalOfferRepository offerRepository;
	
	
	
	@Test
	public void testSaveDeleteEntity() throws Exception {
		
		CulturalOffer co = this.offerRepository.findById(1L).orElse(null);
		Admin found;
		
		Admin a = new Admin("Test", "Testic", "ad@ad", "1234", new ArrayList<CulturalOffer>());
		
		a.getCulturalOffers().add(co);
		
		Admin saved = adminService.save(a);
		found = this.adminService.findOne(saved.getId());
		
		assertTrue(a.equals(saved));
		assertEquals(found.getId(), saved.getId());
		
		this.adminService.delete(saved.getId());
		found = this.adminService.findOne(saved.getId());
		
		assertNull(found);
		
	}
	
	@Test
	public void testFindAll() {
		List<Admin> users = this.adminService.findAll();
		
		assertNotNull(users);
		assertEquals(4, users.size());
		
	}
	
	@Test
	public void testFindAllPageable(){
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<Admin> usersPage = this.adminService.findAll(pageable);
		
		assertEquals(4, usersPage.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		
		long id = 5;
		
		Admin found = this.adminService.findOne(id);
		
		assertNotNull(found);
		assertEquals(id, found.getId());
		
	}
	
	@Test
	public void testFindByIdFail() {
		
		long id = 100;
		
		Admin found = this.adminService.findOne(id);
		
		assertNull(found);
	}
	
	@Test
	public void testFindByEmail() {
		
		String email = "ad@ad";
		
		Admin found = this.adminService.findOneByEmail(email);
		
		assertNotNull(found);
		assertEquals(email, found.getEmail());
		
	}
	
	@Test
	public void testFindByEmailFail(){
		
		String email = "znj@znj";
		
		Admin found = this.adminService.findOneByEmail(email);
		
		assertNull(found);
		
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		
		
		Admin found = this.adminService.findOneByEmail("ad4@ad");
		
		String oldFirst = found.getFirstName();
		String oldLast = found.getLastName();
		
		
		found.setFirstName("Update");
		found.setLastName("UpdateLast");
		
		Admin updated = this.adminService.update(found, found.getId());
		
		assertNotNull(updated);
		assertTrue(found.getFirstName().equalsIgnoreCase(updated.getFirstName()));
		assertTrue(found.getLastName().equalsIgnoreCase(updated.getLastName()));
		assertEquals(found.getId(), updated.getId());
		
		Admin found2 = this.adminService.findOneByEmail("ad4@ad");
		
		found.setFirstName(oldFirst);
		found.setLastName(oldLast);
		
		Admin updated2 = this.adminService.update(found2, found2.getId());
		
		assertNotNull(updated2);
		assertTrue(found2.getFirstName().equalsIgnoreCase(updated.getFirstName()));
		assertTrue(found2.getLastName().equalsIgnoreCase(updated.getLastName()));
		assertEquals(found2.getId(), updated2.getId());
	}
	
}
