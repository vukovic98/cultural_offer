package com.ftn.kts_nvt.beans;

import java.util.ArrayList;

public class Institution extends CulturalOffer {
	private ArrayList<String> workingHours;

	public Institution() {
		super();
	}

	public Institution(String name, ArrayList<String> images, String description, ArrayList<String> workingHours) {
		super(name, images, description);
		this.workingHours = workingHours;
	}

	public ArrayList<String> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(ArrayList<String> workingHours) {
		this.workingHours = workingHours;
	}

}
