package com.jwt.jwtProject.modals;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.id.SequenceGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Blogs")
public class BlogDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int blogId;
	private String blogTitle;
	private String blogDescription;
	private String blogCreatedBy;
	private String userId;
	private String blogContent;
	private List<String> blogImageUrls;
	private List<String> blogImportantLinks;
	private String mainImage;
	private Date createdDate;
	private Date updatedDate;
	private boolean isPublished=false; 
	private boolean emailNotified=false;
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	public String getBlogCreatedBy() {
		return blogCreatedBy;
	}
	public void setBlogCreatedBy(String blogCreatedBy) {
		this.blogCreatedBy = blogCreatedBy;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public List<String> getBlogImageUrls() {
		return blogImageUrls;
	}
	public void setBlogImageUrls(List<String> blogImageUrls) {
		this.blogImageUrls = blogImageUrls;
	}
	public List<String> getBlogImportantLinks() {
		return blogImportantLinks;
	}
	public void setBlogImportantLinks(List<String> blogImportantLinks) {
		this.blogImportantLinks = blogImportantLinks;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isEmailNotified() {
		return emailNotified;
	}
	public void setEmailNotified(boolean emailNotified) {
		this.emailNotified = emailNotified;
	}
	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	
	
	
}
