package com.ftn.kts_nvt.beans;

import java.util.ArrayList;

public class Monument extends CulturalOffer {
	private int year;

	public Monument() {
		super();
	}

	public Monument(String name, ArrayList<String> images, String description, int year) {
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
