package com.ftn.kts_nvt.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "geo_location")
public class GeoLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long locationId;

	@NotBlank(message = "Latitude should exist!")
	@Column(name = "latitude", nullable = false)
	private double latitude;

	@NotBlank(message = "Longitude should exist!")
	@Column(name = "longitude", nullable = false)
	private double longitude;

	@NotBlank(message = "Place should exist!")
	@Column(name = "place")
	private String place;

	public GeoLocation() {
		super();
	}

	public GeoLocation(double latitude, double longitude, String place) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.place = place;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
