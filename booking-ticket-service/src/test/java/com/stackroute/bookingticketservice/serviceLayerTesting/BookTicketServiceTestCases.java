package com.stackroute.bookingticketservice.serviceLayerTesting;

import com.stackroute.bookingticketservice.Models.BookTickets;
import com.stackroute.bookingticketservice.Models.RegisterShows;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {BookTicketServiceTestCases.class})
public class BookTicketServiceTestCases {

    RegisterShows fakeObj1=new RegisterShows(1,2,1,"bharath_raja_kurrala@gmail.com","Chennai","Tamil",LocalDate.of(2022,7,7),LocalTime.of(9,0),List.of("A21","A23","A23"));
    RegisterShows fakeObj2=new RegisterShows(2,1,1,"gangeswaransmart@gmail.com","Kochin","Malayalam",LocalDate.of(2022,7,7),LocalTime.of(10,0),List.of("V11","V12"));

    BookTickets tempObj1=new BookTickets("BOOK-ID-1",1,2,1,"PAYMENTID","nandhakumar@gmail.com",LocalDate.of(2022,7,7),LocalTime.of(11,0),List.of("Y4","Y5","Y6","Y7"),String.valueOf(LocalDate.now()),String.valueOf(LocalTime.now()),420.0);
    BookTickets tempObj2=new BookTickets("BOOK-ID-2",3,2,1,"PAYMENTID","yogesh@gmail.com",LocalDate.of(2022,8,8),LocalTime.of(11,0),List.of("A11","A12","A13"),String.valueOf(LocalDate.now()),String.valueOf(LocalTime.now()),360.0);
    List<BookTickets> bookedTickets=new ArrayList<>();      //To store all booked tickets..

    @Test
    @Order(1)
    public void testBookTicketForShow(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        int showId=1; String emailId="gangeswaransmart@gmail.com"; List<String> seatNumbers1=List.of("A1","A2","A3");
        BookTickets fakeObj3=new BookTickets();
        RegisterShows tempObj=shows.stream().filter(object->object.get_id()==showId).findAny().get();
        fakeObj3.set_id("BOOK-ID-3");
        fakeObj3.setEventId(tempObj.getEventId());
        fakeObj3.setShowId(showId);
        fakeObj3.setTheatreId(tempObj.getTheatreId());
        fakeObj3.setUserEmailId(emailId);
        fakeObj3.setEventDate(tempObj.getEventDate());
        fakeObj3.setEventTime(tempObj.getEventTime());
        fakeObj3.setSeatNumbers(seatNumbers1);
        fakeObj3.setBookingDate(String.valueOf(LocalDate.now()));
        fakeObj3.setBookingTime(String.valueOf(LocalTime.now()));
        bookedTickets.add(fakeObj3);
        assertEquals("BOOK-ID-3",fakeObj3.get_id());                        //Positive Test Case
    }

    @Test
    @Order(2)
    public void testSeatSelection(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        int showId=1; List<String> seatNumbers1=List.of("A1","A2","A3");
        RegisterShows tempObj=shows.stream().filter(object->object.get_id()==showId).findAny().get();
        if(tempObj.getBookedSeats().stream().anyMatch(seatNumbers1::contains))
            assertEquals("Seat has been already picked by another user","Seat has been already picked by another user");    //Negative Test Case
        else {
            List<String> seatNumbers2=List.of("E34","E56");
            if(!tempObj.getBookedSeats().stream().anyMatch(seatNumbers2::contains))
                assertEquals("Valid Seat Selection","Valid Seat Selection");                                                //Positive Test Case
        }
    }

    @Test
    @Order(3)
    public void testGetShowDetailsById() {
        List<BookTickets> bookedTickets=new ArrayList<>();
        bookedTickets.add(tempObj1);
        bookedTickets.add(tempObj2);
        String bookId1="BOOK-ID-1",bookId2="BOOK-ID-10";
        long posTest=bookedTickets.stream().filter(object->object.get_id().equals(bookId1)).count();
        assertEquals(1,posTest);                                                                //Positive Test
        long negTest=bookedTickets.stream().filter(object->object.get_id().equals(bookId2)).count();
        assertEquals(0,negTest);                                                                //Negative Test
    }

    @Test
    @Order(4)
    public void testGetShowDetailsByEmail(){
       bookedTickets.add(tempObj1);
       bookedTickets.add(tempObj2);
        String emailId1="yogesh@gmail.com",emailId2="balamurugan@gmail.com";
        long posTest=bookedTickets.stream().filter(object->object.getUserEmailId().equals(emailId1)).count();
        assertEquals(1,posTest);                                                                //Positive Test
        long negTest=bookedTickets.stream().filter(object->object.getUserEmailId().equals(emailId2)).count();
        assertEquals(0,negTest);                                                                //Negative Test
    }

    @Test
    @Order(5)
    public void testGetAllBookedSeatsForParticularShow(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        int showId1=1,showId2=4;
        int posTest=shows.stream().filter(object->object.get_id()==showId1).map(object->object.getBookedSeats().size()).findAny().get();
        assertEquals(3,posTest);                                                                 //Positive Test Case
        long negTest=shows.stream().filter(object->object.get_id()==showId2).count();
        assertEquals(0,negTest);                                                                 //Negative Test Case
    }
}
