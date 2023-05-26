package com.stackroute.bookingticketservice.Controllers;

import com.razorpay.RazorpayException;
import com.stackroute.bookingticketservice.Models.BookTickets;
import com.stackroute.bookingticketservice.Services.BookTicketsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/evento/booking/book")
public class BookTicketsController {

    @Autowired
    BookTicketsService service;

    @PostMapping("/show")
    @Operation(summary = "Book Show", description = "To book ticket for particular show")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Ticket has been booked"),
            @ApiResponse(responseCode = "400", description = "Seat has been already picked by another user..")
    })
    public ResponseEntity<Optional<Object>> bookShows(@RequestBody BookTickets bookTickets){
        return new ResponseEntity<>(service.bookTicketForShow(bookTickets), HttpStatus.CREATED);
    }

    @GetMapping("/show-details/bookingId/{bookingId}")
    @Operation(summary = "Filter booking details by booking ID", description = "To get booking details by booking ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Booking details will be fetched by booking ID"),
            @ApiResponse(responseCode = "400",description = "Invalid booking ID found..")
    })
    public ResponseEntity<BookTickets> getBookingDetailsById(@PathVariable("bookingId") String bookingId){
        return new ResponseEntity<>(service.getShowDetailsById(bookingId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/show-details/emailId/{userEmailId}")
    @Operation(summary = "Filter booking details by registered email ID", description = "To get booking details by user registered email Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Booking details will be fetched by email ID"),
            @ApiResponse(responseCode = "400",description = "You haven't booked any tickets from your account..")
    })
    public ResponseEntity<List<BookTickets>> getBookingDetailsByEmail(@PathVariable("userEmailId") String userEmail){
        return new ResponseEntity<>(service.getShowDetailsByEmail(userEmail),HttpStatus.ACCEPTED);
    }

    @GetMapping("/seat-mapping/showId/{showId}")
    @Operation(summary = "Seat-Mapping API call", description = "To get all booked seats for particular show")
    @ApiResponse(responseCode = "202",description = "Booked seats will be returned for the particular show")
    public ResponseEntity<List<String>> getAllBookedSeatsByShowId(@PathVariable("showId") int showId){
        return new ResponseEntity<>(service.getAllBookedSeatsForParticularShow(showId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/cancellation/{bookingId}")
    public ResponseEntity<String> bookingCancellation(@PathVariable("bookingId") String bookingId) throws RazorpayException {
        return new ResponseEntity<>(service.bookingCancellation(bookingId), HttpStatus.ACCEPTED);
    }
}
