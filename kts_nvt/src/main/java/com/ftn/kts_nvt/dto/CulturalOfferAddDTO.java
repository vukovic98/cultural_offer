package com.ftn.kts_nvt.dto;

import java.util.ArrayList;

import com.ftn.kts_nvt.beans.GeoLocation;

public class CulturalOfferAddDTO {

	String name;
	String description;
	Long type;
	GeoLocation location;
	ArrayList<byte[]> images;

	public CulturalOfferAddDTO() {
		super();
	}

	public CulturalOfferAddDTO(String name, String description, Long type, GeoLocation location,
			ArrayList<byte[]> images) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.location = location;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<byte[]> getImages() {
		return images;
	}

	public void setImages(ArrayList<byte[]> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "CulturalOfferAddDTO [name=" + name + ", description=" + description + ", typeId=" + type + ", location="
				+ location + ", images: " + images + "]";
	}
}
