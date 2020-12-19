package com.koseksi.app.modals;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Blogs")
public class Blog implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE_NAME = "blogs_Sequence";
	
	@Id
	private long blogId;
	
	private String blogTitle;
	private String blogDescription;
	private String blogCreatedBy;
	private String userId;
	private String blogContent;
	private String blogImageUrls;
	private String blogImportantLinks;
	private String mainImage;
	private Date createdDate;
	private Date updatedDate;
	private boolean isPublished=false; 
	private boolean emailNotified=false;
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
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
	public String getBlogImageUrls() {
		return blogImageUrls;
	}
	public void setBlogImageUrls(String blogImageUrls) {
		this.blogImageUrls = blogImageUrls;
	}
	public String getBlogImportantLinks() {
		return blogImportantLinks;
	}
	public void setBlogImportantLinks(String blogImportantLinks) {
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
