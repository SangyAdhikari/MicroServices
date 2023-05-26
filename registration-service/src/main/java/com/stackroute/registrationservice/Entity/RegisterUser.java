package com.stackroute.registrationservice.Entity;




import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import com.stackroute.registrationservice.EnumClass.UserType;
@Document(collection="RegisterUser")
public class RegisterUser {
	@Id
	private String emailId; 
    private String userName;
    private String password;
    private long mobileNo;
    private String location;
    private UserType userType;
   
    
    
   
   
	public RegisterUser() {
		
		// TODO Auto-generated constructor stub
	}

	
	/*
	 * public RegisterUser(String emailId, String userName,UserType userType,
	 * boolean enabled) { super(); this.emailId = emailId; this.userType = userType;
	 * this.enabled = enabled; this.userName=userName;
	 * 
	 * }
	 */

	public RegisterUser(String emailId, String userName, String password, long mobileNo, String location,
			UserType userType) {
		super();
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
		this.mobileNo = mobileNo;
		this.location = location;
		this.userType = userType;
	}
	public String getEmailId() {
		return emailId;
	}
	public RegisterUser setEmailId(String emailId) {
		this.emailId = emailId;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public RegisterUser setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public RegisterUser setPassword(String password) {
		this.password = password;
		return this;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public RegisterUser setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
		return this;
	}
	public String getLocation() {
		return location;
	}
	public RegisterUser setLocation(String location) {
		this.location = location;
		return this;
	}
	public UserType getUserType() {
		return userType;
	}
	public RegisterUser setUserType(UserType userType) {
		this.userType = userType;
		return this;
	}
	
	
	@Override
	public String toString() {
		return "RegisterUser [emailId=" + emailId + ", userName=" + userName + ", password=" + password + ", mobileNo="
				+ mobileNo + ", location=" + location + ", userType=" + userType + "]";
	}
	
	


}
