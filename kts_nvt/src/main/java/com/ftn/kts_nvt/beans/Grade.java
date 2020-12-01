package com.ftn.kts_nvt.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grade_ID")
	private Long id;

	@Column(name = "value", nullable = false)
	private int value;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private RegisteredUser user;

	@ManyToOne
	@JoinColumn(name = "culturalOffer_id", nullable = false)
	private CulturalOffer culturalOffer;

	public Grade() {
		super();
	}
	public Grade(int value, RegisteredUser user, CulturalOffer culturalOffer) {
		super();
		this.value = value;
		this.user = user;
		this.culturalOffer = culturalOffer;
	}
	public Grade(Long id, int value, RegisteredUser user, CulturalOffer culturalOffer) {
		super();
		this.id = id;
		this.value = value;
		this.user = user;
		this.culturalOffer = culturalOffer;
	}

	public Long getId() {
		return id;
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

}
