package com.ftn.kts_nvt.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WorkingHourDTO {

	private Long id;
	
    @NotNull(message = "Day cannot be empty.")
	private DayOfWeek day;

    @NotNull(message = "StartTime cannot be empty.")
	private LocalTime startTime;

    @NotNull(message = "EndTime cannot be empty.")
	private LocalTime endTime;

    public WorkingHourDTO() {}
    
	public WorkingHourDTO(Long id, @NotNull(message = "Day cannot be empty.") DayOfWeek day,
			@NotNull(message = "StartTime cannot be empty.") LocalTime startTime,
			@NotNull(message = "EndTime cannot be empty.") LocalTime endTime) {
		super();
		this.id = id;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "WorkingHourDTO [id=" + id + ", day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
    
}
