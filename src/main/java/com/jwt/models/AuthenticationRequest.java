package com.jwt.models;

import com.jwt.jwtProject.modals.User;

public class AuthenticationRequest {

	private String userName;
	private String password;
	public AuthenticationRequest(User user) {
		this.userName=user.getUsername();
		this.password=user.getPassword();
	}
	public AuthenticationRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
