package com.ftn.kts_nvt.dto;

import java.util.List;

import com.ftn.kts_nvt.beans.Image;

public class CommentDTO {
	private Long id;
	private String content;
	private List<Image> images;
	private int grade;

	public CommentDTO() {
		super();
	}

	public CommentDTO(Long id, String content, List<Image> images, int grade) {
		super();
		this.id = id;
		this.content = content;
		this.images = images;
		this.grade = grade;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
