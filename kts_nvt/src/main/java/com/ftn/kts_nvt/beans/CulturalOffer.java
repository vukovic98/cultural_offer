package com.ftn.kts_nvt.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cultural_offer")
@Embeddable
public class CulturalOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "culturalOffer_ID")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@ElementCollection
	@CollectionTable(name = "culturalOffer_images", joinColumns = @JoinColumn(name = "culturalOffer_ID"))
	private List<Image> images;

	@ManyToMany
	@JoinTable(name = "users_culturalOffers", joinColumns = @JoinColumn(name = "culturalOffer_ID"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<RegisteredUser> subscribedUsers;

	@ElementCollection
	@CollectionTable(name = "culturalOffer_post", joinColumns = @JoinColumn(name = "culturalOffer_ID"))
	private List<Post> posts;

	@ManyToOne
	@JoinColumn(name = "geoLocation_id", nullable = false)
	private GeoLocation location;

	@ElementCollection()
	@CollectionTable(name = "culturalOffer_comment", joinColumns = @JoinColumn(name = "culturalOffer_ID"))
	private List<Comment> comments;

	@Column(name = "description", nullable = true)
	private String description;

	@ManyToOne
	@JoinColumn(name = "cultural_offer_type_id", nullable = false)
	private CulturalOfferType type;

	@ElementCollection
	@CollectionTable(name = "culturalOffer_grade", joinColumns = @JoinColumn(name = "culturalOffer_ID"))
	private List<Grade> grades;

	public CulturalOffer() {
		super();
	}

	public CulturalOffer(Long id) {
		super();
		this.id = id;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RegisteredUser> getSubscribedUsers() {
		return subscribedUsers;
	}

	public void setSubscribedUsers(List<RegisteredUser> subscribedUsers) {
		this.subscribedUsers = subscribedUsers;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public CulturalOfferType getType() {
		return type;
	}

	public void setType(CulturalOfferType type) {
		this.type = type;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(o == null || getClass() != o.getClass())
			return false;
		
	    CulturalOffer category = (CulturalOffer) o;
	    if (category.getId() == null || id == null) {
	        if(category.getName().equals(getName())){
	            return true;
	        }
	        return false;
	    }
	    return Objects.equals(id, category.getId());
	}
}
