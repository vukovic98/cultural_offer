package com.ftn.kts_nvt.dto;

import java.util.List;

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.beans.Institution;
import com.ftn.kts_nvt.beans.WorkingHour;

public class InstitutionDTO {
	private long id;
	private String name;
	private List<Image> images;
	private GeoLocation geoLocation;
	private String description;
	private List<WorkingHour> workingHours;

	public InstitutionDTO() {

	}

	public InstitutionDTO(long id, String name, List<Image> images, GeoLocation geoLocation, String description,
			List<WorkingHour> workingHours) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.geoLocation = geoLocation;
		this.description = description;
		this.workingHours = workingHours;
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

	public List<WorkingHour> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHour> workingHours) {
		this.workingHours = workingHours;
	}

}
