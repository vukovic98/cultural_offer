package com.ftn.kts_nvt.beans;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "manifestation")
public class Manifestation extends CulturalOffer {
	
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	public Manifestation() {
		super();
	}

	public Manifestation(String name, ArrayList<Image> images, String description, LocalDate startDate,
			LocalDate endDate) {
		super(name, images, description);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
