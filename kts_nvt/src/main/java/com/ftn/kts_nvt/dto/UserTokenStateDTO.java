package com.ftn.kts_nvt.dto;

import java.time.Instant;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenStateDTO {

	private String authenticationToken;
	//private String refreshToken;
	private int expiresAt;
	private String email;
	    
    //private String accessToken;
    //private Long expiresIn;

    public UserTokenStateDTO() {
    }

    public UserTokenStateDTO(String authenticationToken, int expiresAt) {
		super();
		this.authenticationToken = authenticationToken;
		this.expiresAt = expiresAt;
	}

    public UserTokenStateDTO(String authenticationToken, int expiresAt, String email) {
		super();
		this.authenticationToken = authenticationToken;
		this.expiresAt = expiresAt;
		this.email = email;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public int getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(int expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getEmail() { 
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
