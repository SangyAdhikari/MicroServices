package com.stackroute.bookingticketservice.serviceLayerTesting;

import com.stackroute.bookingticketservice.Exception.CustomizedException;
import com.stackroute.bookingticketservice.Models.RegisterShows;
import com.stackroute.bookingticketservice.Repositories.RegisterRepo;
import com.stackroute.bookingticketservice.Services.RegisterEventService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RegisterShowsServiceTestCases.class})
public class RegisterShowsServiceTestCases {
    @Mock
    RegisterRepo repository;

    @InjectMocks
    RegisterEventService service;

    RegisterShows fakeObj1=new RegisterShows(1,2,1,"bharath_raja_kurrala@gmail.com","Chennai","Tamil",LocalDate.now(), LocalTime.now(),List.of("A21","A23","A23"));
    RegisterShows fakeObj2=new RegisterShows(2,1,1,"gangeswaransmart@gmail.com","Kochin","Malayalam",LocalDate.now(),LocalTime.now(),List.of("V11","V12"));

    @Test
    @Order(1)
    public void testGetAllShows(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        when(repository.findByEventDateGreaterThanEqual(LocalDate.now())).thenReturn(shows);
        assertEquals(2, service.getAllShows().size());                      //Positive Test Case
        assertNotEquals(shows.size()-1,service.getAllShows().size());     //Negative Test Case
    }

