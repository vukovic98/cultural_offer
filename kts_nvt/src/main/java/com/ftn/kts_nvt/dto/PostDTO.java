package com.ftn.kts_nvt.dto;

import java.time.Instant;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ftn.kts_nvt.beans.CulturalOffer;

public class PostDTO {

	private Long id;

    @NotBlank(message = "Title cannot be empty.")
	private String title;

    @NotBlank(message = "Content cannot be empty.")
	private String content;

    @NotNull(message = "PostTime cannot be null.")
	private Instant postTime;
    
    @NotNull(message = "CulturalOffer cannot be null.")    
    private CulturalOffer offer;

    public PostDTO() {}
    
	public PostDTO(Long id, @NotBlank(message = "Title cannot be empty.") String title,
			@NotBlank(message = "Content cannot be empty.") String content,
			@NotNull(message = "PostTime cannot be null.") Instant postTime,
			@NotNull(message = "CulturalOffer cannot be null.") CulturalOffer offer) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.postTime = postTime;
		this.offer = offer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getPostTime() {
		return postTime;
	}

	public void setPostTime(Instant postTime) {
		this.postTime = postTime;
	}

	public CulturalOffer getOffer() {
		return offer;
	}

	public void setOffer(CulturalOffer offer) {
		this.offer = offer;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", title=" + title + ", content=" + content + ", postTime=" + postTime + ", offer="
				+ offer + "]";
	}
    
}
