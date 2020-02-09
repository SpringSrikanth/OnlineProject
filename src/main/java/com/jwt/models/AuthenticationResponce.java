package com.jwt.models;

public class AuthenticationResponce {

	private String token;
	public AuthenticationResponce() {}
	
	public AuthenticationResponce(String token) {
		this.token = token;
	}

	public String getJwt() {
		return token;
	}

	public void setJwt(String token) {
		this.token = token;
	}

}
