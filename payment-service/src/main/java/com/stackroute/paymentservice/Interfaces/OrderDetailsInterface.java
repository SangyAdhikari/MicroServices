package com.stackroute.paymentservice.Interfaces;

import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.Models.OrderDetails;

import java.util.List;
import java.util.Map;

public interface OrderDetailsInterface {
    String saveOrderDetails(Map<String,Object> mapper) throws RazorpayException;            //To store order details
    String updatePaymentDetails(Map<String,Object> mapper) throws RazorpayException;        //Need to update payment status after payment process
    OrderDetails getPaymentDetailsById(String paymentId);                                   //To get payment details by its ID
    List<OrderDetails> getPaymentDetailsByEmail(String emailId);                             //To get payment histories by email
}
