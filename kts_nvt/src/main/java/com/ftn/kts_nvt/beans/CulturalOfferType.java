package com.ftn.kts_nvt.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cultural_offer_type")
public class CulturalOfferType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "culturalOfferType_ID")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "cultural_offer_category_id", nullable = false)
	private CulturalOfferCategory category;

	public CulturalOfferType() {
		super();
	}

	public CulturalOfferType(Long id, String name, CulturalOfferCategory category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
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

	public CulturalOfferCategory getCategory() {
		return category;
	}

	public void setCategory(CulturalOfferCategory category) {
		this.category = category;
	}

}
