package com.ftn.kts_nvt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;

public class GradeDTO {

	private Long id;
	@NotNull(message = "Value cannot be null.")
	private int value;
	@NotNull(message = "User cannot be null.")
	private RegisteredUser user;
	@NotNull(message = "CulturalOFfer cannot be null.")
	private CulturalOfferDTO culturalOffer;
	
	public GradeDTO() {}

	public GradeDTO(Long id, @NotNull(message = "Value cannot be null.") int value,
			@NotNull(message = "User cannot be null.") RegisteredUser user,
			@NotNull(message = "CulturalOFfer cannot be null.") CulturalOfferDTO culturalOffer) {
		super();
		this.id = id;
		this.value = value;
		this.user = user;
		this.culturalOffer = culturalOffer;
	}

	public GradeDTO(@NotNull(message = "Value cannot be null.") int value,
			@NotNull(message = "User cannot be null.") RegisteredUser user,
			@NotNull(message = "CulturalOFfer cannot be null.") CulturalOfferDTO culturalOffer) {
		super();
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

	public CulturalOfferDTO getCulturalOffer() {
		return culturalOffer;
	}

	public void setCulturalOffer(CulturalOfferDTO culturalOffer) {
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

	@Override
	public String toString() {
		return "GradeDTO [id=" + id + ", value=" + value + ", user=" + user + ", culturalOffer=" + culturalOffer + "]";
	}
	
}
