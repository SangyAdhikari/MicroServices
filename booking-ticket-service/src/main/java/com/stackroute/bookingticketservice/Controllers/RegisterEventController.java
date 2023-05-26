package com.stackroute.bookingticketservice.Controllers;

import com.stackroute.bookingticketservice.Models.RegisterShows;
import com.stackroute.bookingticketservice.Services.RegisterEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/evento/booking/shows")
public class RegisterEventController {

    @Autowired
    RegisterEventService service;           //Bean for service class to call method implemented in service layer

    @GetMapping("/getshows")
    @Operation(summary = "Get All Shows",description = "To get all registered shows from collection")
    @ApiResponse(responseCode = "202",description = "Will return all registered shows")
    public ResponseEntity<List<RegisterShows>> getShows(){
        return new ResponseEntity<>(service.getAllShows(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/emailId/{emailId}")
    @Operation(summary = "Organizer history shows", description = "To get all history shows by organizer email ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode= "202",description = "Will return all shows created by particular organizer"),
            @ApiResponse(responseCode= "400",description = "No shows have been created on this account yet..")
    })
    public ResponseEntity<List<RegisterShows>> getShowsByOrganizerEmailID(@PathVariable("emailId") String emailId){
        return new ResponseEntity<>(service.getShowsByOrganizerEmailId(emailId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/showId/{showId}")
    @Operation(summary = "Get show by ID",description = "To get particular show details by show Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Will fetch particular show by its ID"),
            @ApiResponse(responseCode = "400", description = "No such ID found..")
    })
    public ResponseEntity<RegisterShows> getShowById(@PathVariable("showId") int showId){
        return new ResponseEntity<>(service.getShowById(showId),HttpStatus.ACCEPTED);
    }

    @PostMapping("/register-show")
    @Operation(summary = "Create Show",description = "To create new shows by selecting particular event")
    @ApiResponse(responseCode = "201",description = "Show has been created")
    public ResponseEntity<Optional<Object>> addEvent(@RequestBody RegisterShows registerShows) {
        registerShows.setBookedSeats(new ArrayList<String>());
        return new ResponseEntity<>(service.createShow(registerShows),HttpStatus.CREATED);
    }

    @GetMapping("/location/{location}/eventId/{eventId}")
    @Operation(summary = "Filter by Location and Event ID", description = "To get all shows by filtering location and event Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Will return list of shows by filtering location and event ID"),
            @ApiResponse(responseCode = "400", description = "Oops..No shows available for the given input..")
    })
    public ResponseEntity<List<RegisterShows>> getShowsByEventIdAndLocation(@PathVariable("location") String location, @PathVariable("eventId") int eventId){
        return new ResponseEntity<>(service.getShowsByEventIdAndLocation(eventId,location),HttpStatus.ACCEPTED);
    }

    @GetMapping("/theatreId/{theatreId}/eventId/{eventId}")
    @Operation(summary = "Filter by Theatre ID and Event ID",description = "To get all shows by filtering theatre Id and event Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Will return list of shows by filtering theatre ID and event ID"),
            @ApiResponse(responseCode = "400", description = "Oops..No shows available for the given input..")
    })
    public ResponseEntity<List<RegisterShows>> getShowsByEventIdAndTheaterId(@PathVariable("theatreId") int theatreId, @PathVariable("eventId") int eventId) {
        return new ResponseEntity<>(service.getShowsByTheatreIdAndEventId(theatreId,eventId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/date/{eventDate}/eventId/{eventId}")
    @Operation(summary = "Filter by Event Date and Event ID",description = "To get all shows by filtering showdate and event Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Will return list of shows by filtering show date and event ID"),
            @ApiResponse(responseCode = "400", description = "Oops..No shows available for the given input..")
    })
    public ResponseEntity<List<RegisterShows>> getShowsByEventIdAndDate(@PathVariable("eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate, @PathVariable("eventId") int eventId){
        return new ResponseEntity<>(service.getShowsByEventDateAndEventId(eventDate,eventId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/location/{location}/date/{eventDate}/eventId/{eventId}")
    @Operation(summary = "Filter by Location, show Date and Event ID",description = "To get all shows by filtering Location, showdate and event Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Will return list of shows by filtering location, show date and event ID"),
            @ApiResponse(responseCode = "400", description = "Oops..No shows available for the given input..")
    })
    public ResponseEntity<List<RegisterShows>> getShowsByLocationAndDateAndEventId(@PathVariable("location") String location, @PathVariable("eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate, @PathVariable("eventId") int eventId){
        return new ResponseEntity<>(service.getShowsByLocationAndDateAndEventId(location,eventDate,eventId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/language/{language}/eventId/{eventId}")
    @Operation(summary = "Filter by Language and Event ID",description = "To get all shows by filtering event Id and language")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Will return list of shows by filtering language and event ID"),
            @ApiResponse(responseCode = "400", description = "Oops..No shows available for the given input..")
    })
    public ResponseEntity<List<RegisterShows>> getShowsByLanguageAndEventId(@PathVariable("language") String language, @PathVariable("eventId") int eventId){
        return new ResponseEntity<>(service.getShowsByLanguageAndEventId(language,eventId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/location/{location}")
    @Operation(summary = "Filter by user location",description = "To get all shows by filtering particular user location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Will return list of shows by filtering location"),
            @ApiResponse(responseCode = "400", description = "Oops..No shows available for the given input..")
    })
    public ResponseEntity<List<RegisterShows>> getShowsByLocation(@PathVariable("location") String location){
        return new ResponseEntity<>(service.getShowsByLocation(location),HttpStatus.ACCEPTED);
    }
}
