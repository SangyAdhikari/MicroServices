package com.stackroute.registrationservice.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.stackroute.registrationservice.Entity.RegisterUser;
import com.stackroute.registrationservice.ExceptionHandling.UserException;
import com.stackroute.registrationservice.Services.UserService;




@RestController
@RequestMapping("/evento/register")
//@CrossOrigin(origins = "*")
public class UserController {
	
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/register-user")
	
	public ResponseEntity<RegisterUser> newUser(@RequestBody()  RegisterUser registerUser) throws UserException
	{
			RegisterUser newUser = service.newUser(registerUser);
			return  new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	@PutMapping("/update/update-user-password/{emailId}")
	
	public ResponseEntity<RegisterUser> updateUserPassword(@PathVariable("emailId")String emailId,@RequestBody RegisterUser password) throws UserException

	{
		
		RegisterUser updateUserPassword = service.updateUserPassword(emailId, password);
		service.updatedUser(updateUserPassword);
		return new ResponseEntity<>(updateUserPassword,HttpStatus.CREATED);
	}

	@PutMapping("/update/update-user/{emailId}/{userName}/{mobileNo}")
	
	public ResponseEntity<RegisterUser> updateContact(@PathVariable("emailId")String emailId,@PathVariable("userName")String userName,@PathVariable("mobileNo")long mobileNo) throws UserException

	{
		RegisterUser updateUserInfo = service.updateUserInfo(emailId,userName,mobileNo);
		service.saveUpdatedUserInfo(updateUserInfo);
		return new ResponseEntity<>(updateUserInfo,HttpStatus.CREATED);
	}

	

	@GetMapping("/get/users")

	public ResponseEntity<List<RegisterUser>> AllUsers() throws UserException

	{
		List<RegisterUser> AllUsers = service.FetchAllUsers();
		return new ResponseEntity<List<RegisterUser>>(AllUsers,HttpStatus.CREATED);
	}

	

	@GetMapping("/get/user/{emailId}")

	public ResponseEntity<RegisterUser> getOneUser(@PathVariable("emailId")String emailId) throws UserException

	{
		RegisterUser oneUser = service.getOneUser(emailId);
		return new ResponseEntity<>(oneUser,HttpStatus.CREATED);
	}
	/*
	 * @RequestMapping("/login") public String loginView() { return "login"; }
	 */
	
	
	
		  
	  
	 
	 
	
	
}
