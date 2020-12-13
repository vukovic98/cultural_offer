package com.ftn.kts_nvt.dto;

import com.ftn.kts_nvt.beans.Image;

public class CommentUserDTO {
	private Long id;
	private String commenterEmail;
	private String commenterName;
	private String content;
	private Image image;
	private boolean approved;
	
	public CommentUserDTO() {
	}

	public CommentUserDTO(Long id, String commenterEmail, String commenterName, String content, Image image,
			boolean approved) {
		super();
		this.id = id;
		this.commenterEmail = commenterEmail;
		this.commenterName = commenterName;
		this.content = content;
		this.image = image;
		this.approved = approved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommenterEmail() {
		return commenterEmail;
	}

	public void setCommenterEmail(String commenterEmail) {
		this.commenterEmail = commenterEmail;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	

}
