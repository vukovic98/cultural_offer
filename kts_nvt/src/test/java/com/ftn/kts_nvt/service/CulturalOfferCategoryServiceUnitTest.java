package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
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

import com.ftn.kts_nvt.beans.CulturalOfferCategory;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;
import com.ftn.kts_nvt.services.CulturalOfferCategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferCategoryServiceUnitTest {

	@Autowired
	private CulturalOfferCategoryService culturalOfferCategoryService;

	@MockBean
	private CulturalOfferCategoryRepository culturalOfferCategoryRepository;

	@Before
	public void setup() {
		List<CulturalOfferCategory> categories = new ArrayList<CulturalOfferCategory>();

		// Category1- Manifestation
		List<CulturalOfferType> types = new ArrayList<CulturalOfferType>();
		CulturalOfferCategory category1 = new CulturalOfferCategory(1L, "Manifestation", types);
		CulturalOfferType t1 = new CulturalOfferType(1L, "Festival", category1);
		CulturalOfferType t2 = new CulturalOfferType(2L, "Type2", category1);
		types.add(t1);
		types.add(t2);
		category1.setTypes(types);

		// Category2- Institution
		List<CulturalOfferType> types2 = new ArrayList<CulturalOfferType>();
		CulturalOfferCategory category2 = new CulturalOfferCategory(2L, "Institution", types2);
		CulturalOfferType t11 = new CulturalOfferType(3L, "Type3", category2);
		CulturalOfferType t22 = new CulturalOfferType(4L, "Type4", category2);
		types2.add(t11);
		types2.add(t22);
		category2.setTypes(types2);

		categories.add(category1);
		categories.add(category2);

		Pageable pageable = PageRequest.of(0, 5);
		Page<CulturalOfferCategory> culturalOfferCategoriesPage = new PageImpl<>(categories, pageable, 2);

		//findById 1L
		CulturalOfferCategory savedCategory = new CulturalOfferCategory(1L, "Manifestation", types);
		savedCategory.setId(1L);
		
		// Definisanje ponasanja
		given(this.culturalOfferCategoryRepository.findAll()).willReturn(categories);
		given(this.culturalOfferCategoryRepository.findAll(pageable)).willReturn(culturalOfferCategoriesPage);
		given(this.culturalOfferCategoryRepository.findById(1L)).willReturn(java.util.Optional.of(savedCategory));
        given(this.culturalOfferCategoryRepository.findById(5L)).willReturn(java.util.Optional.empty());
	}

	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<CulturalOfferCategory> found = this.culturalOfferCategoryService.findAll(pageable);

		verify(this.culturalOfferCategoryRepository, times(1)).findAll(pageable);
		assertEquals(2, found.getNumberOfElements());
	}

	@Test
	public void testFindById() {
		CulturalOfferCategory found = this.culturalOfferCategoryService.findOne(1L);
		verify(this.culturalOfferCategoryRepository, times(1)).findById(1L);
		assertEquals(1L, found.getId());
	}

	@Test
	public void testFindByIdNotFound() {
		CulturalOfferCategory found = this.culturalOfferCategoryService.findOne(5L);
		verify(this.culturalOfferCategoryRepository, times(1)).findById(5L);
		assertNull(found);
	}

}
