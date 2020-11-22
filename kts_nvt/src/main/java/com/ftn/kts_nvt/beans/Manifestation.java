package com.ftn.kts_nvt.beans;

import java.time.LocalDate;
import java.util.ArrayList;

public class Manifestation extends CulturalOffer {
	private LocalDate startDate;
	private LocalDate endDate;

	public Manifestation() {
		super();
	}

	public Manifestation(String name, ArrayList<String> images, String description, LocalDate startDate,
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
