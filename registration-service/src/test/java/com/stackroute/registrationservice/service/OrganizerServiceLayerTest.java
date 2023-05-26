//package com.stackroute.registrationservice.service;
//
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.stackroute.registrationservice.Entity.RegisterOrganizer;
//import com.stackroute.registrationservice.Entity.TheatreDetails;
//import com.stackroute.registrationservice.EnumClass.UserType;
//
//import com.stackroute.registrationservice.ExceptionHandling.OrganizerException;
//
//import com.stackroute.registrationservice.Repository.OrganizerRepository;
//import com.stackroute.registrationservice.Repository.TheatreDetailsRepository;
//import com.stackroute.registrationservice.Services.OrganizerServiceImpl;
//
//
//@SpringBootTest(classes = {OrganizerServiceLayerTest.class})
//public class OrganizerServiceLayerTest {
//	@Mock
//	private OrganizerRepository repository;
//
//	@Mock
//	private TheatreDetailsRepository tRepository;
//
//	@InjectMocks
//	private OrganizerServiceImpl serviceImpl;
//
//
//	/*
//	 * @Test public void testNewOrganizer() { List<TheatreDetails> list=new
//	 * ArrayList<>(); TheatreDetails details=new
//	 * TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South",
//	 * "Karnataka","Bangalore"); list.add(details); RegisterOrganizer trialObj=new
//	 * RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,
//	 * UserType.User); when(repository.save(trialObj)).thenReturn(trialObj);
//	 * assertEquals(trialObj, serviceImpl.newOrganiser(trialObj)); }
//	 *
//	 * @Test public void testUpdateOrganizerPassword() { List<TheatreDetails>
//	 * list=new ArrayList<>(); TheatreDetails details=new
//	 * TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South",
//	 * "Karnataka","Bangalore"); list.add(details); RegisterOrganizer trialObj=new
//	 * RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,
//	 * UserType.User); String emailId="nitesh@gmail.com"; String
//	 * password="Nitesh@2310";
//	 * when(repository.findById(emailId)).thenReturn(Optional.ofNullable(trialObj));
//	 * assertEquals(trialObj, serviceImpl.updateOrganizerPassword(emailId,
//	 * password));//Positive String fakePassword="Priti@1310";
//	 * assertNotEquals(trialObj.getPassword(),
//	 * serviceImpl.updateOrganizerPassword(emailId, fakePassword));//Negative }
//	 */
//
//	@Test
//	public void testUpdateOrganizerInfo() throws OrganizerException {
//		List<TheatreDetails> list=new ArrayList<>();
//		TheatreDetails details=new TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South","Karnataka","Bangalore");
//		list.add(details);
//		RegisterOrganizer trialObj=new RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,UserType.User);
//		when(repository.findById(trialObj.getEmailId())).thenReturn(Optional.ofNullable(trialObj));
//		String emailId="nitesh@gmail.com";
//		String password="Nitesh@2310";
//		long mobileNo=837283606;
//		assertEquals(trialObj, serviceImpl.updateOrganizerInfo(emailId,password,mobileNo));
//	}
//
//	@Test
//	public void testGetAllOrganizer() throws OrganizerException {
//		List<TheatreDetails> list=new ArrayList<>();
//		TheatreDetails details=new TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South","Karnataka","Bangalore");
//		list.add(details);
//		RegisterOrganizer trialObj=new RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,UserType.User);
//		List<RegisterOrganizer> oList=new ArrayList<>();
//		oList.add(trialObj);
//		when(repository.findAll()).thenReturn(oList);
//		assertEquals(1, serviceImpl.getAllOrganizer().size());//positive
//		assertNotEquals(2, serviceImpl.getAllOrganizer().size());//Negative
//	}
//	@Test()
//	public void testGetOneOrganizer() throws OrganizerException
//	{
//		List<TheatreDetails> list=new ArrayList<>();
//		TheatreDetails details=new TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South","Karnataka","Bangalore");
//		list.add(details);
//		RegisterOrganizer trialObj=new RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,UserType.User);
//		String emailId="nitesh@gmail.com";
//		when(repository.findById(emailId)).thenReturn(Optional.ofNullable(trialObj));
//		assertEquals(trialObj, serviceImpl.getOneOrganizer(emailId));
//
//	}
//	@Test
//	public void testGetTheatreDetails() throws OrganizerException {
//		TheatreDetails details=new TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South","Karnataka","Bangalore");
//		Integer theatreId=1;
//		when(tRepository.findById(theatreId)).thenReturn(Optional.ofNullable(details));
//		assertEquals(details, serviceImpl.getTheatreDetails(1));
//
//	}
//	/*@Test
//	public void testUpdateTheatreDetails() {
//		List<TheatreDetails> list=new ArrayList<>();
//		TheatreDetails details=new TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South","Karnataka","Bangalore");
//		list.add(details);
//		RegisterOrganizer trialObj=new RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,UserType.User);
//		String emailId="nitesh@gmail.com";
//		when(repository.findById(emailId)).thenReturn(Optional.ofNullable(trialObj));
//		assertEquals(trialObj, serviceImpl.updateTheatreDetails(emailId, details));
//	}*/
//
//
//
//
//
//}
