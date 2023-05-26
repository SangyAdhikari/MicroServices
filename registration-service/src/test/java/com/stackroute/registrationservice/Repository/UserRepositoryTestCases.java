//package com.stackroute.registrationservice.Repository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.stackroute.registrationservice.Entity.RegisterUser;
//import com.stackroute.registrationservice.EnumClass.UserType;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class UserRepositoryTestCases {
//
//	RegisterUser trialObj;
//
//	@Autowired
//	UserRepository repository;
//
//	@BeforeEach
//	public void setUp() {
//		trialObj=new RegisterUser("nitesh@gmail.com","Nitesh","Nitish@2310",837283606,"Bangalore",UserType.User);
//	}
//	   @AfterEach
//	    public void tearDown() {
//	        trialObj= null;
//
//	        repository.deleteAll();
//	    }
//
//	@Test
//	public void testSaveUser() {
//		repository.insert(trialObj);
//		Optional<RegisterUser> id = repository.findById(trialObj.getEmailId());
//		RegisterUser registerUser = id.get();
//		List<RegisterUser> list=new ArrayList<>();
//		list.add(registerUser);
//		 assertNotNull(list);
//		assertEquals(1, list.size());
//	}
//	@Test
//	public void testFindById() {
//		repository.insert(trialObj);
//		String emailId="nitesh@gmail.com";
//		Optional<RegisterUser> id = repository.findById(emailId);
//		RegisterUser registerUser = id.get();
//		assertEquals(trialObj.getEmailId(), registerUser.getEmailId());
//	}
//	@Test
//	public void testFindAll() {
//		repository.insert(trialObj);
//		List<RegisterUser> findAll = repository.findAll();
//		assertEquals(1, findAll.size());
//	}
//}
