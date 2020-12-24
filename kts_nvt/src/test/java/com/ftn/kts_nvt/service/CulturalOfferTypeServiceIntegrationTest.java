package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.services.CulturalOfferCategoryService;
import com.ftn.kts_nvt.services.CulturalOfferTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferTypeServiceIntegrationTest {

	@Autowired
	private CulturalOfferTypeService service;

	@Autowired
	private CulturalOfferCategoryService categoryService;
	 
	@Test
	public void testFindAll() {
		List<CulturalOfferType> found = service.findAll();
		assertEquals(4, found.size());
	}
	
	@Test
	public void testFindAllByCategoryId() {
		List<CulturalOfferType> found = service.findAll(2L);
		assertEquals(2, found.size());
		
		found = service.findAll(10L);
		assertEquals(0, found.size());
	}
	
	@Test
    public void testFindAllPageable() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<CulturalOfferType> found = service.findAll(pageable);
        assertEquals(4, found.getNumberOfElements());
    }
	
	@Test
	public void testFindById() {
		CulturalOfferType found = service.findById(1L);
	    assertEquals(Long.valueOf(1), found.getId());
	    assertEquals("Festival", found.getName());
	}
	
	@Test
	public void testFindByIdFail() {
		CulturalOfferType found = service.findById(10L);
	    assertEquals(null, found);
	}
	
	@Test
	public void testCreateAndDelete() throws Exception {
		CulturalOfferType type = new CulturalOfferType();
		type.setName("Museum");
		//INSERT INTO kts_nvt_test.cultural_offer_category VALUES (2,'Institution');
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
	public void testCreateNameExist() {
		CulturalOfferType type = new CulturalOfferType();
		type.setName("Festival");
		CulturalOfferType created = service.save(type);
		assertEquals(null, created);
	}
	
	@Test
	public void testUpdateNameExist() {
		CulturalOfferType type = service.findById(2L);
		type.setName("Festival");
		type.setCategory(categoryService.findOne(2L));
		
		CulturalOfferType changed = service.update(type, 2L);
		
	    assertEquals(null, changed);
	}
	
	@Test
	public void testUpdate() throws Exception {
		CulturalOfferType type = service.findById(1L);
		type.setName("Type1");
		type.setCategory(categoryService.findOne(2L));
		
		CulturalOfferType changed = service.update(type, 1L);
		
		assertNotNull(changed); 
	    assertEquals("Type1", changed.getName());
	    assertEquals(Long.valueOf(2), changed.getCategory().getId());
	}
}
