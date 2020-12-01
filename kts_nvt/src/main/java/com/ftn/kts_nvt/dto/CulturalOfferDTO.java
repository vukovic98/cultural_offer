package com.ftn.kts_nvt.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Image;

public class CulturalOfferDTO {
	private long id;
	
	@NotBlank(message = "Name should not be blank!")
	private String name;
	
	private List<Image> images;
	
	@NotBlank(message = "Location should exist!")
	private GeoLocation location;
	
	@NotBlank(message = "You should add description!")
	private String description;

	public CulturalOfferDTO() {
		super();
	}

	public CulturalOfferDTO(long id, String name, List<Image> images, GeoLocation location, String description) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.location = location;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
