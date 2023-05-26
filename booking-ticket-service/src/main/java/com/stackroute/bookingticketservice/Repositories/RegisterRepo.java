package com.stackroute.bookingticketservice.Repositories;

import com.stackroute.bookingticketservice.Models.RegisterShows;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegisterRepo extends MongoRepository<RegisterShows,Integer> {
    List<RegisterShows> findByTheatreIdAndOrganizerEmailIdAndLocationAndEventDate(int theatreId, String emailId, String location, LocalDate localDate);
    List<RegisterShows> findByOrganizerEmailId(String emailId);                    //To get all history shows by organizer email Id
    List<RegisterShows> findByEventDateGreaterThanEqual(LocalDate eventDate);      //To get all shows based on current date and current time
    List<RegisterShows> findByEventIdAndLocationAndEventDateGreaterThanEqual(int eventId, String location, LocalDate eventDate);  //To get shows by location and eventID
    List<RegisterShows> findByTheatreIdAndEventIdAndEventDateGreaterThanEqual(int theatreId, int eventId, LocalDate eventDate);    //To get shows based on eventId and theaterId
    List<RegisterShows> findByEventDateAndEventId(LocalDate eventDate, int eventId);  //To get shows by eventDate and eventId
    List<RegisterShows> findByLocationAndEventDateAndEventId(String location, LocalDate eventDate, int eventId); //To get shows by ( Location,Date and eventID )
    List<RegisterShows> findByLanguageAndEventIdAndEventDateGreaterThanEqual(String language, int eventId, LocalDate eventDate);   //To get shows by Language and eventId
    List<RegisterShows> findByLocationAndEventDateGreaterThanEqual(String location, LocalDate eventDate);   //To get shows by user location
}
