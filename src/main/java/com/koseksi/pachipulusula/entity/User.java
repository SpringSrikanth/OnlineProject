package com.koseksi.pachipulusula.entity;

import java.util.Date;

public class User {

	private int userId;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String gender;
	private Date dateOfBirth;
	private String secondaryMail;
	private String Mobile;
	private Date created_date;
	private Date updated_date;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getSecondaryMail() {
		return secondaryMail;
	}
	public void setSecondaryMail(String secondaryMail) {
		this.secondaryMail = secondaryMail;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", password=" + password + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", secondaryMail=" + secondaryMail + ", Mobile=" + Mobile + ", created_date="
				+ created_date + ", updated_date=" + updated_date + "]";
	}
	
	
	


}
