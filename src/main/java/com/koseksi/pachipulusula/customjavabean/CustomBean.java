package com.koseksi.pachipulusula.customjavabean;

public class CustomBean {

	private int statusCode=200;
    private String saatus="success";
    private String Message="Health is Ok";
    public CustomBean(){}
	public CustomBean(int statusCode, String saatus, String message) {
		this.statusCode = statusCode;
		this.saatus = saatus;
		Message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getSaatus() {
		return saatus;
	}
	public void setSaatus(String saatus) {
		this.saatus = saatus;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}

}
