package com.ftn.kts_nvt.beans;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "registeredUser")
public class RegisteredUser extends User {
	private Collection<Comment> comments;
	private Collection<CulturalOffer> culturalOffers;
	
	public RegisteredUser() {
		
	}
	public RegisteredUser(Collection<Comment> comments, Collection<CulturalOffer> culturalOffers) {
		super();
		this.comments = comments;
		this.culturalOffers = culturalOffers;
	}
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	public Collection<CulturalOffer> getCulturalOffers() {
		return culturalOffers;
	}
	public void setCulturalOffers(Collection<CulturalOffer> culturalOffers) {
		this.culturalOffers = culturalOffers;
	}
}
