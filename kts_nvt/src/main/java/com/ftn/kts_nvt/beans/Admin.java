package com.ftn.kts_nvt.beans;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {
	private Collection<CulturalOffer> culturalOffers;
	
	public Admin() {
		
	}
	public Admin(Collection<CulturalOffer> culturalOffers) {
		super();
		this.culturalOffers = culturalOffers;
	}

	public Collection<CulturalOffer> getCulturalOffers() {
		return culturalOffers;
	}

	public void setCulturalOffers(Collection<CulturalOffer> culturalOffers) {
		this.culturalOffers = culturalOffers;
	}

}
