package com.ftn.kts_nvt.dto;

import javax.validation.constraints.NotBlank;

public class CulturalOfferTypeDTO {
	private long id;

	@NotBlank(message = "Name cannot be empty.")
	private String name;

	@NotBlank(message = "Category Id cannot be empty.")
	private long categoryId;

	@NotBlank(message = "Category name cannot be empty.")
	private String categoryName;

	public CulturalOfferTypeDTO() {
		super();
	}

	public CulturalOfferTypeDTO(long id, @NotBlank(message = "Name cannot be empty.") String name,
			@NotBlank(message = "Category Id cannot be empty.") long categoryId,
			@NotBlank(message = "Category name cannot be empty.") String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
