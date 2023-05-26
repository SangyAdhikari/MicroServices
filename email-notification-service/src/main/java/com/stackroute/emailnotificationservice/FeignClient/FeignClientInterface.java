package com.stackroute.emailnotificationservice.FeignClient;

import com.stackroute.emailnotificationservice.Model.BookingEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feignClient",url = "localhost:8080/evento")
public interface FeignClientInterface {

    @GetMapping("/events/eventId/{eventId}")          //Calling another microservice ( eventDetails ) to get ReleaseDate and EventDuration
    BookingEmail getEvent(@PathVariable("eventId") long eventId);

    @GetMapping("/register/get/theatreDetails/{theatreId}")          //Calling another micrservice ( RegisterService ) to get theatre details by theatre ID
    BookingEmail getTheatre(@PathVariable("theatreId") long theatreId);
}
