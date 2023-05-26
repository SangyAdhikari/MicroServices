package com.stackroute.paymentservice.Controllers;

import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.Models.OrderDetails;
import com.stackroute.paymentservice.Services.OrderDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
//@CrossOrigin(origins = "*")
@RequestMapping("/evento/payment-gateway")
public class OrderDetailsController {

    @Autowired
    OrderDetailsService service;

    @GetMapping("/paymentPage")
    public String paymentPage(){
        return "index";
    }

    @ApiResponse(description = "This POST API will be invoked from front end after clicking BOOK TICKETS Button.. It requires User email ID, amount..")
    @PostMapping("/create-order")
    @ResponseBody
    public ResponseEntity<String> saveOrderDetails(@RequestBody Map<String,Object> mapper) throws RazorpayException {
        return new ResponseEntity<>(service.saveOrderDetails(mapper),HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch Payment Details by ID", description = "To fetch payment details ticket by payment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Payment details have been fetched.."),
            @ApiResponse(responseCode = "400", description = "No such Payment ID found in database..")
    })
    @GetMapping("/payment-details/paymentId/{paymentId}")
    public ResponseEntity<OrderDetails> getPaymentDetailsById(@PathVariable String paymentId){
        return new ResponseEntity<>(service.getPaymentDetailsById(paymentId), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Fetch Payment histories by email ID", description = "To fetch payment histories by email ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Payment details have been fetched.."),
            @ApiResponse(responseCode = "400", description = "No Transaction has been made from your email account..")
    })
    @GetMapping("/payment-details/emailId/{emailId}")
    public ResponseEntity<List<OrderDetails>> getPaymentDetailsByEmail(@PathVariable String emailId){
        return new ResponseEntity<>(service.getPaymentDetailsByEmail(emailId),HttpStatus.ACCEPTED);
    }

    @ApiResponse(responseCode = "202",description = "This API is to update all details like ( Payment ID, Status, No of attempts, Booking ID... Don't use this API call from postman or browser.. This will be invoked after successful payment process..")
    @PutMapping("/payment-details")
    @ResponseBody
    public String updatePaaymentDetails(@RequestBody Map<String,Object> mapper) throws RazorpayException {
        return service.updatePaymentDetails(mapper);
    }
}
