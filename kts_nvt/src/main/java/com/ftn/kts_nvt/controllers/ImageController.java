package com.ftn.kts_nvt.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.services.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping("/upload")
	public ResponseEntity<?> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Image img = new Image(compressBytes(file.getBytes()));
		imageService.save(img);
		System.out.println("imageService.save");
	    //return ResponseEntity.status(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping(path = { "/get/{imageId}" })
	public ResponseEntity<Image> getImage(@PathVariable("imageId") Long imageId) {
		final Image retrievedImage = imageService.findById(imageId);
		System.out.println("imageService find by id = " + imageId);

		if(retrievedImage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Image img = new Image(decompressBytes(retrievedImage.getPicByte()));
		System.out.println("image not null retun it");

		return new ResponseEntity<>(img, HttpStatus.FOUND);
	}

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
		    outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
			return outputStream.toByteArray();
	}
	
	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
		        outputStream.write(buffer, 0, count);
			}
		    outputStream.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (DataFormatException e) {
			System.out.println(e.getMessage());
		}
		return outputStream.toByteArray();
	}

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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> create(@Valid @RequestBody Image image) {
		//TODO provera poslate slike
		Image created = this.imageService.save(image);
		if (created != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping(consumes = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> delete(@RequestBody Image image) {

		boolean deleted = this.imageService.delete(image);

		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {

		boolean deleted = this.imageService.deleteById(id);

		if (deleted)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@PutMapping(path = "/{id}", consumes = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> update(@RequestBody Image image, @PathVariable("id") long id) {
		//TODO provera unete slike
		Image updatedImage = this.imageService.update(image, id);
		if (updatedImage != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
