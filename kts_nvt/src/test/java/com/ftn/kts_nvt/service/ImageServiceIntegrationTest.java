package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		 List<Image> images = imageService.findAll();
		 assertNotNull(images);
	     assertEquals(2, images.size());
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
	 public void testCreateAndDelete() throws Exception {
		 Image image = new Image("4".getBytes());
		 Image saved = imageService.save(image);

	     assertEquals(image.getId(), saved.getId());
	     
	     boolean ok = this.imageService.deleteById(saved.getId());
	     Image found = this.imageService.findById(saved.getId());
			
	     assertTrue(ok);
	     assertEquals(null, found);
	 }	
}
