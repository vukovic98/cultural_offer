package com.ftn.kts_nvt.dto;


import com.ftn.kts_nvt.beans.Image;

public class CommentDTO {
	private Long id;
	private String content;
	private Image image;

	public CommentDTO() {
		super();
	}

	public CommentDTO(Long id, String content, Image image) {
		super();
		this.id = id;
		this.content = content;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
