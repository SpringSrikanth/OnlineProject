package com.jwt.jwtProject.modals;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	@NonNull
	private String username;
	@Value("0")
	private int active;
	private String roles;
	@NonNull
	private String firstname;
	@NonNull
	private String lastname;
	@NonNull
	private String email;
	@NonNull
	private String password; 
	private String gender; 
	@Nullable
	private String secondaryMail; 
	
	@Value("USER")
	private String Default_Role;
	
	@Nullable
	private String mobile;
	@Nullable
	private Date created_date=new Date();
	@Nullable
	private Date updated_date=new Date();
	@Nullable
	private Date dateofbirth;

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int isActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
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
	public String getSecondaryMail() {
		return secondaryMail;
	}
	public void setSecondaryMail(String secondaryMail) {
		this.secondaryMail = secondaryMail;
	}
	public String getDefault_Role() {
		return Default_Role;
	}
	public void setDefault_Role(String default_Role) {
		Default_Role = default_Role;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	
}
