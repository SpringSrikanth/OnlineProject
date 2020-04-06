package com.jwt.models;

import java.util.List;

import com.jwt.jwtProject.modals.Role;
import com.jwt.jwtProject.modals.User;

public class AuthenticationResponce {
	private String token;
	private User userdetails;
	private List<Role> roles;
	public AuthenticationResponce() {}
	
	public AuthenticationResponce(String token) {
		this.token = token;
	}
	
	public AuthenticationResponce(String token,User userdetails, List<Role> roles) {
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
