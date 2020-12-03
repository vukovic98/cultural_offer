package com.ftn.kts_nvt.beans;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "registered_user")
public class RegisteredUser extends User {
	
	@ElementCollection
	@CollectionTable(name = "registeredUser_comments", joinColumns = @JoinColumn(name = "user_id"))
	private List<Comment> comments;
	
	@ElementCollection
	@CollectionTable(name = "registeredUser_culturalOffers", joinColumns = @JoinColumn(name = "user_id"))
	private List<CulturalOffer> culturalOffers;
	
	public RegisteredUser() {
		
	}
	public RegisteredUser(List<Comment> comments, List<CulturalOffer> culturalOffers) {
		super();
		this.comments = comments;
		this.culturalOffers = culturalOffers;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<CulturalOffer> getCulturalOffers() {
		return culturalOffers;
	}
	public void setCulturalOffers(List<CulturalOffer> culturalOffers) {
		this.culturalOffers = culturalOffers;
	}
}
