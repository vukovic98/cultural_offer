package com.ftn.kts_nvt.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Image;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CulturalOfferDetailsDTO {

	private long id;
	
	@NotBlank(message = "Name should not be blank!")
	private String name;
	
	private List<Image> images;
	
	@NotBlank(message = "Location should exist!")
	private GeoLocation location;
	
	@NotBlank(message = "You should add description!")
	private String description;

	private List<PostDTO> posts;

	private List<CommentUserDTO> comments;

	private CulturalOfferTypeDTO type;

	private List<GradeDTO> grades;

	private double avgGrade;

	private double subscribersCount;
	
	private double postsCount;
	
	private double commentsCount;
	  
	public long getId() {
		return id;
	}


	public void setId(long id) {
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


	public GeoLocation getLocation() {
		return location;
	}


	public void setLocation(GeoLocation location) {
		this.location = location;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<PostDTO> getPosts() {
		return posts;
	}


	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}


	public List<CommentUserDTO> getComments() {
		return comments;
	}


	public void setComments(List<CommentUserDTO> comments) {
		this.comments = comments;
	}


	public CulturalOfferTypeDTO getType() {
		return type;
	}


	public void setType(CulturalOfferTypeDTO type) {
		this.type = type;
	}


	public List<GradeDTO> getGrades() {
		return grades;
	}


	public void setGrades(List<GradeDTO> grades) {
		this.grades = grades;
	}
	
	public double getAvgGrade() {
		return avgGrade;
	}


	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}


	public double getSubscribersCount() {
		return subscribersCount;
	}


	public void setSubscribersCount(double subscribersCount) {
		this.subscribersCount = subscribersCount;
	}


	public double getPostsCount() {
		return postsCount;
	}


	public void setPostsCount(double postsCount) {
		this.postsCount = postsCount;
	}


	public double getCommentsCount() {
		return commentsCount;
	}


	public void setCommentsCount(double commentsCount) {
		this.commentsCount = commentsCount;
	}


	@Override
	public String toString() {
		return "CulturalOfferDetailsDTO [id=" + id + ", name=" + name + ", images=" + images + ", location=" + location
				+ ", description=" + description + ", posts=" + posts + ", comments=" + comments + ", type=" + type
				+ ", grades=" + grades + ", avgGrade=" + avgGrade + ", subscribersCount=" + subscribersCount
				+ ", postsCount=" + postsCount + ", commentsCount=" + commentsCount + "]";
	}
	
}
