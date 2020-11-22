package com.ftn.kts_nvt.beans;

import java.util.ArrayList;

public abstract class CulturalOffer {
	private Long id;
	private String name;
	private ArrayList<String> images;
	private String description;

	public CulturalOffer() {
		super();
	}

	public CulturalOffer(String name, ArrayList<String> images, String description) {
		super();
		this.name = name;
		this.images = images;
		this.description = description;
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

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
