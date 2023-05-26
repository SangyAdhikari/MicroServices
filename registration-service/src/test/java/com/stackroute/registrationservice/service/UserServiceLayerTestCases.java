//package com.stackroute.registrationservice.service;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import com.stackroute.registrationservice.Entity.RegisterUser;
//import com.stackroute.registrationservice.EnumClass.UserType;
//import com.stackroute.registrationservice.ExceptionHandling.UserException;
//import com.stackroute.registrationservice.Repository.UserRepository;
//import com.stackroute.registrationservice.Services.UserServiceImpl;
//@SpringBootTest(classes = {UserServiceLayerTestCases.class})
//class UserServiceLayerTestCases {
//
//	RegisterUser  trialObj=new RegisterUser("nitesh@gmail.com","Nitesh","Nitish@2310",837283606,"Bangalore",UserType.User);
//
//	@Mock
//	private UserRepository repository;
//
//	@InjectMocks
//	private UserServiceImpl serviceImpl;
//
//
//	/*
//	 * @Test public void testNewUser() {
//	 * when(repository.save(trialObj)).thenReturn(trialObj);
//	 * assertEquals(trialObj,serviceImpl.newUser(trialObj)); }
//	 *
//	 * @Test public void testUpdateUserPassword() { String
//	 * emailId="nitesh@gmail.com"; String password="Nitish@2310";
//	 * when(repository.findById(trialObj.getEmailId())).thenReturn(Optional.
//	 * ofNullable(trialObj)); assertEquals(trialObj,
//	 * serviceImpl.updateUserPassword(emailId, password));//positive String
//	 * fakePassword="Priti@1310"; assertNotEquals(trialObj.getPassword(),
//	 * serviceImpl.updateUserPassword(emailId,fakePassword));//Negative }
//	 */
//
//
//	    @Test
//	    public void testUpdateUserInfo() throws UserException {
//	    	String emailId="nitesh@gmail.com";
//	    	String password="Nitesh@2310";
//	    	long mobileNo=837283606;
//	    	when(repository.findById(emailId)).thenReturn(Optional.ofNullable(trialObj));
//	    	assertEquals(trialObj, serviceImpl.updateUserInfo(emailId, password, mobileNo));//Positive
//	    	String fakePassword="Priti@1310";
//	    	long fakemobileNo=977547850;
//	    	assertNotEquals(trialObj.getPassword(), serviceImpl.updateUserInfo(emailId, fakePassword, fakemobileNo));//Negative
//	    }
//	    @Test
//	    public void testFetchAllUsers() throws UserException {
//	    	RegisterUser trialObj1=new RegisterUser("priti@gmail.com","Nitesh","Nitish@2310",837283606,"Bangalore",UserType.User);
//	    	List<RegisterUser> allUsers=new ArrayList<>();
//	    	allUsers.add(trialObj1);
//	    	allUsers.add(trialObj);
//	    	when(repository.findAll()).thenReturn(allUsers);
//	    	assertEquals(2, serviceImpl.FetchAllUsers().size());//Positive
//	    	assertNotEquals(1, serviceImpl.FetchAllUsers().size());//Negative
//	    }
//	    @Test
//	    public void testGetOneUser() throws UserException
//	    {
//	    	when(repository.findById(trialObj.getEmailId())).thenReturn(Optional.ofNullable(trialObj));
//	    	assertEquals(trialObj, serviceImpl.getOneUser(trialObj.getEmailId()));//positive
//	    	String emailId="priti@gmail.com";
//	    	//assertNotEquals(trialObj.getEmailId(), serviceImpl.getOneUser(emailId));
//
//	    }
//
//}
