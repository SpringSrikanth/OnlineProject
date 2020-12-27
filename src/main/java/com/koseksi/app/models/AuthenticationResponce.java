package com.koseksi.app.models;

import java.util.List;

import com.koseksi.app.modals.Role;
import com.koseksi.app.modals.User;

public class AuthenticationResponce {
	private String accessToken;
	private String refreshToken;
	private User userdetails;
	private List<Role> roles;
	public AuthenticationResponce() {}
	
	public AuthenticationResponce(String accessToken,String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public AuthenticationResponce(String accessToken,String refreshToken,User userdetails, List<Role> roles) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userdetails=userdetails;
		this.roles=roles;
	}

	public User getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(User userdetails) {
		this.userdetails = userdetails;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	

}
