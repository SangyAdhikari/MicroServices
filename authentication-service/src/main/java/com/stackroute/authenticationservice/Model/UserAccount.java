package com.stackroute.authenticationservice.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@Table(name = "Login_Details")
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

      @Id
	    private String userEmail;
	    private String userPassword;
		private String userType;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
				"userEmail='" + userEmail + '\'' +
				", userPassword='" + userPassword + '\'' +
				", userType='" + userType + '\'' +
				'}';
	}
}
