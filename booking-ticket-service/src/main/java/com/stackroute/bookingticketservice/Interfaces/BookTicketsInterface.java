package com.stackroute.bookingticketservice.Interfaces;

import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import com.stackroute.bookingticketservice.Models.BookTickets;

import java.util.List;
import java.util.Optional;

public interface BookTicketsInterface {
    Optional<Object> bookTicketForShow(BookTickets bookTickets);   //To store details for booked tickets
    BookTickets getShowDetailsById(String bookId);            //To get booking details by booking ID
    List<BookTickets> getShowDetailsByEmail(String userEmail);      //To get booking details by user email ID
    List<String> getAllBookedSeatsForParticularShow(int showId);  //To get all booked seats for particular show
    String bookingCancellation(String bookingId) throws RazorpayException;                 //To cancel the booked ticket
    void rabbitMqDataProducerForBookingTickets(BookTickets bookTickets);            //To produce data to the booking queue
    void rabbitMqDataProducerForCancellingTickets(BookTickets bookTickets, Refund refund);      //To produce data to the cancellation queue
}
