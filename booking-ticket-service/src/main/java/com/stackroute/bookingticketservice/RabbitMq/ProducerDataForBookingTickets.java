package com.stackroute.bookingticketservice.RabbitMq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/*This class is to set all values given through postman to produce the data, this data will be consumed by email service to send email*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProducerDataForBookingTickets {
    private String _id;
    private int eventId;
    private int showId;
    private int theatreId;
    private String paymentId;
    private String userEmailId;
    private String eventDate;
    private String eventTime;
    private List<String> seatNumbers;
    private String bookingDate;
    private String bookingTime;
    private Double ticketFair;
}
