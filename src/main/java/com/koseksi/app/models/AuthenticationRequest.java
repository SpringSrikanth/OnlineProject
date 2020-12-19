package com.koseksi.app.models;

import com.koseksi.app.modals.User;

public class AuthenticationRequest {

	private String username;
	private String password;
	public AuthenticationRequest(User user) {
		this.username=user.getUsername();
		this.password=user.getPassword();
	}
	public AuthenticationRequest(String userName, String password) {
		this.username = userName;
		this.password = password;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
