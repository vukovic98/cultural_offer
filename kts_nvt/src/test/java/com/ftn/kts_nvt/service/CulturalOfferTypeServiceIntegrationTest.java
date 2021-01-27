package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

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

import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.services.CulturalOfferCategoryService;
import com.ftn.kts_nvt.services.CulturalOfferTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulturalOfferTypeServiceIntegrationTest {

	@Autowired
	private CulturalOfferTypeService service;

	@Autowired
	private CulturalOfferCategoryService categoryService;
	 
	@Test
	public void a_testFindAll() {
		List<CulturalOfferType> found = service.findAll();
		assertEquals(4, found.size());
	}
	
	@Test
	public void b_testFindAllByCategoryId() {
		List<CulturalOfferType> found = service.findAll(2L);
		assertEquals(2, found.size());
		
		found = service.findAll(10L);
		assertEquals(0, found.size());
	}
	
	@Test
    public void c_testFindAllPageable() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<CulturalOfferType> found = service.findAll(pageable);
        assertEquals(4, found.getNumberOfElements());
    }
	
	@Test
	public void d_testFindById() {
		CulturalOfferType found = service.findById(2L);
	    assertEquals(Long.valueOf(2), found.getId());
	    assertEquals("Festival", found.getName());
	}
	
	@Test
	public void e_testFindByIdFail() {
		CulturalOfferType found = service.findById(10L);
	    assertEquals(null, found);
	}
		
	@Test
	public void f_testCreateNameExist() {
		CulturalOfferType type = new CulturalOfferType();
		type.setName("Festival");
		CulturalOfferType created = service.save(type);
		assertEquals(null, created);
	}
	
	@Test
	public void g_testUpdate() throws Exception {
		CulturalOfferType type = service.findById(2L);
		type.setName("Type111");
		type.setCategory(categoryService.findOne(1L));
		CulturalOfferType changed = service.update(type, 2L);
		assertNotNull(changed); 
	    assertEquals("Type111", changed.getName());
	    assertEquals(Long.valueOf(1), changed.getCategory().getId());
	    
	    //rollback
	    type.setName("Festival");
		type.setCategory(categoryService.findOne(1L));
		changed = service.update(type, 2L);
		assertNotNull(changed); 
	    assertEquals("Festival", changed.getName());
	    assertEquals(Long.valueOf(1), changed.getCategory().getId());
	    
	}
	
	@org.springframework.transaction.annotation.Transactional
	@Test
	public void h_testCreateAndDelete() throws Exception {
		CulturalOfferType type = new CulturalOfferType();
		type.setName("Museum");
		type.setCategory(categoryService.findOne(2L));
		CulturalOfferType created = service.save(type);
		
	    assertEquals(type.getName(), created.getName());
	    assertEquals(type.getId(), created.getId());
	    assertEquals(type, created);
	    
	    boolean ok = service.deleteById(created.getId());
	    CulturalOfferType found = service.findById(created.getId());
			
	    assertTrue(ok);
	    assertEquals(null, found);
	}

	@Test
	public void y_testUpdateFail() throws Exception {
		CulturalOfferType type = service.findById(2L);
		type.setName("Festival");
		type.setCategory(categoryService.findOne(1L));
		CulturalOfferType changed = service.update(type, 2L);
		assertNull(changed); 
	}
}
