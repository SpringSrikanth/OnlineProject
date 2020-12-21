package com.koseksi.app.modals;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fileDetails", uniqueConstraints={@UniqueConstraint(columnNames = {"fileName"})})
public class FileDetails {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String fileId;

	@Column(name = "filename" ,unique=true,nullable = false)
	private String fileName;
	
	private String fileType;
	
	private String classPK=this.getClass().getCanonicalName();
	
	private int userId;

	private Date createdDate=new Date();
	
	private Date updatedDate=new Date();
	
	private int status=0;
	
	@Lob
	private byte[] data;
	
	public FileDetails() {}

	public FileDetails(String fileName, String fileType, byte[] data ,int userId) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.userId =userId;
	}
	

	public FileDetails(String fileId, String fileName, String fileType, String classPK, int userId, Date createdDate,
			Date updatedDate, int status, byte[] data) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.classPK = classPK;
		this.userId = userId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getClassPK() {
		return classPK;
	}

	public void setClassPK(String classPK) {
		this.classPK = classPK;
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
	
}
