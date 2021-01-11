package com.ftn.kts_nvt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddPostDTO {

	private Long id;

	@NotBlank(message = "Title cannot be empty.")
	private String title;

	@NotBlank(message = "Content cannot be empty.")
	private String content;

	@NotNull(message = "Cultural Offer ID cannot be empty.")
	private Long culturalOfferId;

	public AddPostDTO() {
		super();
	}

	public AddPostDTO(Long id, String title, String content, Long culturalOfferId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.culturalOfferId = culturalOfferId;
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

	public Long getCulturalOfferId() {
		return culturalOfferId;
	}

	public void setCulturalOfferId(Long culturalOfferId) {
		this.culturalOfferId = culturalOfferId;
	}

}
