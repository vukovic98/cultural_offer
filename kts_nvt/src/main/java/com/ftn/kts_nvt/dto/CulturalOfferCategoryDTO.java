package com.ftn.kts_nvt.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.ftn.kts_nvt.beans.CulturalOfferType;

public class CulturalOfferCategoryDTO {

	private Long id;

	@NotBlank(message = "Name cannot be empty.")
	private String name;
	
	private List<CulturalOfferType> types;

	public CulturalOfferCategoryDTO() {}

	public CulturalOfferCategoryDTO(@NotBlank(message = "Name cannot be empty.") String name) {
		super();
		this.name = name;
	}
	public CulturalOfferCategoryDTO(Long id, 
								@NotBlank(message = "Name cannot be empty.") String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CulturalOfferCategoryDTO(Long id, 
							@NotBlank(message = "Name cannot be empty.") String name, 
							List<CulturalOfferType> types) {
		super();
		this.id = id;
		this.name = name;
		this.types = types;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CulturalOfferType> getTypes() {
		return types;
	}

	public void setTypes(List<CulturalOfferType> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "CulturalOfferCategoryDTO [id=" + id + ", name=" + name + ", types=" + types + "]";
	}
	
}