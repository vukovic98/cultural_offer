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
import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.repositories.GradeRepository;
import com.ftn.kts_nvt.services.GradeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class GradeServiceUnitTest {

	@Autowired
	private GradeService service;
	
	@MockBean
	private GradeRepository repository;
	
	@Before
	public void setup() {
		ArrayList<Grade> grades = new ArrayList<Grade>();
		Grade grade1 = new Grade(1L, 5, new RegisteredUser(1L), new CulturalOffer(1L));
		Grade grade2 = new Grade(2L, 2, new RegisteredUser(1L), new CulturalOffer(2L));
		Grade grade3 = new Grade(3L, 4, new RegisteredUser(2L), new CulturalOffer(1L));
		Grade grade4 = new Grade(4L, 1, new RegisteredUser(2L), new CulturalOffer(2L));
		grades.add(grade1);
		grades.add(grade2);
		grades.add(grade3);
		grades.add(grade4);
		Pageable pageable = PageRequest.of(0,5);
        Page<Grade> gradesPage = new PageImpl<>(grades, pageable, 4);
        
        given(repository.findAll()).willReturn(grades);
        given(repository.findAll(pageable)).willReturn(gradesPage);
        
        given(repository.findById(1L)).willReturn(java.util.Optional.of(grade1));
        given(repository.findById(10L)).willReturn(java.util.Optional.empty());
        
        given(repository.findGradesByUser_Id(1L)).willReturn(Arrays.asList(grade1, grade2));
        given(repository.findGradesByCulturalOffer_Id(2L)).willReturn(Arrays.asList(grade2, grade4));
        given(repository.findGradeByUser_IdAndCulturalOffer_Id(1L, 1L)).willReturn(java.util.Optional.of(grade1));

        Grade savedGrade = new Grade(1L, 5, new RegisteredUser(1L), new CulturalOffer(1L));
        given(repository.save(grade1)).willReturn(savedGrade);
        
        doNothing().when(repository).delete(grade1);
        doNothing().when(repository).deleteById(1L);
	}
	
	@Test
	public void testFindAll() {
		List<Grade> found = service.findAll();
		
		verify(this.repository, times(1)).findAll();
		assertEquals(4, found.size());
	}
	
	@Test
	public void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0,5);
        Page<Grade> found = service.findAll(pageable);
	
        verify(repository, times(1)).findAll(pageable);
        assertEquals(4, found.getNumberOfElements());
	}

	@Test
	public void testFindById() {
		Grade found = service.findOne(1L);
		
		verify(repository, times(1)).findById(1L);
		assertEquals(1L, found.getId());
	}
	
	@Test
	public void testFindByIdNotFound() {
		Grade found = service.findOne(10L);
		verify(repository, times(1)).findById(10L);
		assertNull(found);
	}
	
	@Test
	public void testFindByUser() {
		List<Grade> grades = service.findByUser(1L);
		verify(repository, times(1)).findGradesByUser_Id(1L);
		assertEquals(2, grades.size());
	}
	
	@Test
	public void testFindByUserFail() {
		List<Grade> grades = service.findByUser(10L);
		verify(repository, times(1)).findGradesByUser_Id(10L);
		assertEquals(0, grades.size());
	}
	
	@Test
	public void testFindByOffer() {
		List<Grade> grades = service.findByOffer(2L);
		verify(repository, times(1)).findGradesByCulturalOffer_Id(2L);
		assertEquals(2, grades.size());
	}
	
	@Test
	public void testFindByOfferFail() {
		List<Grade> grades = service.findByOffer(10L);
		verify(repository, times(1)).findGradesByCulturalOffer_Id(10L);
		assertEquals(0, grades.size());
	}
		
	@Test
	public void testDelete() throws Exception {
		service.delete(1L);
		verify(repository, times(1)).findById(1L);
	}
	

}
