package com.stackroute.paymentservice.Services;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.Exception.CustomizedException;
import com.stackroute.paymentservice.Interfaces.OrderDetailsInterface;
import com.stackroute.paymentservice.Models.OrderDetails;
import com.stackroute.paymentservice.Repositories.OrderDetailsRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderDetailsService implements OrderDetailsInterface {

    @Autowired
    OrderDetailsRepo repository;

    @Autowired
    OrderDetails orderDetails;

    @Override
    public String saveOrderDetails(Map<String,Object> mapper) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_B2hboJXaufQJZD", "h83cQ6lheLbmEC5jjBEBoA4r");            //Declaring key ID and secret key
        JSONObject orderRequest = new JSONObject();                                                                                //JSON object to store all order details further it will be created razorpay entity
        orderDetails.setAmount(Integer.parseInt(mapper.get("amount").toString()));                                  //Setting amount value
        orderDetails.setCurrencyType("INR");                                                                        //Setting currency type
        orderRequest.put("amount",Integer.parseInt(mapper.get("amount").toString())*100);                           //Multiply by 100 as this will be converted as PAISE
        orderRequest.put("currency","INR");
        Order order = razorpay.orders.create(orderRequest);                                                         //Storing order details in razorpay order entity
        orderDetails.set_id(order.get("id"));
        orderDetails.setNoOfAttempts(order.get("attempts"));
        orderDetails.setUserEmailId(mapper.get("emailId").toString());
        orderDetails.setOrderStatus(order.get("status"));
        orderDetails.setTimestamp(order.get("created_at"));
        orderDetails.setBookingId("");
        repository.save(orderDetails);                                                                              //Storing order details in our database
        return order.toString();
    }

    @Override
    public String updatePaymentDetails(Map<String, Object> mapper) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_B2hboJXaufQJZD", "h83cQ6lheLbmEC5jjBEBoA4r");
        //Payment paymentDetails=razorpay.payments.fetch(mapper.get("payment_id").toString());
        Order order=razorpay.orders.fetch(mapper.get("order_id").toString());
        orderDetails=repository.findById(order.get("id").toString()).get();
        orderDetails.setNoOfAttempts(order.get("attempts"));
        orderDetails.setOrderStatus(order.get("status"));
        orderDetails.setBookingId(mapper.get("bookingId").toString());
        repository.save(orderDetails);
        return "Payment Details updated successfully!!";
    }

    @Override
    public OrderDetails getPaymentDetailsById(String paymentId) {
        Optional<OrderDetails> orderDetails=repository.findById(paymentId);
        if(orderDetails.isEmpty())
            throw new CustomizedException("No such Payment ID found in database..");
        return repository.findById(paymentId).get();
    }

    @Override
    public List<OrderDetails> getPaymentDetailsByEmail(String emailId) {
        List<OrderDetails> lists=repository.findByUserEmailId(emailId);
        if(lists.size()==0)
            throw new CustomizedException("No Transaction has been made from your email account..");
        return lists;
    }
}
