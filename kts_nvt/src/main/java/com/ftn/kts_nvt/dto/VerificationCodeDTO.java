package com.ftn.kts_nvt.dto;

public class VerificationCodeDTO {

	private String code;
	private String userEmail;

	public VerificationCodeDTO() {
		super();
	}

	public VerificationCodeDTO(String code, String userEmail) {
		super();
		this.code = code;
		this.userEmail = userEmail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
