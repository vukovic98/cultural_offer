package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.repositories.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public Image save(Image i) {
		return this.imageRepository.save(i);
	}

	public boolean deleteById(Long id) {
		boolean exists = this.imageRepository.existsById(id);

		if (exists) {
			this.imageRepository.deleteById(id);
		}
		return exists;
	}

	public boolean delete(Image i) {
		boolean exists = this.imageRepository.existsById(i.getId());

		if (exists) {
			this.imageRepository.delete(i);
		}
		return exists;
	}

	public ArrayList<Image> findAll() {
		return (ArrayList<Image>) this.imageRepository.findAll();
	}

	public Image findById(Long id) {
		Optional<Image> found = this.imageRepository.findById(id);

		if (found.isPresent()) {
			Image image = found.get();
			return image;
		} else
			return null;
	}

	public Image update(Image changedImage, long id) {

		Optional<Image> found = this.imageRepository.findById(id);

		if (found.isPresent()) {
			Image oldImage = found.get();
			oldImage.setUrl(changedImage.getUrl());
			return this.imageRepository.save(oldImage);
		} else
			return null;

	}

}
