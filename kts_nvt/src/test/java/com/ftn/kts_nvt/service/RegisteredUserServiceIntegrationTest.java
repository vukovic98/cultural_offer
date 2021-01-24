package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.CommentRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.services.RegisteredUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RegisteredUserServiceIntegrationTest {

	@Autowired
	private RegisteredUserService registeredUserService;
	
	@Autowired
	private CulturalOfferRepository offerRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	@Test
	public void testSaveDeleteEntity() throws Exception {
		
		RegisteredUser found;
		
		RegisteredUser r = new RegisteredUser("Registered", "User", "test@test.com", "1234", new ArrayList<Comment>(), new ArrayList<CulturalOffer>(), true);
		
		
		RegisteredUser saved = registeredUserService.save(r);
		found = this.registeredUserService.findOne(saved.getId());
		
		assertTrue(r.equals(saved));
		assertEquals(found.getId(), saved.getId());
		
		this.registeredUserService.delete(saved.getId());
		found = this.registeredUserService.findOne(saved.getId());
		
		assertNull(found);
		
	}
	
	@Test
	public void testFindAll() {
		List<RegisteredUser> users = this.registeredUserService.findAll();
		
		assertNotNull(users);
		assertEquals(3, users.size());
		
	}
	
	@Test
	public void testFindAllPageable(){
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<RegisteredUser> usersPage = this.registeredUserService.findAll(pageable);
		
		assertEquals(3, usersPage.getNumberOfElements());
	}
	
	@Test
	public void testFindById() {
		
		long id = 1;
		
		RegisteredUser found = this.registeredUserService.findOne(id);
		
		assertNotNull(found);
		assertEquals(1L, found.getId());
		
	}
	
	@Test
	public void testFindByIdFail() {
		
		long id = 100;
		
		RegisteredUser found = this.registeredUserService.findOne(id);
		
		assertNull(found);
	}
	
	@Test
	public void testFindByEmail() {
		
		String email = "a4@a";
		
		RegisteredUser found = this.registeredUserService.findOneByEmail(email);
		
		assertNotNull(found);
		assertEquals(email, found.getEmail());
		
	}
	
	@Test
	public void testFindByEmailFail(){
		
		String email = "znj@znj";
		
		RegisteredUser found = this.registeredUserService.findOneByEmail(email);
		
		assertNull(found);
		
	}
	
	@Test
	@Transactional
	public void testSubscribeUnsubscribe() {
		
		CulturalOffer offer = offerRepository.findById(1L).orElse(null);
		
		RegisteredUser user = this.registeredUserService.findOne(1L);
				
		boolean ok = this.registeredUserService.subscribe(user, offer.getId());
		
		assertEquals(user.getCulturalOffers().get(0).getName(), offer.getName());
		assertEquals(offer.getSubscribedUsers().get(0).getEmail(), user.getEmail());
		
		assertEquals(1, user.getCulturalOffers().size());
		assertEquals(1, offer.getSubscribedUsers().size());
		
		assertTrue(ok);
		
		
		
		boolean unsubOk = this.registeredUserService.unsubscribe(user, offer.getId());
		
		assertTrue(unsubOk);
		
		assertEquals(0, user.getCulturalOffers().size());
		assertEquals(0, offer.getSubscribedUsers().size());
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		RegisteredUser found = this.registeredUserService.findOneByEmail("a4@a");
		
		String oldFirst = found.getFirstName();
		String oldLast = found.getLastName();
		
		
		found.setFirstName("Update");
		found.setLastName("UpdateLast");
		
		RegisteredUser updated = this.registeredUserService.update(found, found.getId());
		
		assertNotNull(updated);
		assertTrue(found.getFirstName().equalsIgnoreCase(updated.getFirstName()));
		assertTrue(found.getLastName().equalsIgnoreCase(updated.getLastName()));
		assertEquals(found.getId(), updated.getId());
		
		RegisteredUser found2 = this.registeredUserService.findOneByEmail("a4@a");
		
		found.setFirstName(oldFirst);
		found.setLastName(oldLast);
		
		RegisteredUser updated2 = this.registeredUserService.update(found2, found2.getId());
		
		assertNotNull(updated2);
		assertTrue(found2.getFirstName().equalsIgnoreCase(updated.getFirstName()));
		assertTrue(found2.getLastName().equalsIgnoreCase(updated.getLastName()));
		assertEquals(found2.getId(), updated2.getId());
	}
	
}
