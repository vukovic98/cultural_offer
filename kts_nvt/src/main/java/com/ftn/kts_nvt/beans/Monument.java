package com.ftn.kts_nvt.beans;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "monument")
@DiscriminatorValue("MONUMENT")
public class Monument extends CulturalOffer {
	
	@Column(name = "year")
	private int year;

	public Monument() {
		super();
	}

	public Monument(String name, ArrayList<Image> images, String description, int year) {
		super(name, images, description);
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
