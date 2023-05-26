package com.stackroute.bookingticketservice.Services;

import com.stackroute.bookingticketservice.Exception.CustomizedException;
import com.stackroute.bookingticketservice.Interfaces.RegisterEventInterface;
import com.stackroute.bookingticketservice.Models.RegisterShows;
import com.stackroute.bookingticketservice.Repositories.RegisterRepo;
import com.stackroute.bookingticketservice.feignClientInterface.FeignClientInterface;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterEventService implements RegisterEventInterface {

    @Getter
    String eventDuration;   //Using this variable to get duration of particular event ( Used feign client since event details service running different microservice

    @Getter
    String releaseDate;     //Using this variable to get release date of particular event to avoid creating shows before release date

    @Autowired
    RegisterRepo repository;

    @Autowired
    FeignClientInterface feignClientInterface;              //Feign client interface object

    @Autowired RegisterEventInterface interfaceObj;         //Normal Interface object

    @Override
    public List<RegisterShows> getAllShows() {
        return repository.findByEventDateGreaterThanEqual(LocalDate.now());
    }

    @Override
    public List<RegisterShows> getShowsByOrganizerEmailId(String emailId) {
        List<RegisterShows> lists=repository.findByOrganizerEmailId(emailId);
        if(lists.isEmpty())
            throw new CustomizedException("No shows have been created on this account yet..");
        return lists;
    }

    @Override
    public RegisterShows getShowById(int showId) {
        if(repository.findById(showId).isPresent())
            return repository.findById(showId).get();
        else
            throw new CustomizedException("No such ID found..");
    }

    @Override
    public Optional<Object> createShow(RegisterShows object) {
        if(repository.count()==0)
            object.set_id(1);
        else {
            List<Integer> ids = repository.findAll().stream().map(obj -> obj.get_id()).collect(Collectors.toList()); //Fetching all existing ids from collections
            int maxValue = ids.stream().max(Integer::compareTo).get();                                               //Sorting all ids by ascending order
            object.set_id(maxValue + 1);                                                                             //Increment maxValue by 1 and this will be set as current id
        }
        return Optional.ofNullable(interfaceObj.showCreationCriteria(object));                                        //Calling an interface method to check show criteria before creating show
    }

    @Override
    public List<RegisterShows> getShowsByEventIdAndLocation(int eventId, String location) {
        List<RegisterShows> list=repository.findByEventIdAndLocationAndEventDateGreaterThanEqual(eventId, location, LocalDate.now());
        if(list.isEmpty())
            throw new CustomizedException("Oops..No shows available for the given input..");
        return list;
    }

    @Override
    public List<RegisterShows> getShowsByTheatreIdAndEventId(int theatreId, int eventId) {
        List<RegisterShows> list=repository.findByTheatreIdAndEventIdAndEventDateGreaterThanEqual(theatreId,eventId,LocalDate.now());
        if(list.isEmpty())
            throw new CustomizedException("Oops..No shows available for the given input..");
        return list;
    }

    @Override
    public List<RegisterShows> getShowsByEventDateAndEventId(LocalDate eventDate, int eventId) {
        List<RegisterShows> list=repository.findByEventDateAndEventId(eventDate, eventId);
        if(list.isEmpty())
            throw new CustomizedException("Oops..No shows available for the given input..");
        return list;
    }

    @Override
    public List<RegisterShows> getShowsByLocationAndDateAndEventId(String location, LocalDate eventDate, int eventId) {
        List<RegisterShows> list=repository.findByLocationAndEventDateAndEventId(location, eventDate, eventId);
        if(list.isEmpty())
            throw new CustomizedException("Oops..No shows available for the given input..");
        return list;
    }

    @Override
    public List<RegisterShows> getShowsByLanguageAndEventId(String language, int eventId) {
        List<RegisterShows> list=repository.findByLanguageAndEventIdAndEventDateGreaterThanEqual(language, eventId,LocalDate.now());
        if(list.isEmpty())
            throw new CustomizedException("Oops..No shows available for the given input..");
        return list;
    }

    @Override
    public List<RegisterShows> getShowsByLocation(String location) {
        List<RegisterShows> list=repository.findByLocationAndEventDateGreaterThanEqual(location, LocalDate.now());
        if(list.isEmpty())
            throw new CustomizedException("Oops..No shows available for the given input..");
        return list;
    }

    @Override
    public Optional<Object> showCreationCriteria(RegisterShows object){
        eventDuration=feignClientInterface.getEvent(object.getEventId()).getEventDuration();                         //Getting duration from eventDetails service
        releaseDate=feignClientInterface.getEvent(object.getEventId()).getReleaseDate();                             //Getting release date from eventDetails service
        if(!object.getEventDate().isBefore(LocalDate.parse(releaseDate,DateTimeFormatter.ISO_DATE))) {               //Condition to check whether given date is releasing date of movie
            List<RegisterShows> tempObj = repository.findByTheatreIdAndOrganizerEmailIdAndLocationAndEventDate(object.getTheatreId(), object.getOrganizerEmailId(), object.getLocation(), object.getEventDate());
            if (!tempObj.isEmpty()) {
                List<LocalTime> showTimings = tempObj.stream().map(time -> time.getEventTime()).collect(Collectors.toList());
                showTimings.add(object.getEventTime());
                List<LocalTime> sortTimings = showTimings.stream().sorted().collect(Collectors.toList());
                if(interfaceObj.showCreationTimeValidation(sortTimings)){
                    if (object.getEventTime().isAfter(LocalTime.of(19, 30)))
                        throw new CustomizedException("Shows are allowed to create before 19:30..");
                    else {
                        if(LocalDate.now().isEqual(object.getEventDate()) && object.getEventTime().isBefore(LocalTime.now()))       //Criteria to check show timings is valid or not
                            throw new CustomizedException("Invalid show Time injected..");
                        else
                            return Optional.ofNullable(repository.save(object));
                    }
                }
                else
                    throw new CustomizedException("Invalid show creation.. Show timing is getting overlapped..");
            }
            else {
                if(LocalDate.now().isEqual(object.getEventDate()) && object.getEventTime().isBefore(LocalTime.now()))       //Criteria to check show timings is valid or not
                    throw new CustomizedException("Invalid show Time injected..");
                else
                    return Optional.ofNullable(repository.save(object));
            }
        }
        else
            throw new CustomizedException("Show can't be created before the show release date..");
    }

    @Override
    public boolean showCreationTimeValidation(List<LocalTime> localTimes) {                             //Show timings validator
        boolean flag=true;
        LocalTime durationGapBetweenShows = LocalTime.parse(eventDuration, DateTimeFormatter.ISO_TIME).plus(Duration.ofMinutes(35));   //Duration Plus 35 will be considered as a break timing for each shows
        for(int i=0;i<localTimes.size()-1;i++){
            LocalTime timeDiff=localTimes.get(i+1).minusHours(localTimes.get(i).getHour()).minusMinutes(localTimes.get(i).getMinute());
            if(timeDiff.isBefore(durationGapBetweenShows)) {
                flag=false;
                break;
            }
        }
        return flag;
    }
}
