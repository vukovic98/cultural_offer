package com.ftn.kts_nvt.service;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.repositories.ImageRepository;
import com.ftn.kts_nvt.services.ImageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ImageServiceUnitTest {

	@Autowired
	private ImageService service;
	
	@MockBean
	private ImageRepository repository;
	
	@Before
	public void setup() {
		ArrayList<Image> images = new ArrayList<Image>();
		Image img1 = new Image(1L, "1".getBytes());
		Image img2 = new Image(2L, "2".getBytes());
		images.add(img1);
		images.add(img2);
        given(repository.findAll()).willReturn(images);
        given(repository.findById(1L)).willReturn(java.util.Optional.of(img1));
        given(repository.findById(2L)).willReturn(java.util.Optional.of(img2));
        given(repository.findById(10L)).willReturn(java.util.Optional.empty());
        given(repository.save(img1)).willReturn(img1);
        doNothing().when(repository).delete(img1);
        doNothing().when(repository).deleteById(1L);
	}
	
	@Test
	public void testFindAll() {
		ArrayList<Image> images = service.findAll();
		
		verify(repository, times(1)).findAll();
		assertEquals(2, images.size());
	}

	@Test
	public void testFindById() {
		Image image = service.findById(1L);		
		verify(repository, times(1)).findById(1L);
		assertEquals(1L, image.getId());
	}
	
	@Test
	public void testFindByIdFail() {
		Image image = service.findById(10L);		
		verify(repository, times(1)).findById(10L);
		assertEquals(null, image);
	}
	
	@Test
	public void testCreate() {
		Image img = new Image("10".getBytes());
		Image created = service.save(img);
		verify(repository, times(1)).save(img);
	}
	
}
