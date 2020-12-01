package com.ftn.kts_nvt.dto;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;

public class GradeDTO {

	private Long id;
	private int value;
	private RegisteredUser user;
	private CulturalOffer culturalOffer;
	
	public GradeDTO() {}

	public GradeDTO(int value, RegisteredUser user, CulturalOffer culturalOffer) {
		super();
		this.value = value;
		this.user = user;
		this.culturalOffer = culturalOffer;
	}
	
	public GradeDTO(Long id, int value, RegisteredUser user, CulturalOffer culturalOffer) {
		super();
		this.id = id;
		this.value = value;
		this.user = user;
		this.culturalOffer = culturalOffer;
	}

	public Long getId() {
		return id;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public CulturalOffer getCulturalOffer() {
		return culturalOffer;
	}

	public void setCulturalOffer(CulturalOffer culturalOffer) {
		this.culturalOffer = culturalOffer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
