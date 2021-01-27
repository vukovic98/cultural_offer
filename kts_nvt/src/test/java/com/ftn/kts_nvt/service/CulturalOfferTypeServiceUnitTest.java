package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.CulturalOfferTypeRepository;
import com.ftn.kts_nvt.services.CulturalOfferTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferTypeServiceUnitTest {

	@Autowired
	private CulturalOfferTypeService service;
	
	@MockBean
	private CulturalOfferTypeRepository repository;
	
	@Before
	public void setup() {
		ArrayList<CulturalOfferType> types = new ArrayList<CulturalOfferType>();
		//Long id, String name, CulturalOfferCategory category
		CulturalOfferType type1 = new CulturalOfferType(1L, "type1", new CulturalOfferCategory(1L, "cat1", new ArrayList<>()));
		CulturalOfferType type2 = new CulturalOfferType(2L, "type2", new CulturalOfferCategory(1L, "cat1", new ArrayList<>()));
		CulturalOfferType type3 = new CulturalOfferType(3L, "type3", new CulturalOfferCategory(2L, "cat2", new ArrayList<>()));
		CulturalOfferType type4 = new CulturalOfferType(4L, "type4", new CulturalOfferCategory(2L, "cat2", new ArrayList<>()));
		types.add(type1);
		types.add(type2);
		types.add(type3);
		types.add(type4);
		Pageable pageable = PageRequest.of(0,5);
        Page<CulturalOfferType> typesPage = new PageImpl<>(types, pageable, 4);
        
        given(repository.findAll()).willReturn(types);
        given(repository.findAll(pageable)).willReturn(typesPage);
        
        given(repository.findById(1L)).willReturn(java.util.Optional.of(type1));
        given(repository.findById(10L)).willReturn(java.util.Optional.empty());
        
        //given(repository.findByCategoryId(1L)).willReturn((ArrayList<CulturalOfferType>) Arrays.asList(type1, type2));
        given(repository.findByName("type1")).willReturn(type1);
        
        CulturalOfferType savedType = new CulturalOfferType(1L, "type1", new CulturalOfferCategory(1L, "cat1", new ArrayList<>()));
        given(repository.save(type1)).willReturn(savedType);
        
        doNothing().when(repository).delete(type1);
        doNothing().when(repository).deleteById(1L);
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<CulturalOfferType> found = this.service.findAll(pageable);

		verify(this.repository, times(1)).findAll(pageable);
		assertEquals(4, found.getNumberOfElements());
	}
	
	@Test
	public void testFindAll() {
		List<CulturalOfferType> found = service.findAll();
		
		verify(this.repository, times(1)).findAll();
		assertEquals(4, found.size());
	}
	
	@Test
	public void testFindById() {
		CulturalOfferType found = service.findById(1L);
		
		verify(repository, times(1)).findById(1L);
		assertEquals(1L, found.getId());
	}
	
	@Test
	public void testFindByIdNotFound() {
		CulturalOfferType found = service.findById(10L);
		verify(repository, times(1)).findById(10L);
		assertNull(found);
	}
	
	@Test
	public void testFindByNameId() {
		CulturalOfferType found = service.findByName("type1");
		
		verify(repository, times(1)).findByName("type1");
		assertEquals(1L, found.getId());
	}
	
	@Test
	public void testFindByNameNotFound() {
		CulturalOfferType found = service.findByName("type10");		
		verify(repository, times(1)).findByName("type10");
		assertNull(found);
	}
}
