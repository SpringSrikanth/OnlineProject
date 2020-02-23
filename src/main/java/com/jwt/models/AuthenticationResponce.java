package com.jwt.models;

import com.jwt.jwtProject.modals.User;

public class AuthenticationResponce {
	private String token;
	private User userdetails;
	public AuthenticationResponce() {}
	
	public AuthenticationResponce(String token) {
		this.token = token;
	}
	
	public AuthenticationResponce(String token,User userdetails) {
		this.token = token;
		this.userdetails=userdetails;
	}

	public String getJwt() {
		return token;
	}

	public void setJwt(String token) {
		this.token = token;
	}

	public User getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(User userdetails) {
		this.userdetails = userdetails;
	}

}
