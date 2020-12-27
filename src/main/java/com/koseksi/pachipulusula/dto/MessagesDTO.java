package com.koseksi.pachipulusula.dto;

import java.util.Date;

public class MessagesDTO {

 private int message_Id;
	 
	 private Date created_date;
	 
	 private Date updated_date;
	 
	 private String message;
	 
	 private String from_user_name;
	 
	 private String to_user_name;
	 
	 private String status;
	 
	 private String file_url;
	 
	 private String file_type;
	 
	 private String type_of_message;
	 
	 private int from_user_id;
	 
	 private int to_user_id;

	public int getMessage_Id() {
		return message_Id;
	}

	public void setMessage_Id(int message_Id) {
		this.message_Id = message_Id;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFrom_user_name() {
		return from_user_name;
	}

	public void setFrom_user_name(String from_user_name) {
		this.from_user_name = from_user_name;
	}

	public String getTo_user_name() {
		return to_user_name;
	}

	public void setTo_user_name(String to_user_name) {
		this.to_user_name = to_user_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getType_of_message() {
		return type_of_message;
	}

	public void setType_of_message(String type_of_message) {
		this.type_of_message = type_of_message;
	}

	public int getFrom_user_id() {
		return from_user_id;
	}

	public void setFrom_user_id(int from_user_id) {
		this.from_user_id = from_user_id;
	}

	public int getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(int to_user_id) {
		this.to_user_id = to_user_id;
	}

	@Override
	public String toString() {
		return "MessagesDTO [message_Id=" + message_Id + ", created_date=" + created_date + ", updated_date="
				+ updated_date + ", message=" + message + ", from_user_name=" + from_user_name + ", to_user_name="
				+ to_user_name + ", status=" + status + ", file_url=" + file_url + ", file_type=" + file_type
				+ ", type_of_message=" + type_of_message + ", from_user_id=" + from_user_id + ", to_user_id="
				+ to_user_id + "]";
	}
	 
	 
	public MessagesDTO() {}
	 
}
