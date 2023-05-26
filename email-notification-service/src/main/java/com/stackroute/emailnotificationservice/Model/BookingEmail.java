package com.stackroute.emailnotificationservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEmail {
    private String _id;
    private int eventId;
    private int showId;
    private int theatreId;
    private String paymentId;
    private String userEmailId;
    private String eventDate;
    private String eventTime;
    private String eventName;
    private String eventType;
    private String eventDuration;
    private List<String> castAndCrew;
    private List<String> seatNumbers;
    private Double ticketFair;
    private String bookingDate;
    private String bookingTime;
    private String theatreName;
    private String street;
    private String district;
    private String state;
    private String pinCode;
}
