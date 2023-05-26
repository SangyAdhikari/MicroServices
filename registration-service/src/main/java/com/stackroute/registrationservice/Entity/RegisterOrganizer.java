package com.stackroute.registrationservice.Entity;


import java.util.List;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.registrationservice.EnumClass.UserType;

@Document(collection="RegisterOrganizer")
public class RegisterOrganizer {
	
	@Id
	private String emailId;
    private String organizerName;
    private String password;
    private List<TheatreDetails> list;
    //private List<TheatreDetails> lists;
	private long mobileNo;
    private UserType userType;
	public String getEmailId() {
		return emailId;
		
	}
	public RegisterOrganizer() {
		System.out.println("hi");
		
	}
	
	public RegisterOrganizer(String emailId, String organizerName, String password, List<TheatreDetails> list,
			long mobileNo, UserType userType) {
		super();
		this.emailId = emailId;
		this.organizerName = organizerName;
		this.password = password;
		this.list = list;
		this.mobileNo = mobileNo;
		this.userType = userType;
	}
	public RegisterOrganizer setEmailId(String emailId) {
		this.emailId = emailId;
		return this;
	}
	public String getOrganizerName() {
		return organizerName;
	}
	public RegisterOrganizer setOrganizerName(String organiserName) {
		this.organizerName = organiserName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public RegisterOrganizer setPassword(String password) {
		this.password = password;
		return this;
	}

	public long getMobileNo() {
		return mobileNo;
	}
	public RegisterOrganizer setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
		return this;
	}
	public UserType getUserType() {
		return userType;
	}
	public RegisterOrganizer setUserType(UserType userType) {
		this.userType = userType;
		return this;
	}
	public List<TheatreDetails> getList() {
		return list;
	}
	public void setList(List<TheatreDetails> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "RegisterOrganizer [emailId=" + emailId + ", organizerName=" + organizerName + ", password=" + password
				+ ", list=" + list + ", mobileNo=" + mobileNo + ", userType=" + userType + "]";
	}
	
	
	
	
	
	
    


}
