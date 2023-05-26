package com.stackroute.bookingticketservice.controllerLayerTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookingticketservice.Controllers.BookTicketsController;
import com.stackroute.bookingticketservice.Models.BookTickets;
import com.stackroute.bookingticketservice.Models.RegisterShows;
import com.stackroute.bookingticketservice.Services.BookTicketsService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookTicketsControllerTestCases {

    @Autowired
    MockMvc mockMvc;

    @Mock
    BookTicketsService service;

    @InjectMocks
    BookTicketsController controller;

    private List<BookTickets> lists;

    BookTickets fakeObj1,fakeObj2;
    RegisterShows registerShows;

    @BeforeEach
    public void setUp() {
        this.lists = new ArrayList<>();
        fakeObj1 = new BookTickets("BOOK-ID-1",1,2,1,"PAYMENTID","nandhakumar@gmail.com",LocalDate.of(2022,7,7),LocalTime.of(11,0),List.of("Y4","Y5","Y6","Y7"),String.valueOf(LocalDate.now()),String.valueOf(LocalTime.now()),420.0);
        fakeObj2 = new BookTickets("BOOK-ID-2",3,2,1,"yogesh@gmail.com","PAYMENTID",LocalDate.of(2022,8,8),LocalTime.of(11,0),List.of("A11","A12","A13"),String.valueOf(LocalDate.now()),String.valueOf(LocalTime.now()),360.0);
        registerShows =new RegisterShows(1,2,1,"bharath_raja_kurrala@gmail.com","Chennai","Tamil",LocalDate.of(2022,7,7),LocalTime.of(9,0),List.of("A21","A23","A23"));
        this.lists.add(fakeObj1);
        this.lists.add(fakeObj2);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    public void tearDown(){
        fakeObj1=null; fakeObj2=null;
        registerShows =null;
    }

    @Test
    @Order(1)
    public void testBookShows() throws Exception {
        BookTickets fakeObj3=new BookTickets();
        when(service.bookTicketForShow(any(BookTickets.class))).thenReturn(Optional.ofNullable(fakeObj3));
        this.mockMvc.perform(post("/evento/booking/book/show").content(asJsonString(fakeObj3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));          //Positive Test Case
    }

    @Test
    @Order(2)
    public void testGetBookingDetailsById() throws Exception {
        String bookingId1="BOOK-ID-1";
        when(service.getShowDetailsById(bookingId1)).thenReturn(fakeObj1);
        this.mockMvc.perform(get("/evento/booking/book/show-details/bookingId/{bookingId}",bookingId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowDetailsById(bookingId1);        //Positive Test Case
        assertEquals(bookingId1,fakeObj1.get_id());
    }

    @Test
    @Order(3)
    public void testGetBookingDetailsByEmail() throws Exception {
        String emailId1="yogesh@gmail";
        List<BookTickets> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(service.getShowDetailsByEmail(emailId1)).thenReturn(fakeList);
        this.mockMvc.perform(get("/evento/booking/book/show-details/emailId/{userEmailId}",emailId1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getShowDetailsByEmail(emailId1);       //Positive Test Case
    }

    @Test
    @Order(4)
    public void testGetAllBookedSeatsByShowId() throws Exception {
        int showId=1;
        List<String> bookedSeats= registerShows.getBookedSeats();
        when(service.getAllBookedSeatsForParticularShow(showId)).thenReturn(bookedSeats);
        this.mockMvc.perform(get("/evento/booking/book/seat-mapping/showId/{showId}",showId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service,times(1)).getAllBookedSeatsForParticularShow(showId);        //Positive Test Case
        assertEquals(bookedSeats.size(),service.getAllBookedSeatsForParticularShow(showId).size());

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
