package com.stackroute.eventdetailsservice.model;

public class UserRating {
	
	private String emailId;
	private int userRating;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getUserRating() {
		return userRating;
	}
	public void setUserRating(int userRating) {
		this.userRating = userRating;
	}
	public UserRating() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRating(String emailId, int userRating) {
		super();
		this.emailId = emailId;
		this.userRating = userRating;
	}
	
	
	
}
