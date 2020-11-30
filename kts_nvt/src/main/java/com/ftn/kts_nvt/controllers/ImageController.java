package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.services.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Image> findById(@PathVariable("id") long id) {
		Image found = this.imageService.findById(id);

		if (found != null)
			return new ResponseEntity<>(found, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<ArrayList<Image>> findAll() {
		ArrayList<Image> images = this.imageService.findAll();
		if (!images.isEmpty())
			return new ResponseEntity<ArrayList<Image>>(images, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody Image image) {
		//TODO provera poslate slike
		Image created = this.imageService.save(image);
		if (created != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> delete(@RequestBody Image image) {

		boolean deleted = this.imageService.delete(image);

		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {

		boolean deleted = this.imageService.deleteById(id);

		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@RequestBody Image image, @PathVariable("id") long id) {
		//TODO provera unete slike
		Image updatedImage = this.imageService.update(image, id);
		if (updatedImage != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}