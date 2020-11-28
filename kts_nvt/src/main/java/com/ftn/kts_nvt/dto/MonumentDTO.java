package com.ftn.kts_nvt.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.beans.WorkingHour;

public class MonumentDTO {

	private long id;
	@NotBlank(message = "Name cannot be empty.")
	private String name;

	@NotNull(message = "Image cannot be null.")
	private List<Image> images;
	
	@NotNull(message = "GeoLocation cannot be null.")
	private GeoLocation geoLocation;
	
	@NotBlank(message = "Description cannot be empty.")
	private String description;


	@NotNull(message = "Year cannot be null.")
	private int year;
		
	public MonumentDTO() {
		super();
	}

	public MonumentDTO(long id, @NotBlank(message = "Name cannot be empty.") String name,
			@NotNull(message = "Image cannot be null.") List<Image> images,
			@NotNull(message = "GeoLocation cannot be null.") GeoLocation geoLocation,
			@NotBlank(message = "Description cannot be empty.") String description,
			@NotNull(message = "Year cannot be null.") int year) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.geoLocation = geoLocation;
		this.description = description;
		this.year = year;
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

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "MonumentDTO [id=" + id + ", name=" + name + ", images=" + images + ", geoLocation=" + geoLocation
				+ ", description=" + description + ", year=" + year + "]";
	}
}
