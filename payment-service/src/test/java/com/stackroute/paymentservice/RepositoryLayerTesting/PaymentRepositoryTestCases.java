package com.stackroute.paymentservice.RepositoryLayerTesting;

import com.stackroute.paymentservice.Models.OrderDetails;
import com.stackroute.paymentservice.Repositories.OrderDetailsRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTestCases {

    @Mock
    OrderDetailsRepo repository;

    private List<OrderDetails> lists;

    OrderDetails fakeObj;

    @BeforeEach
    public void setUp() {
        this.lists = new ArrayList<>();
        fakeObj = new OrderDetails("order_JtHFIHsqv6Rwrr","EVENTO-BOOK-ID-1",120,"INR","paid",1,"gangeswaransmart@gmail.com", new Date());
        this.lists.add(fakeObj);
    }

    @AfterEach
    public void tearDown(){
        lists=null; fakeObj=null;
    }

    @Test
    @Order(1)
    public void testFindByUserEmailId(){
        String emailId="gangeswaransmart@gmail.com";
        List<OrderDetails> fakeList=new ArrayList<>();
        fakeList.add(fakeObj);
        when(repository.findByUserEmailId(emailId)).thenReturn(fakeList);
        assertEquals(emailId,repository.findByUserEmailId(emailId).get(0).getUserEmailId());     //Positive Test Case
    }
}
