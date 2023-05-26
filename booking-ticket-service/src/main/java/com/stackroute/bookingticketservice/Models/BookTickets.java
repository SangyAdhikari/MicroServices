package com.stackroute.bookingticketservice.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class BookTickets {
    @Id
    private String _id;
    private int eventId;
    private int showId;
    private int theatreId;
    private String paymentId;
    private String userEmailId;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate eventDate;
    private LocalTime eventTime;
    private List<String> seatNumbers;
    private String bookingDate;
    private String bookingTime;
    private Double ticketFair;
}
