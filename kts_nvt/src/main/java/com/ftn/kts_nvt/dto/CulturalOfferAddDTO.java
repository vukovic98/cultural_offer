package com.ftn.kts_nvt.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.ftn.kts_nvt.beans.GeoLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CulturalOfferAddDTO {

	String name;
	String description;
	Long type;
	GeoLocation location;
	@Override
	public String toString() {
		return "CulturalOfferAddDTO [name=" + name + ", description=" + description
				+ ", typeId=" + type + ", location=" + location + "]";
	}
}
