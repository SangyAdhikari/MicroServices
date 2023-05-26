package com.stackroute.bookingticketservice.feignClientInterface;

import com.stackroute.bookingticketservice.Services.RegisterEventService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feignClient",url = "localhost:8080/evento")
public interface FeignClientInterface {

    @GetMapping("/events/eventId/{eventId}")          //Calling another microservice ( eventDetails ) to get ReleaseDate and EventDuration
    RegisterEventService getEvent(@PathVariable("eventId") long eventId);
}
