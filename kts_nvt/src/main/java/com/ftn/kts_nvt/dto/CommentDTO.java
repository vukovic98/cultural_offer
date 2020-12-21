package com.ftn.kts_nvt.dto;


public class CommentDTO {
	private Long id;
	private String content;


	public CommentDTO() {
		super();
	}

	public CommentDTO(Long id, String content) {
		super();
		this.id = id;
		this.content = content;

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

	

}
