package com.stackroute.registrationservice.RabbitMQ;

import com.stackroute.registrationservice.EnumClass.UserType;

public class RabbitMqMessage {
	private String emailId;
	private String password;
	private UserType userType;
	public String getEmailId() {
		return emailId;
	}
	public RabbitMqMessage setEmailId(String emailId) {
		this.emailId = emailId;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public RabbitMqMessage setPassword(String password) {
		this.password = password;
		return this;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	

}
