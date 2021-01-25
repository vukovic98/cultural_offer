package com.ftn.kts_nvt.beans;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cultural_offer_category")
public class CulturalOfferCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "culturalOfferCategory_ID")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@ElementCollection
	@CollectionTable(name = "culturalOfferCategory_types", joinColumns = @JoinColumn(name = "culturalOfferCategory_ID"))
	private List<CulturalOfferType> types;

	public CulturalOfferCategory() {
		super();
	}

	public CulturalOfferCategory(Long id, String name, List<CulturalOfferType> types) {
		super();
		this.id = id;
		this.name = name;
		this.types = types;
	}

	public CulturalOfferCategory(String name) {
		super();
		this.name = name;
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
		return "CulturalOfferCategory [id=" + id + ", name=" + name + ", types=" + types + "]";
	}
}
