package com.stackroute.registrationservice.Services;

import java.util.List;

import com.stackroute.registrationservice.Entity.RegisterOrganizer;
import com.stackroute.registrationservice.Entity.RegisterUser;
import com.stackroute.registrationservice.ExceptionHandling.UserException;

public interface UserService {
	public RegisterUser newUser(RegisterUser registerUser) throws UserException;
	public RegisterUser updateUserPassword(String emailId,RegisterUser password) throws UserException;
	public void updatedUser (RegisterUser updateduser);
	public RegisterUser updateUserInfo(String emailId, String userName, long mobileNo) throws UserException;
	public void saveUpdatedUserInfo(RegisterUser registerUser);
	public List<RegisterUser> FetchAllUsers() throws UserException;
	public RegisterUser getOneUser(String emailId) throws UserException;
	

}
