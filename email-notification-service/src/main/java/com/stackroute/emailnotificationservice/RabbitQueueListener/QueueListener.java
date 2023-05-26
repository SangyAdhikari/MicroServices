package com.stackroute.emailnotificationservice.RabbitQueueListener;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.emailnotificationservice.FeignClient.FeignClientInterface;
import com.stackroute.emailnotificationservice.Model.BookingEmail;
import com.stackroute.emailnotificationservice.Model.CancellationEmail;
import com.stackroute.emailnotificationservice.RabbitMq.MqConfigure;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class QueueListener {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    FeignClientInterface feignClientObject;

    private String key="rzp_test_B2hboJXaufQJZD";
    private String secret="h83cQ6lheLbmEC5jjBEBoA4r";

    SimpleMailMessage message=new SimpleMailMessage();                              //Simple Mail Message class to responsible to handle email trigger part

    @RabbitListener(queues = MqConfigure.QUEUE1)
    public void bookingListner(BookingEmail bookingEmail) throws RazorpayException, IOException {
        RazorpayClient razorpay = new RazorpayClient(key,secret);                       //Declaring key ID and secret key - To get payment details in this case we need only email ID
        Payment payment=razorpay.payments.fetch(bookingEmail.getPaymentId());            //Fetching payment Details by its payment ID
        message.setFrom("evento.bookconfirmation@gmail.com");
        message.setTo(payment.get("email").toString());                                 //Getting email ID from payment
        message.setSubject("Booking confirmation email for Booking ID : "+ bookingEmail.get_id());
        message.setText("\n \n This is to confirm that ticket has been successfully booked for the below show"
                +"\n Movie Name : "+feignClientObject.getEvent(bookingEmail.getEventId()).getEventName()
                +"\n Event Type : "+feignClientObject.getEvent(bookingEmail.getEventId()).getEventType()
                +"\n Duration of the Show : "+feignClientObject.getEvent(bookingEmail.getEventId()).getEventDuration()
                +"\n Cast & Crew : "+feignClientObject.getEvent(bookingEmail.getEventId()).getCastAndCrew().toString()
                +"\n Show Date : "+ bookingEmail.getEventDate()
                +"\n Show Timing : "+ bookingEmail.getEventTime()
                +"\n Ticket Fair : Rs."+ bookingEmail.getTicketFair()
                +"\n Booked Seat Numbers : "+ bookingEmail.getSeatNumbers().toString()
                +"\n Booked Date : "+ bookingEmail.getBookingDate()
                +"\n Booked Time : "+ bookingEmail.getBookingTime()
                +"\n Theatre Address : "+feignClientObject.getTheatre(bookingEmail.getTheatreId()).getTheatreName()+", \n"
                                       +" "+feignClientObject.getTheatre(bookingEmail.getTheatreId()).getStreet()+", \n"
                                       +" "+feignClientObject.getTheatre(bookingEmail.getTheatreId()).getDistrict()+", \n"
                                       +" "+feignClientObject.getTheatre(bookingEmail.getTheatreId()).getState()+", \n"
                                       +" "+feignClientObject.getTheatre(bookingEmail.getTheatreId()).getPinCode()
                +"\n \n Payment Details : "
                +"\n Transaction ID : "+payment.get("id")
                +"\n Paid Amount : "+ (((Integer) payment.get("amount")).intValue()/100) //Converting Paise into Rupees
                +"\n Contact Number : "+payment.get("contact")
                +"\n Transaction Date & Time : "+payment.get("created_at").toString()                        
                +"\n Transaction Status : "+payment.get("status")
                +"\n \n Enjoy your Show......."
                +"\n \n Thanks,"
                +"\n Evento Team...");
        javaMailSender.send(message);
        System.out.println("Booking confirmation Email has been to end user : "+ bookingEmail.getUserEmailId());
    }

    @RabbitListener(queues = MqConfigure.QUEUE2)
    public void cancellationListener(CancellationEmail cancellationEmail) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(key,secret);                       //Declaring key ID and secret key - To get payment details in this case we need only email ID
        message.setFrom("evento.bookconfirmation@gmail.com");
        message.setTo(cancellationEmail.getUserEmailId());
        message.setSubject("Cancellation confirmation email for Booking ID : " +cancellationEmail.get_id()+" , Refund ID - "+cancellationEmail.getRefundId());
        message.setText("This is to confirm that ticket has been successfully cancelled for the below show"
                        +"\n Refund ID : "+cancellationEmail.getRefundId()
                        +"\n Refund Status : "+cancellationEmail.getRefundStatus()
                        +"\n Refund Amount : Rs."+cancellationEmail.getRefundedAmount()
                        +"\n Movie Name : "+feignClientObject.getEvent(cancellationEmail.getEventId()).getEventName()
                        +"\n Event Type : "+feignClientObject.getEvent(cancellationEmail.getEventId()).getEventType()
                        +"\n Cancelled Seat Numbers : "+ cancellationEmail.getSeatNumbers().toString()
                        +"\n Show Date : "+ cancellationEmail.getEventDate()
                        +"\n Show Timing : "+ cancellationEmail.getEventTime()
                        +"\n Ticket Cancelled Date : "+cancellationEmail.getCancelledDate()
                        +"\n Ticket Cancelled Time : "+cancellationEmail.getCancelledTime()
                        +"\n Theatre Address : "+feignClientObject.getTheatre(cancellationEmail.getTheatreId()).getTheatreName()+", "
                                                +" "+feignClientObject.getTheatre(cancellationEmail.getTheatreId()).getStreet()+", \n"
                                                +" "+feignClientObject.getTheatre(cancellationEmail.getTheatreId()).getDistrict()+", \n"
                                                +" "+feignClientObject.getTheatre(cancellationEmail.getTheatreId()).getState()+", \n"
                                                +" "+feignClientObject.getTheatre(cancellationEmail.getTheatreId()).getPinCode()
                        +"\n \n Thanks for choosing Evento app to book shows..."
                        +"\n Evento Team...");
        javaMailSender.send(message);
        System.out.println("Cancellation confirmation Email has been to end user : "+ cancellationEmail.getUserEmailId());
    }
}
