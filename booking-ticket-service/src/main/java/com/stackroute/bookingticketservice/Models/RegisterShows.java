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
@Document
@NoArgsConstructor
@AllArgsConstructor
public class RegisterShows {
    @Id
    private int _id;
    private int eventId;
    private int theatreId;
    private String organizerEmailId;
    private String location;
    private String language;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate eventDate;
    private LocalTime eventTime;
    private List<String> bookedSeats;
}
