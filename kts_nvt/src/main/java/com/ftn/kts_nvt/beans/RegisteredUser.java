package com.ftn.kts_nvt.beans;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "registered_user")
public class RegisteredUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ElementCollection
	@CollectionTable(name = "registeredUser_comments", joinColumns = @JoinColumn(name = "user_id"))
	private List<Comment> comments;

	@ManyToMany(mappedBy = "subscribedUsers")
	private List<CulturalOffer> culturalOffers;

	@Column(name = "verified", nullable = false)
	private boolean verified = false;

	public RegisteredUser() {

	}
	
	

	public RegisteredUser(String firstName, String lastName, String email, String password, List<Comment> comments,
			List<CulturalOffer> culturalOffers, boolean verified) {
		super(firstName, lastName, email, password);
		this.comments = comments;
		this.culturalOffers = culturalOffers;
		this.verified = verified;
	}



	public RegisteredUser(List<Comment> comments, List<CulturalOffer> culturalOffers) {
		super();
		this.comments = comments;
		this.culturalOffers = culturalOffers;
		this.verified = false;
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

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

}
