package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.services.CulturalOfferService;
import com.ftn.kts_nvt.services.GradeService;
import com.ftn.kts_nvt.services.RegisteredUserService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GradeServiceIntegrationTest {

	@Autowired
    private GradeService service;

	@Autowired
	private CulturalOfferService culturalOfferService;
	
	@Autowired
	private RegisteredUserService userService;
	
	@Test
	public void testFindAll() {
		List<Grade> found = service.findAll();
		assertEquals(4, found.size());
	}
	
	@Test
    public void testFindAllPageable() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Grade> found = service.findAll(pageable);
        assertEquals(4, found.getNumberOfElements());
    }
	
	@Test
	public void testFindById() {
		Grade found = service.findOne(1L);
	    assertEquals(Long.valueOf(1), found.getId());
	    assertEquals(Long.valueOf(1L), found.getCulturalOffer().getId());
	    assertEquals(Long.valueOf(1L), found.getUser().getId());
	    assertEquals(5, found.getValue());
	}
	@Test
	public void testFindByIdFail() {
		Grade found = service.findOne(10L);
	    assertNull(found);
	}
	
	@Test
	public void findByUserId() {
		List<Grade> grades = service.findByUser(1L);
		assertEquals(2, grades.size());
		for(Grade g : grades) {
			assertEquals(Long.valueOf(1), g.getUser().getId());
		}
	}
	
	@Test
	public void findByUserIdFail() {
		List<Grade> grades = service.findByUser(10L);
		assertEquals(0, grades.size());
	}
	
	@Test
	public void findByOfferId() {
		List<Grade> grades = service.findByOffer(1L);
		assertEquals(2, grades.size());
		for(Grade g : grades) {
			assertEquals(Long.valueOf(1), g.getCulturalOffer().getId());
		}
	}
	
	@Test
	public void findByOfferIdFail() {
		List<Grade> grades = service.findByOffer(10L);
		assertEquals(0, grades.size());
	}
	
	@Transactional
	@Test
	public void x_testCreateAndDelete() throws Exception {
		Grade grade = new Grade();
		grade.setValue(4);
		grade.setCulturalOffer(culturalOfferService.findById(4L));
		grade.setUser(userService.findOne(4L));
		
		Grade created = service.create(grade);

		assertEquals(grade.getId(), created.getId());
	    assertEquals(grade.getValue(), created.getValue());
	    assertEquals(grade.getCulturalOffer().getId(), created.getCulturalOffer().getId());
	    assertEquals(grade.getUser().getId(), created.getUser().getId());
	    assertEquals(grade, created);
	    
	    service.delete(created.getId());
	    Grade found = service.findOne(created.getId());			
	    assertEquals(null, found);
	}
	
	@Test(expected = Exception.class)
	public void testDeleteFail() throws Exception {
		service.delete(10L);
	}
	
	@Test 
	public void testUpdate() throws Exception {
		Grade grade = service.findOne(1L);
		grade.setValue(1);
		Grade changedGrade = service.update(grade, grade.getId());
		assertNotNull(changedGrade);
		assertEquals(1, changedGrade.getValue());
		assertEquals(Long.valueOf(1), changedGrade.getId());
		
		//rollback
		grade.setValue(5);
		changedGrade = service.update(grade, grade.getId());
		assertNotNull(changedGrade);
		assertEquals(5, changedGrade.getValue());
		assertEquals(Long.valueOf(1), changedGrade.getId());
	}
	
	@Test(expected = Exception.class)
	public void testUpdateFail() throws Exception {
		Grade grade = service.findOne(1L);
		grade.setValue(5);
		Grade changedGrade = service.update(grade, 10L);
	}
	
}
