package com.ftn.kts_nvt.dto;

import java.time.LocalDate;
import java.util.List;

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Image;

public class ManifestationDTO {
	private long id;
	private String name;
	private List<Image> images;
	private GeoLocation location;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;

	public ManifestationDTO() {
		super();
	}

	public ManifestationDTO(long id, String name, List<Image> images, GeoLocation location, String description,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.location = location;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
