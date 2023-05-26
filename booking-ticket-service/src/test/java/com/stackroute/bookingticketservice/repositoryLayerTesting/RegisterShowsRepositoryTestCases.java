package com.stackroute.bookingticketservice.repositoryLayerTesting;

import com.stackroute.bookingticketservice.Models.RegisterShows;
import com.stackroute.bookingticketservice.Repositories.RegisterRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
public class RegisterShowsRepositoryTestCases {

    @Mock
    RegisterRepo repository;

    private List<RegisterShows> lists;

    RegisterShows fakeObj1,fakeObj2;

    @BeforeEach
    public void setUp() {
        this.lists = new ArrayList<>();
        fakeObj1 = new RegisterShows(1, 2, 1, "bharath_raja_kurrala@gmail.com", "Chennai", "Tamil", LocalDate.now(), LocalTime.now(), List.of("A21", "A23", "A23"));
        fakeObj2 = new RegisterShows(2, 1, 1, "gangeswaransmart@gmail.com", "Kochin", "Malayalam", LocalDate.now(), LocalTime.now(), List.of("V11", "V12"));
        this.lists.add(fakeObj1);
        this.lists.add(fakeObj2);
    }

    @AfterEach
    public void tearDown(){
        fakeObj1=null; fakeObj2=null;
    }

    // @Test
    // @Order(1)
    // public void testFindByEventIdAndTheatreIdAndOrganizerEmailIdAndLocationAndEventDate(){
    //     List<RegisterShows> fakeList=new ArrayList<>();
    //     fakeList.add(fakeObj2);
    //     when(repository.findByTheatreIdAndOrganizerEmailIdAndLocationAndEventDate(1,"gangeswaransmart@gmail.com","Kochin",LocalDate.now())).thenReturn(fakeList);
    //     RegisterShows validateObj=repository.findByTheatreIdAndOrganizerEmailIdAndLocationAndEventDate(1,"gangeswaransmart@gmail.com","Kochin",LocalDate.now()).get(0);
    //     //Positive Test Case
    //     assertEquals(fakeObj2.getEventId(),validateObj.getEventId());
    //     assertEquals(fakeObj2.getTheatreId(),validateObj.getTheatreId());
    //     assertEquals(fakeObj2.getOrganizerEmailId(),validateObj.getOrganizerEmailId());
    //     assertEquals(fakeObj2.getLocation(),validateObj.getLocation());
    //     assertEquals(fakeObj2.getEventDate(),validateObj.getEventDate());
    // }

    @Test
    @Order(2)
    public void testFindByOrganizerEmailId(){
        when(repository.findByOrganizerEmailId("gangeswaransmart@gmail.com")).thenReturn(List.of(fakeObj2));
        assertEquals(1,repository.findByOrganizerEmailId("gangeswaransmart@gmail.com").size());        //Positive Test Case
    }

    @Test
    @Order(3)
    public void testFindByEventDateGreaterThanEqual(){
        when(repository.findByEventDateGreaterThanEqual(LocalDate.now())).thenReturn(lists);
        assertEquals(lists.size(),repository.findByEventDateGreaterThanEqual(LocalDate.now()).size());          //Positive Test Case
    }

    @Test
    @Order(4)
    public void testFindByEventIdAndLocationAndEventDateGreaterThanEqual(){
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        when(repository.findByEventIdAndLocationAndEventDateGreaterThanEqual(2,"Chennai",LocalDate.now())).thenReturn(fakeList);
        RegisterShows validateObj=repository.findByEventIdAndLocationAndEventDateGreaterThanEqual(2,"Chennai",LocalDate.now()).get(0);
        //Positive Test Case
        assertEquals(fakeObj1.getEventId(),validateObj.getEventId());
        assertEquals(fakeObj1.getLocation(),validateObj.getLocation());
    }

    @Test
    @Order(5)
    public void testFindByTheatreIdAndEventIdAndEventDateGreaterThanEqual(){
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(repository.findByTheatreIdAndEventIdAndEventDateGreaterThanEqual(1,1,LocalDate.now())).thenReturn(fakeList);
        RegisterShows validateObj=repository.findByTheatreIdAndEventIdAndEventDateGreaterThanEqual(1,1,LocalDate.now()).get(0);
        //Positive Test Case
        assertEquals(fakeObj2.getTheatreId(),validateObj.getTheatreId());
        assertEquals(fakeObj2.getEventId(),validateObj.getEventId());
    }

    @Test
    @Order(6)
    public void testFindByEventDateAndEventId(){
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        when(repository.findByEventDateAndEventId(LocalDate.now(),2)).thenReturn(fakeList);
        RegisterShows validateObj=repository.findByEventDateAndEventId(LocalDate.now(),2).get(0);
        //Positive Test Case
        assertEquals(fakeObj1.getEventDate(),validateObj.getEventDate());
        assertEquals(fakeObj1.getEventId(),validateObj.getEventId());
    }

    @Test
    @Order(7)
    public void testFindByLocationAndEventDateAndEventId(){
        RegisterShows fakeObj3 = new RegisterShows(1, 2, 1, "nandhakumar@gmail.com", "Chennai", "Tamil", LocalDate.now(), LocalTime.now(), List.of("B23", "A12", "A34"));
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        fakeList.add(fakeObj3);
        when(repository.findByLocationAndEventDateAndEventId("Chennai",LocalDate.now(),2)).thenReturn(fakeList);
        List<RegisterShows> tempList=repository.findByLocationAndEventDateAndEventId("Chennai",LocalDate.now(),2);
        //Positive Test Case
        assertEquals(fakeList.size(),tempList.size());
    }

    @Test
    @Order(8)
    public void testFindByLanguageAndEventIdAndEventDateGreaterThanEqual(){
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj2);
        when(repository.findByLanguageAndEventIdAndEventDateGreaterThanEqual("Malayalam",1,LocalDate.now())).thenReturn(fakeList);
        RegisterShows validateObj=repository.findByLanguageAndEventIdAndEventDateGreaterThanEqual("Malayalam",1,LocalDate.now()).get(0);
        //Positive Test Case
        assertEquals(fakeObj2.getLanguage(),validateObj.getLanguage());
        assertEquals(fakeObj2.getEventId(),validateObj.getEventId());
    }

    @Test
    @Order(9)
    public void testFindByLocationAndEventDateGreaterThanEqual(){
        List<RegisterShows> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        when(repository.findByLocationAndEventDateGreaterThanEqual("Chennai",LocalDate.now())).thenReturn(fakeList);
        RegisterShows validateObj=repository.findByLocationAndEventDateGreaterThanEqual("Chennai",LocalDate.now()).get(0);
        //Positive Test Case
        assertEquals(fakeObj1.getLocation(),validateObj.getLocation());
    }
}
