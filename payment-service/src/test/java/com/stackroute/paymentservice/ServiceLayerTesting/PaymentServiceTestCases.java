package com.stackroute.paymentservice.ServiceLayerTesting;

import com.stackroute.paymentservice.Models.OrderDetails;
import com.stackroute.paymentservice.Repositories.OrderDetailsRepo;
import com.stackroute.paymentservice.Services.OrderDetailsService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PaymentServiceTestCases.class})
public class PaymentServiceTestCases {

    @Mock
    OrderDetailsRepo repository;

    @InjectMocks
    OrderDetailsService service;

    OrderDetails fakeObj1=new OrderDetails("order_JtHFIHsqv6Rwrr","EVENTO-BOOK-ID-1",120,"INR","paid",1,"gangeswaransmart@gmail.com", new Date());
    OrderDetails fakeObj2=new OrderDetails("order_JtHmW7U5mpcDQy","EVENTO-BOOK-ID-2",360,"INR","created",0,"gangeswaran04@gmail.com",new Date());

    @Test
    @Order(1)
    public void testSaveOrderDetails(){
        List<OrderDetails> fakeList=new ArrayList<>();
        OrderDetails fakeObj3=new OrderDetails("order_JtHmW7U5mpcYUi","EVENTO-BOOK-ID-3",480,"INR","created",0,"gangeswaranweb@gmail.com",new Date());
        fakeList.add(fakeObj1);
        fakeList.add(fakeObj2);
        fakeList.add(fakeObj3);
        long count=fakeList.stream().count();
        assertEquals(fakeList.size(),count);            //Positive Test Case
    }

    @Test
    @Order(2)
    public void testUpdatePaymentDetails(){
        List<OrderDetails> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        fakeList.add(fakeObj2);
        fakeList.stream().filter(object->object.get_id().equals("order_JtHmW7U5mpcDQy")).forEach(update->{update.setOrderStatus("paid"); update.setNoOfAttempts(1);});
        //Positive Test Case
        assertEquals(fakeObj2.getOrderStatus(),"paid");
        assertEquals(fakeObj2.getNoOfAttempts(),1);
    }

    @Test
    @Order(3)
    public void testGetPaymentDetailsById(){
        String paymentId="order_JtHFIHsqv6Rwrr";
        when(repository.findById(paymentId)).thenReturn(Optional.ofNullable(fakeObj1));
        assertEquals(fakeObj1.get_id(),service.getPaymentDetailsById(paymentId).get_id());      //Positive Test Case
    }

    @Test
    @Order(4)
    public void testGetPaymentDetailsByEmail(){
        String emailId="gangeswaransmart@gmail.com";
        when(repository.findByUserEmailId(emailId)).thenReturn(List.of(fakeObj1));
        assertEquals(fakeObj1.get_id(),service.getPaymentDetailsByEmail(emailId).get(0).get_id());  //Positive Test Case
    }
}
