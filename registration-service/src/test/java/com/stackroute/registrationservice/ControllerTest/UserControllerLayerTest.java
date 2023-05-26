//package com.stackroute.registrationservice.ControllerTest;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.registrationservice.Controller.UserController;
//import com.stackroute.registrationservice.Entity.RegisterUser;
//import com.stackroute.registrationservice.EnumClass.UserType;
//import com.stackroute.registrationservice.Services.UserServiceImpl;
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
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//@WebMvcTest(UserControllerLayerTest.class)
//public class UserControllerLayerTest {
//	RegisterUser trialObj;
//
//	 @Autowired
//	    MockMvc mockMvc;
//
//	    @Mock
//	   UserServiceImpl serviceImpl;
//
//	    @InjectMocks
//	     UserController controller;
//
//		@BeforeEach
//		public void setup(){
//			mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
//			trialObj=new RegisterUser("nitesh@gmail.com","Nitesh","Nitish@2310",837283606,"Bangalore",UserType.User);
//		}
//
//		     @Test
//		    public void testNewUser() throws  Exception{
//
//
//		        when(serviceImpl.newUser(any(RegisterUser.class))).thenReturn((trialObj));
//					mockMvc.perform(post("/evento/register/register-user")
//
//					                .contentType(MediaType.APPLICATION_JSON)
//					                .content(jsonToString(trialObj)))
//					.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//		    }
//		     @Test
//		     public void testUpdateUserPassword() throws Exception
//		     {
//		    	 String emailId="nitesh@gmail.com";
//		    	 String password="Nitesh@2310";
//				        when(serviceImpl.updateUserPassword(emailId,trialObj)).thenReturn(trialObj);
//							mockMvc.perform(put("/evento/register/update/update-user-password/nitesh@gmail.com/Nitesh@2310")
//							                .contentType(MediaType.APPLICATION_JSON)
//							                .content(jsonToString(trialObj)))
//							.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//				    }
//		     @Test
//		     public void testUpdateUserInfo() throws Exception {
//		    	 String emailId="nitesh@gmail.com";
//		    	 String password="Nitesh@2310";
//		    	 long mobileNo=837283606;
//				        when(serviceImpl.updateUserInfo(emailId,password,mobileNo)).thenReturn(trialObj);
//							mockMvc.perform(put("/evento/register/update/update-user/nitesh@gmail.com/Nitesh@2310/837283606")
//							                .contentType(MediaType.APPLICATION_JSON)
//							                .content(jsonToString(trialObj)))
//							.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//
//		     }
//		     @Test
//		     public void testGetAllUsers() throws Exception {
//		    	 List<RegisterUser> list=new ArrayList<>();
//		    	 list.add(trialObj);
//					        when(serviceImpl.FetchAllUsers()).thenReturn(list);
//					        assertEquals(1, serviceImpl.FetchAllUsers().size());
//								mockMvc.perform(get("/evento/register/get/users")
//								                .contentType(MediaType.APPLICATION_JSON)
//								                .content(jsonToString(list)))
//								.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//		     }
//		     @Test
//		     public void testGetOneUser() throws Exception {
//		    	 String emailId="nitesh@gmail.com";
//
//				        when(serviceImpl.getOneUser(emailId)).thenReturn(trialObj);
//							mockMvc.perform(get("/evento/register/get/user/nitesh@gmail.com")
//							                .contentType(MediaType.APPLICATION_JSON)
//							                .content(jsonToString(trialObj)))
//							.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//
//		     }
//
//		     private static String jsonToString(final Object o) throws JsonProcessingException {
//		         String result;
//		         try {
//		             ObjectMapper mapper = new ObjectMapper();
//		             String jsonContent = mapper.writeValueAsString(o);
//		             result = jsonContent;
//		             return result;
//
//		         } catch (JsonProcessingException e) {
//		             result = "JsonProcessingException";
//		         }
//		         return result;
//		     }
//
//
//
//
//}
