package com.ftn.kts_nvt.beans;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {
	
	@ElementCollection
	@CollectionTable(name = "admin_culturalOffers", joinColumns = @JoinColumn(name = "user_id"))
	private List<CulturalOffer> culturalOffers;
	
	public Admin() {
		
	}
	public Admin(List<CulturalOffer> culturalOffers) {
		super();
		this.culturalOffers = culturalOffers;
	}

	
	
	public Admin(String firstName, String lastName, String email, String password, List<CulturalOffer> culturalOffers) {
		super(firstName, lastName, email, password);
		this.culturalOffers = culturalOffers;
	}
	public List<CulturalOffer> getCulturalOffers() {
		return culturalOffers;
	}

	public void setCulturalOffers(List<CulturalOffer> culturalOffers) {
		this.culturalOffers = culturalOffers;
	}

}
