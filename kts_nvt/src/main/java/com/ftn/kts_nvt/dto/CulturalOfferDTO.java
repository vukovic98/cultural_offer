package com.ftn.kts_nvt.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Image;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class CulturalOfferDTO {
	private long id;

	@NotBlank(message = "Name should not be blank!")
	private String name;

	private List<Image> images;

	@NotBlank(message = "Location should exist!")
	private GeoLocation location;

	@NotBlank(message = "You should add description!")
	private String description;

	private double avgGrade;

	private double subscribersCount;

	public CulturalOfferDTO() {
		super();
	}

	public CulturalOfferDTO(long id, @NotBlank(message = "Name should not be blank!") String name, List<Image> images,
			@NotBlank(message = "Location should exist!") GeoLocation location,
			@NotBlank(message = "You should add description!") String description, double avgGrade,
			double subscribersCount) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.location = location;
		this.description = description;
		this.avgGrade = avgGrade;
		this.subscribersCount = subscribersCount;
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

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}

	public double getSubscribersCount() {
		return subscribersCount;
	}

	public void setSubscribersCount(double subscribersCount) {
		this.subscribersCount = subscribersCount;
	}

	@Override
	public String toString() {
		return "CulturalOfferDTO [id=" + id + ", name=" + name + ", images=" + images + ", location=" + location
				+ ", description=" + description + ", avgGrade=" + avgGrade + ", subscribersCount=" + subscribersCount
				+ "]";
	}

}
