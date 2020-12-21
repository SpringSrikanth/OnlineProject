package com.koseksi.app.models;

public class FileUploadResponce {

	private String fileName;
	private String downloadUri;
	private String fileId;
	private String fileType;
	private byte[] fileData;
	private int userId;
	public FileUploadResponce(String fileName, String downloadUri, String fileId, String fileType, byte[] fileData,int userId) {
		this.fileName = fileName;
		this.downloadUri = downloadUri;
		this.fileId = fileId;
		this.fileType = fileType;
		this.fileData = fileData;
		this.userId = userId;
	}
	
	
	public FileUploadResponce() {
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDownloadUri() {
		return downloadUri;
	}
	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
