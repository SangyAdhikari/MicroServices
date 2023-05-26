package com.stackroute.eventdetailsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.stackroute.eventdetailsservice.controller.MyController;
import com.stackroute.eventdetailsservice.model.EventDetails;
import com.stackroute.eventdetailsservice.model.UserRating;
import com.stackroute.eventdetailsservice.service.EventServiceImpl;

class ControllerTest {

	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
	/*
	@Test
	public void getRecievedInviationsControllerTest() throws Exception {
	    Mockito.when(teamupService.getRecievedInvitations("swastik@gmail.com")).thenReturn(teamupList);
	    responseEntity = new ResponseEntity<>(teamupList, HttpStatus.OK);
	    assertEquals(responseEntity, teamupController.getRecievedInvitations("swastik@gmail.com"));
	}
	*/
	
	@Mock
	private EventServiceImpl serviceOb;
	@InjectMocks
	private MyController controllerOb;
	
	List<EventDetails> obList = new ArrayList<>();
	EventDetails obj = new EventDetails();
	
	@Before
	public void setEventDetails() {
		obj.set_id(345);
		obj.setEventName("Bahubali");
		obj.setEventType("Movie");
		
		List<String> l1 = new ArrayList<>();
		l1.add("Hindi");
		l1.add("English");
		obj.setReleasedLanguages(l1);
		
		obj.setEventDuration("5");
		
		List<String> l2 = new ArrayList<>();
		l2.add("Bahubali");
		l2.add("Katappa");
		obj.setCastAndCrew(l2);
		
		obj.setEventDescription("Action movie");
		obj.setGenreOfEvent("");
		obj.setEventRating(5.0);
		
		List<UserRating> l3 = new ArrayList<>();
		UserRating userob1 = new UserRating();
		userob1.setEmailId("kunal@gmail.com");
		userob1.setUserRating(5);
		UserRating userob2 = new UserRating();
		userob2.setEmailId("Amit@gmail.com");
		userob2.setUserRating(5);
		l3.add(userob1);
		l3.add(userob2);
		obj.setUserRating(l3);
		obj.setImage(null);
		//obj.setReleateDate(LocalDate.now());
		
		obList.add(obj);
	}
	
	/*@Test
	public void getshowEvents() throws Exception{
		Mockito.when(serviceOb.getEvents()).thenReturn(obList);
		assertEquals(obList,controllerOb.showEvents());
	}*/
}
