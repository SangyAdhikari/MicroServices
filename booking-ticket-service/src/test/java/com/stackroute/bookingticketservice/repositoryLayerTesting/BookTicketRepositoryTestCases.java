package com.stackroute.bookingticketservice.repositoryLayerTesting;

import com.stackroute.bookingticketservice.Models.BookTickets;
import com.stackroute.bookingticketservice.Repositories.BookTicketsRepo;
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
public class BookTicketRepositoryTestCases {

    @Mock
    BookTicketsRepo repository;

    private List<BookTickets> lists;

    BookTickets fakeObj1;

    @BeforeEach
    public void setUp() {
        this.lists = new ArrayList<>();
        fakeObj1 = new BookTickets("BOOK-ID-1",1,2,1,"PAYMENTID","nandhakumar@gmail.com",LocalDate.of(2022,7,7),LocalTime.of(11,0),List.of("Y4","Y5","Y6","Y7"),String.valueOf(LocalDate.now()),String.valueOf(LocalTime.now()),420.0);
        this.lists.add(fakeObj1);
    }

    @AfterEach
    public void tearDown(){
        fakeObj1=null; lists=null;
    }

    @Test
    @Order(1)
    public void testFindByUserEmailId(){
        String emailId="nandhakumar@gmail.com";
        List<BookTickets> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        when(repository.findByUserEmailId(emailId)).thenReturn(fakeList);
        assertEquals(emailId,repository.findByUserEmailId(emailId).get(0).getUserEmailId());        //Positive Test Case
    }
}
