package com.stackroute.paymentservice.ControllerLayerTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.paymentservice.Controllers.OrderDetailsController;
import com.stackroute.paymentservice.Models.OrderDetails;
import com.stackroute.paymentservice.Services.OrderDetailsService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTestCases {

    @Autowired
    MockMvc mockMvc;

    @Mock
    OrderDetailsService service;

    @InjectMocks
    OrderDetailsController controller;

    private List<OrderDetails> lists;

    OrderDetails fakeObj1,fakeObj2;

    @BeforeEach
    public void setUp(){
        this.lists=new ArrayList<>();
        fakeObj1=new OrderDetails("order_JtHFIHsqv6Rwrr","EVENTO-BOOK-ID-1",120,"INR","paid",1,"gangeswaransmart@gmail.com", new Date());
        fakeObj2=new OrderDetails("order_JtHmW7U5mpcDQy","EVENTO-BOOK-ID-2",360,"INR","created",0,"gangeswaran04@gmail.com",new Date());
        this.lists.add(fakeObj1);
        this.lists.add(fakeObj2);
        this.mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    public void tearDown(){
        fakeObj1=null;fakeObj2=null;
    }

    @Test
    @Order(1)
    public void testSaveOrderDetails() throws Exception {
        OrderDetails fakeObj3=new OrderDetails("order_JtHmW7U5mpcDQy","EVENTO-BOOK-ID-3",360,"INR","created",0,"gangeswaranweb@gmail.com",new Date());
        this.mockMvc.perform(post("/evento/payment-gateway/create-order").content(asJsonString(fakeObj3)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        //Positive Test Case
        assertEquals("order_JtHmW7U5mpcDQy",fakeObj3.get_id());
        assertEquals("EVENTO-BOOK-ID-3",fakeObj3.getBookingId());
    }

    @Test
    @Order(2)
    public void testGetPaymentDetailsById() throws Exception {
        String paymentId="order_JtHFIHsqv6Rwrr";
        List<OrderDetails> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        this.mockMvc.perform(get("/evento/payment-gateway/payment-details/paymentId/{paymentId}",paymentId)).andExpect(status().isAccepted());
        //Positive Test Case
        assertEquals(paymentId,fakeList.get(0).get_id());
    }

    @Test
    @Order(3)
    public void testGetPaymentDetailsByEmail() throws Exception {
        String emailId="gangeswaransmart@gmail.com";
        List<OrderDetails> fakeList=new ArrayList<>();
        fakeList.add(fakeObj1);
        this.mockMvc.perform(get("/evento/payment-gateway/payment-details/emailId/{emailId}",emailId)).andExpect(status().isAccepted());
        //Positive Test Case
        assertEquals(emailId,fakeList.get(0).getUserEmailId());
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
