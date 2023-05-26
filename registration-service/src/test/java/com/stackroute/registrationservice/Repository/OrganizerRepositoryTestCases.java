//package com.stackroute.registrationservice.Repository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import com.stackroute.registrationservice.Entity.RegisterOrganizer;
//import com.stackroute.registrationservice.Entity.TheatreDetails;
//import com.stackroute.registrationservice.EnumClass.UserType;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class OrganizerRepositoryTestCases {
//	RegisterOrganizer trialObj;
//	TheatreDetails details;
//
//
//	@Autowired
//	OrganizerRepository repository;
//
//	@BeforeEach
//	public void setUp() {
//		List<TheatreDetails> list=new ArrayList<>();
//		details=new TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South","Karnataka","Bangalore");
//		list.add(details);
//		 trialObj=new RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,UserType.User);
//	}
//	   @AfterEach
//	    public void tearDown() {
//	        trialObj= null;
//	        details=null;
//	        repository.deleteAll();
//	    }
//
//	@Test
//	public void testSaveUser() {
//		repository.insert(trialObj);
//		Optional<RegisterOrganizer> id = repository.findById(trialObj.getEmailId());
//		RegisterOrganizer registerUser = id.get();
//		List<RegisterOrganizer> list=new ArrayList<>();
//		list.add(registerUser);
//		 assertNotNull(list);
//		assertEquals(1, list.size());
//	}
//	@Test
//	public void testFindById() {
//		repository.insert(trialObj);
//		String emailId="nitesh@gmail.com";
//		Optional<RegisterOrganizer> id = repository.findById(emailId);
//		RegisterOrganizer registerUser = id.get();
//		assertEquals(trialObj.getEmailId(), registerUser.getEmailId());
//	}
//	@Test
//	public void testFindAll() {
//		repository.insert(trialObj);
//		List<RegisterOrganizer> findAll = repository.findAll();
//		assertEquals(1, findAll.size());
//	}
//
//
//
//}
