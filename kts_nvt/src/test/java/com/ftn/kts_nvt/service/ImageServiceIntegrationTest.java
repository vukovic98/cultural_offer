package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.services.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ImageServiceIntegrationTest {

	 @Autowired
	 private ImageService imageService;
	 
	 @Test
	 public void testFindAll() {
		 List<Image> found = imageService.findAll();
	     assertEquals(2, found.size());
	 }
	 
	 @Test
	 public void testFindById() {
		 Image found = imageService.findById(1L);
	     assertEquals(Long.valueOf(1), found.getId());
	 }
	 
	 @Test
	 public void testFindByIdFail() {
		 Image found = imageService.findById(10L);
	     assertEquals(null, found);
	 }
	 
	 @Test
	 public void testCreate() throws Exception {
		 Image image = new Image(4L, "4".getBytes());
		 Image created = imageService.save(image);

	     assertEquals(image, created);
	 }
	 
	 @Test
	 public void testDelete() throws Exception {
		 imageService.deleteById(1L);

	     assertEquals(2, imageService.findAll().size());
	     assertEquals(null, imageService.findById(1L));
	 }
	 
}
