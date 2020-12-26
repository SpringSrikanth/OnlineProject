package com.koseksi.app.modals;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MailMessage {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	@Column(name="toAddress",nullable = false)
	private String toAddress;
	private String messageBody="";
	private String messageSubject="";
	private String sentByUsername;
	private int userId;
	@Column(name="messageStatus",nullable = true)
	private String messageStatus;
	@Column(name="failureMessages",nullable = true)
	private String failureMessages;
	private Date createdDate= new Date();
	private int isFileAttached=0;
	
	public MailMessage() {}
	
	public int getIsFileAttached() {
		return isFileAttached;
	}

	public void setIsFileAttached(int isFileAttached) {
		this.isFileAttached = isFileAttached;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getSentByUsername() {
		return sentByUsername;
	}
	public void setSentByUsername(String sentByUsername) {
		this.sentByUsername = sentByUsername;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public String getFailureMessages() {
		return failureMessages;
	}
	public void setFailureMessages(String failureMessages) {
		this.failureMessages = failureMessages;
	}

	@Override
	public String toString() {
		return "MailMessage [id=" + id + ", toAddress=" + toAddress + ", messageBody=" + messageBody
				+ ", messageSubject=" + messageSubject + ", sentByUsername=" + sentByUsername + ", userId=" + userId
				+ ", messageStatus=" + messageStatus + ", failureMessages=" + failureMessages + ", createdDate="
				+ createdDate + ", isFileAttached=" + isFileAttached + "]";
	}
}

