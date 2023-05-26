//package com.stackroute.registrationservice.ControllerTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.registrationservice.Controller.OrganizerController;
//import com.stackroute.registrationservice.Entity.RegisterOrganizer;
//import com.stackroute.registrationservice.Entity.TheatreDetails;
//import com.stackroute.registrationservice.EnumClass.UserType;
//import com.stackroute.registrationservice.Services.OrganizerServiceImpl;
//
//@WebMvcTest(OrganizerControllerLayerTest.class)
//public class OrganizerControllerLayerTest {
//
//	RegisterOrganizer trialObj;
//	TheatreDetails details;
//
//	@Autowired
//	MockMvc mockMvc;
//
//	@Mock
//	OrganizerServiceImpl serviceImpl;
//
//	@InjectMocks
//	OrganizerController controller;
//
//	@BeforeEach
//	public void setUp() {
//		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
//		List<TheatreDetails> list=new ArrayList<>();
//		details=new TheatreDetails(1,"multiplex",100,"5th cross road","737121","MGR","South","Karnataka","Bangalore");
//		list.add(details);
//		 trialObj=new RegisterOrganizer("nitesh@gmail.com","Nitesh","Nitesh@2310",list,837283606,UserType.User);
//	}
//	@Test
//	public void testRegisterOrganizer() throws Exception {
//		when(serviceImpl.newOrganiser(any())).thenReturn(trialObj);
//		mockMvc.perform(post("/evento/register/register-organizer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(trialObj)))
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//	}
//	@Test
//	public void testUpdateOrganizerPassword() throws  Exception {
//		String emailId="nitesh@gmail.com";
//		String password="Nitesh@2310";
//		when(serviceImpl.updateOrganizerPassword(emailId, trialObj)).thenReturn(trialObj);
//		mockMvc.perform(put("/evento/register/update/update-organizer-password/nitesh@gmail.com/Nitesh@2310")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(trialObj))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//	}
//	@Test
//	public void testOrganizerInfo() throws Exception
//	{
//		String emailId="nitesh@gmail.com";
//		String password="Nitesh@2310";
//		long mobileNo=837283606;
//		when(serviceImpl.updateOrganizerInfo(emailId, password, mobileNo)).thenReturn(trialObj);
//		mockMvc.perform(put("/evento/register/update/update-organizer/nitesh@gmail.com/Nitesh@2310/837283606")
//				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(trialObj)))
//		         .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//	}
//	@Test
//	public void testGetAllOrganizer() throws  Exception {
//		List<RegisterOrganizer> list=new ArrayList<>();
//		list.add(trialObj);
//		when(serviceImpl.getAllOrganizer()).thenReturn(list);
//		assertEquals(1, serviceImpl.getAllOrganizer().size());
//		mockMvc.perform(get("/evento/register/get/organizers").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(list))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//	}
//	@Test
//	public void testGetOneOrganizer() throws  Exception
//	{
//		String emailId="nitesh@gmail.com";
//		when(serviceImpl.getOneOrganizer(emailId)).thenReturn(trialObj);
//		mockMvc.perform(get("/evento/register/get/organizer/nitesh2gmail.com").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(trialObj))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//	}
//	@Test
//	public void testGetTheatreDetailsById() throws Exception{
//		int id=1;
//		when(serviceImpl.getTheatreDetails(id)).thenReturn(details);
//		mockMvc.perform(get("/evento/register/get/theatreDetails/1").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(trialObj))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//	}
//	private static String jsonToString(final Object o) throws JsonProcessingException {
//        String result;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(o);
//            result = jsonContent;
//            return result;
//
//        } catch (JsonProcessingException e) {
//            result = "JsonProcessingException";
//        }
//        return result;
//    }
//
//}
