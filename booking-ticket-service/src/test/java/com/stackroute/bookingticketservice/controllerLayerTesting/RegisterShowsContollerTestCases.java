package com.stackroute.bookingticketservice.controllerLayerTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookingticketservice.Controllers.RegisterEventController;
import com.stackroute.bookingticketservice.Models.RegisterShows;
import com.stackroute.bookingticketservice.Services.RegisterEventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
public class RegisterShowsContollerTestCases {

    @Autowired
    MockMvc mockMvc;

    @Mock
    RegisterEventService service;

    @InjectMocks
    RegisterEventController controller;

    private List<RegisterShows> lists;

    RegisterShows fakeObj1,fakeObj2;

    @BeforeEach
    public void setUp() {
        this.lists = new ArrayList<>();
        fakeObj1 = new RegisterShows(1, 2, 1, "bharath_raja_kurrala@gmail.com", "Chennai", "Tamil", LocalDate.of(2022,7,10), LocalTime.now(), List.of("A21", "A23", "A23"));
        fakeObj2 = new RegisterShows(2, 1, 1, "gangeswaransmart@gmail.com", "Kochin", "Malayalam", LocalDate.of(2022,7,9), LocalTime.now(), List.of("V11", "V12"));
        this.lists.add(fakeObj1);
        this.lists.add(fakeObj2);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    public void tearDown(){
        fakeObj1=null; fakeObj2=null;
    }

    @Test
    @Order(1)
    public void testGetShows() throws Exception {
        when(service.getAllShows()).thenReturn(lists);
        this.mockMvc.perform(get("/evento/booking/shows/getshows").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getAllShows();                 //Positive Test Case
    }

    @Test
    @Order(2)
    public void testGetShowsByOrganizerEmailID() throws Exception {
        String emailId="gangeswaransmart";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(service.getShowsByOrganizerEmailId(emailId)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/shows/emailId/{emailId}",emailId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowsByOrganizerEmailId(emailId);       //Positive Test Case
        assertEquals(fakeList.size(),service.getShowsByOrganizerEmailId(emailId).size());
    }

    @Test
    @Order(3)
    public void testGetShowById() throws Exception {
        int showId1=1;
        when(service.getShowById(showId1)).thenReturn(fakeObj1);
        this.mockMvc.perform(get("/evento/booking/shows/showId/{showId}",showId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowById(showId1);           //Positive Test Case
    }

    @Test
    @Order(4)
    public void testCreateShow() throws Exception {
        RegisterShows fakeObj3=new RegisterShows();
        when(service.createShow(any(RegisterShows.class))).thenReturn(Optional.ofNullable(fakeObj3));
        this.mockMvc.perform(post("/evento/booking/shows/register-show").content(asJsonString(fakeObj3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));          //Positive Test Case
    }

    @Test
    @Order(5)
    public void testGetShowsByEventIdAndLocation() throws Exception {
        int eventId1=1; String location1="Kochin";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(service.getShowsByEventIdAndLocation(eventId1,location1)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/shows/location/{location}/eventId/{eventId}",location1,eventId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowsByEventIdAndLocation(eventId1,location1);           //Positive Test Case
        assertEquals(fakeObj2,fakeList.get(0));
    }

    @Test
    @Order(6)
    public void testGetShowsByEventIdAndTheaterId() throws Exception {
        int eventId1=2,theatreId1=1;
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        when(service.getShowsByTheatreIdAndEventId(theatreId1,eventId1)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/shows//theatreId/{theatreId}/eventId/{eventId}",theatreId1,eventId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowsByTheatreIdAndEventId(theatreId1,eventId1);          //Positive Test Case
        assertEquals(fakeObj1,fakeList.get(0));
    }

    @Test
    @Order(7)
    public void testGetShowsByEventIdAndDate() throws Exception {
        int eventId1=1; LocalDate eventDate1=LocalDate.of(2022,7,9);
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(service.getShowsByEventDateAndEventId(eventDate1,eventId1)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/shows/date/{eventDate}/eventId/{eventId}",eventDate1,eventId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowsByEventDateAndEventId(eventDate1,eventId1);        //Positive Test Case
        assertEquals(fakeObj2,fakeList.get(0));
    }

    @Test
    @Order(8)
    public void testGetShowsByLocationAndDateAndEventId() throws Exception {
        RegisterShows fakeObj3=new RegisterShows(3,1,1,"kunalvasthava@gmail.com","Kochin","Kannada",LocalDate.of(2022,7,9),LocalTime.of(11,30,0),List.of("B11","B13","B15"));
        List<RegisterShows> fakeList=new ArrayList<>();
        int eventId1=1; String location1="Kochin"; LocalDate localDate1=LocalDate.of(2022,7,9);
        fakeList.add(fakeObj2);
        fakeList.add(fakeObj3);
        when(service.getShowsByLocationAndDateAndEventId(location1,localDate1,eventId1)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/shows/location/{location}/date/{eventDate}/eventId/{eventId}",location1,localDate1,eventId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowsByLocationAndDateAndEventId(location1,localDate1,eventId1);        //Positive Test Case
        assertEquals(fakeList.size(),service.getShowsByLocationAndDateAndEventId(location1,localDate1,eventId1).size());
    }

    @Test
    @Order(9)
    public void testGetShowsByLanguageAndEventId() throws Exception {
        int eventId1=1; String language1="Malayalam";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(service.getShowsByLanguageAndEventId(language1,eventId1)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/shows/language/{language}/eventId/{eventId}",language1,eventId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowsByLanguageAndEventId(language1,eventId1);                          //Positive Test Case
        assertEquals(fakeObj2,fakeList.get(0));
    }

    @Test
    @Order(10)
    public void testGetShowsByLocation() throws Exception {
        String location1="Kochin";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(service.getShowsByLocation(location1)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/shows/location/{location}",location1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowsByLocation(location1);                                             //Positive Test Case
        assertEquals(fakeObj2,fakeList.get(0));
    }

    //This method will invoke when we create new shows ( POST )
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
