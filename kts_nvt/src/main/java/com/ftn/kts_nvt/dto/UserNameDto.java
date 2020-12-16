package com.ftn.kts_nvt.dto;

public class UserNameDto {
	private String firstName;
	private String lastName;
	
	
	public UserNameDto(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserNameDto() {};
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

}
