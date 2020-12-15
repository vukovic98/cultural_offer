package com.ftn.kts_nvt.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.ftn.kts_nvt.beans.GeoLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CulturalOfferAddDTO {

	String name;
	String description;
	Long type;
	GeoLocation location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
				+ location + "]";
	}
}
