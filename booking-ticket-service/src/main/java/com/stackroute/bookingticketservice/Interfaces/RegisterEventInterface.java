package com.stackroute.bookingticketservice.Interfaces;

import com.stackroute.bookingticketservice.Models.RegisterShows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface RegisterEventInterface {
    List<RegisterShows> getAllShows();                      //To get all shows from collection
    List<RegisterShows> getShowsByOrganizerEmailId(String emailId); //To get all history shows by organizer email Id
    RegisterShows getShowById(int showId);             // To get show details by ShowId
    Optional<Object> createShow(RegisterShows object);   //To create show by selecting particular event
    List<RegisterShows> getShowsByEventIdAndLocation(int eventId, String location); //To get shows by location and eventID
    List<RegisterShows> getShowsByTheatreIdAndEventId(int theatreId, int eventId);  //To get shows based by theatreId and eventId
    List<RegisterShows> getShowsByEventDateAndEventId(LocalDate eventDate, int eventId);    //To get shows by eventId and date
    List<RegisterShows> getShowsByLocationAndDateAndEventId(String location, LocalDate eventDate, int eventId); //To get shows by ( Location,Date and eventID )
    List<RegisterShows> getShowsByLanguageAndEventId(String language, int eventId);       //Tp get shows by language and eventId
    List<RegisterShows> getShowsByLocation(String location);   //To get shows by location
    Optional<Object> showCreationCriteria(RegisterShows object);             //Method to check show criteria
    boolean showCreationTimeValidation(List<LocalTime> localTimes);                                     //Method to check show timings before creation
}
