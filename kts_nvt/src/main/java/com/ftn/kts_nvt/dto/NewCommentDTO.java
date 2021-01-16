package com.ftn.kts_nvt.dto;


public class NewCommentDTO {

	private Long offerId;
	private String content;
	private byte[] image;

	public NewCommentDTO() {
		super();
	}

	public NewCommentDTO(Long offerId, String content, byte[] image) {
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}


}
