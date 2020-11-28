package com.ftn.kts_nvt.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "institution")
@DiscriminatorValue("INSTITUTION")
public class Institution extends CulturalOffer {
	
	@ElementCollection
	@CollectionTable(name = "institution_workingHours", joinColumns = @JoinColumn(name = "institution_ID"))
	private List<WorkingHour> workingHours;

	public Institution() {
		super();
	}

	public Institution(String name, ArrayList<Image> images, String description, ArrayList<WorkingHour> workingHours) {
		super(name, images, description);
		this.workingHours = workingHours;
	}

	public List<WorkingHour> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHour> workingHours) {
		this.workingHours = workingHours;
	}

}
