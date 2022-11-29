package com.cognizant.xxPatientAuthorization.model;

public class AuthResponse {
	
	private String username;
	
	private boolean isValid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public AuthResponse(String username, boolean isValid) {
		super();
		this.username = username;
		this.isValid = isValid;
	}

	public AuthResponse() {
		super();
	}

}
