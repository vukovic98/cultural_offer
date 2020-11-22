package com.ftn.kts_nvt.beans;

import java.util.ArrayList;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "cultural_offer")
@Embeddable
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CulturalOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "culturalOffer_ID")
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@ElementCollection
	@CollectionTable(name = "culturalOffer_images", joinColumns = @JoinColumn(name = "culturalOffer_ID"))
	private ArrayList<Image> images;
	
	@Column(name = "description", nullable = true)
	private String description;

	public CulturalOffer() {
		super();
	}

	public CulturalOffer(String name, ArrayList<Image> images, String description) {
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

	public ArrayList<Image> getImages() {
		return images;
	}

	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
