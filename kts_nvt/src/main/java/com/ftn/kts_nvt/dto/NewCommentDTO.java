package com.ftn.kts_nvt.dto;

import com.ftn.kts_nvt.beans.Image;

public class NewCommentDTO {

	
	private Long offerId;
	private String content;
	private Image image;
	public NewCommentDTO() {
		super();
	}
	public NewCommentDTO(Long offerId, String content, Image image) {
		super();
		this.offerId = offerId;
		this.content = content;
		this.image = image;
	}
	public Long getOfferId() {
		return offerId;
	}
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
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
