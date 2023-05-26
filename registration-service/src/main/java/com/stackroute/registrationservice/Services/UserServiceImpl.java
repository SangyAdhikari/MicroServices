package com.stackroute.registrationservice.Services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.stackroute.registrationservice.Entity.RegisterUser;

import com.stackroute.registrationservice.ExceptionHandling.UserException;
import com.stackroute.registrationservice.RabbitMQ.RabbitMqClass;
import com.stackroute.registrationservice.Repository.OrganizerRepository;
import com.stackroute.registrationservice.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository UserRepo;
	@Autowired
	RabbitMqClass rabbit;
	@Autowired
	private OrganizerRepository Orepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		super();
		UserRepo = userRepo;
	
	}


	@Override
	public RegisterUser newUser(RegisterUser registerUser) throws UserException {
		if(Orepo.findById(registerUser.getEmailId()).isEmpty() && UserRepo.findById(registerUser.getEmailId()).isEmpty()){
			   rabbit.userData(registerUser);
			   return UserRepo.save(registerUser);
			}
		else
		{
			throw new UserException("email Already Exist");
		}
		
	 
	}
	@Override
	public RegisterUser updateUserPassword(String emailId, RegisterUser password) throws UserException {
		if(UserRepo.findById(emailId).isEmpty())
		{
			 throw new UserException("Email Not Found");
		}
		else
		{
			 Optional<RegisterUser> user = UserRepo.findById(emailId);
		RegisterUser registerdUser = user.get();
		rabbit.updateUserData(emailId, password.getPassword());
		registerdUser.setPassword(password.getPassword());
		return registerdUser;
		}
	
		
	}

	@Override
	public void updatedUser(RegisterUser updateduser) {
		UserRepo.save(updateduser);
		
	}

	@Override
	public RegisterUser updateUserInfo(String emailId, String userName, long mobileNo) throws UserException {
		if(UserRepo.findById(emailId).isEmpty())
		{
			throw new UserException("No User Found with emailId");
		}
		else {
			Optional<RegisterUser> user = UserRepo.findById(emailId);
		RegisterUser updatedRegisterUser = user.get();
		
		RegisterUser updated = updatedRegisterUser.setUserName(userName).setMobileNo(mobileNo);
		return updated;
		}	
	}

	@Override
	public void saveUpdatedUserInfo(RegisterUser registerUser) {
		UserRepo.save(registerUser);
		
	}

	@Override
	public List<RegisterUser> FetchAllUsers() throws UserException {
		List<RegisterUser> AllUsers =UserRepo.findAll();
		if(AllUsers.isEmpty())
		{
		throw new UserException("No Users Record Found");
		}
		else {
			return AllUsers;
		}
		
	}

	@Override
	public RegisterUser getOneUser(String emailId) throws UserException {
		if(UserRepo.findById(emailId).isEmpty())
		{
			throw new UserException("No Record Found With EmailId");
		}
		else
		{
			Optional<RegisterUser> PaUser = UserRepo.findById(emailId);
		RegisterUser User = PaUser.get();
			return User;
	}
	}
}
	

	