    @Test
    @Order(2)
    public void testGetShowsByOrganizerEmailId(){
        String emailId="gangeswaransmart@gmail.com";
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj2);
        when(repository.findByOrganizerEmailId(emailId)).thenReturn(shows);
        assertEquals(1,service.getShowsByOrganizerEmailId(emailId).size());    //Positive Test Case
        when(repository.findByOrganizerEmailId("nandhu@gmail.com")).thenThrow(CustomizedException.class);       //Negative Test Case
    }

    @Test
    @Order(3)
    public void testGetShowById(){
        List<RegisterShows> shows=new ArrayList<>();
        int showId=2;
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        RegisterShows temObj=shows.get(showId-1);
        when(repository.findById(showId)).thenReturn(Optional.ofNullable(temObj));
        assertEquals(temObj,service.getShowById(showId));                           //Positive Test Case
        List<Integer> ids=shows.stream().map(object->object.get_id()).collect(Collectors.toList());
        int invalidshowId=6;
        assertEquals(false,ids.contains(invalidshowId));                    //Negative Test Case
    }

    @Test
    @Order(4)
    public void testCreateShow(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        RegisterShows fakeObj3=new RegisterShows(3,1,4,"kunalvasthava@gmail.com","Bangalore","Kannada", LocalDate.of(2022,7,9),LocalTime.of(12,45,0),List.of("B11","B13","B15"));
        shows.add(fakeObj3);
        int insertedId=fakeObj3.get_id();
        List<Integer> collectIds=shows.stream().map(object->object.get_id()).collect(Collectors.toList());
        assertEquals(true,collectIds.contains(insertedId));                 //Positive Test Case
    }

    @Test
    @Order(5)
    public void testGetShowsByEventIdAndLocation(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        int eventId1=1; String location1="Kochin";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(repository.findByEventIdAndLocationAndEventDateGreaterThanEqual(eventId1,location1,LocalDate.now())).thenReturn(fakeList);
        assertEquals(fakeList.size(),service.getShowsByEventIdAndLocation(eventId1,location1).size());          //Positive Test Case
        int eventId2=3; String location2="Kochin";
        when(repository.findByEventIdAndLocationAndEventDateGreaterThanEqual(eventId2,location2,LocalDate.now())).thenThrow(CustomizedException.class);     //Negative Test Case
    }

    @Test
    @Order(6)
    public void testGetShowsByTheatreIdAndEventId(){
        List<RegisterShows> shows=new ArrayList<>();
        RegisterShows fakeObj3=new RegisterShows(3,1,1,"kunalvasthava@gmail.com","Bangalore","Kannada",LocalDate.of(2022,7,10),LocalTime.of(11,30,00),List.of("B11","B13","B15"));
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        shows.add(fakeObj3);
        int theatreId1=1,eventId1=1;
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        fakeList.add(fakeObj3);
        when(repository.findByTheatreIdAndEventIdAndEventDateGreaterThanEqual(theatreId1,eventId1,LocalDate.now())).thenReturn(fakeList);
        assertEquals(fakeList.size(),service.getShowsByTheatreIdAndEventId(theatreId1,eventId1).size());        //Positive Test Case
        int theatreId2=2,eventId2=2;
        when(repository.findByTheatreIdAndEventIdAndEventDateGreaterThanEqual(theatreId2,eventId2,LocalDate.now())).thenThrow(CustomizedException.class);   //Negative Test Case
    }

    @Test
    @Order(7)
    public void testGetShowsByEventDateAndEventId(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        LocalDate eventDate1=LocalDate.of(2022,7,7); int eventId1=1;
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(repository.findByEventDateAndEventId(eventDate1,eventId1)).thenReturn(fakeList);
        assertEquals(fakeList.size(),service.getShowsByEventDateAndEventId(eventDate1,eventId1).size());        //Positive Test Case
        LocalDate eventDate2=LocalDate.of(2022,7,10); int eventId2=2;
        when(repository.findByEventDateAndEventId(eventDate2,eventId2)).thenThrow(CustomizedException.class);   //Negative Test Case
    }

    @Test
    @Order(8)
    public void testGetShowsByLocationAndDateAndEventId(){
        List<RegisterShows> shows=new ArrayList<>();
        RegisterShows fakeObj3=new RegisterShows(3,1,1,"kunalvasthava@gmail.com","Kochin","Kannada",LocalDate.of(2022,7,7),LocalTime.of(11,30,0),List.of("B11","B13","B15"));
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        shows.add(fakeObj3);
        int eventId1=1; LocalDate eventDate1=LocalDate.of(2022,7,7); String location1="Kochin";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        fakeList.add(fakeObj3);
        when(repository.findByLocationAndEventDateAndEventId(location1,eventDate1,eventId1)).thenReturn(fakeList);
        assertEquals(fakeList.size(),service.getShowsByLocationAndDateAndEventId(location1,eventDate1,eventId1).size());    //Positive Test Case
        int eventId2=4; LocalDate eventDate2=LocalDate.of(2022,7,10); String location2="Kochin";
        when(repository.findByLocationAndEventDateAndEventId(location2,eventDate2,eventId2)).thenThrow(CustomizedException.class);   //Negative Test Case
    }

    @Test
    @Order(9)
    public void testgetShowsByLanguageAndEventId(){
        List<RegisterShows> shows=new ArrayList<>();
        RegisterShows fakeObj3=new RegisterShows(1,2,1,"bharath_raja_kurrala@gmail.com","Chennai","Tamil",LocalDate.of(2022,7,7),LocalTime.of(11,30,0),List.of("A21","A23","A23"));
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        shows.add(fakeObj3);
        int eventId1=2; String language1="Tamil";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        fakeList.add(fakeObj3);
        when(repository.findByLanguageAndEventIdAndEventDateGreaterThanEqual(language1,eventId1,LocalDate.now())).thenReturn(fakeList);
        assertEquals(fakeList.size(),service.getShowsByLanguageAndEventId(language1,eventId1).size());      //Positive Test Case
        int eventId2=5; String language2="Tamil";
        when(repository.findByLanguageAndEventIdAndEventDateGreaterThanEqual(language2,eventId2,LocalDate.now())).thenThrow(CustomizedException.class); //Negative Test Case
    }

    @Test
    @Order(10)
    public void testGetShowsByLocation(){
        List<RegisterShows> shows=new ArrayList<>();
        shows.add(fakeObj1);
        shows.add(fakeObj2);
        String location1="Kochin";
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(repository.findByLocationAndEventDateGreaterThanEqual(location1,LocalDate.now())).thenReturn(fakeList);
        assertEquals(fakeList.size(),service.getShowsByLocation(location1).size());             //Positive Test Case
        String location2="Bangalore";
        when(repository.findByLocationAndEventDateGreaterThanEqual(location2,LocalDate.now())).thenThrow(CustomizedException.class);        //Negative Test Case
    }
}
