package com.stackroute.eventdetailsservice.service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/*
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;*/
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.Query;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.inject.internal.util.Objects;
import com.stackroute.eventdetailsservice.Repository.MyRepository;
import com.stackroute.eventdetailsservice.model.EventDetails;
import com.stackroute.eventdetailsservice.model.UserRating;
/*
import com.stackroute.eventdetailsservice.model.UserRating;
*/

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	MyRepository repo;
		
	@Override
	public EventDetails toUpdateEvent(EventDetails ob) {
		// TODO Auto-generated method stub
		
		Optional<EventDetails> findById = repo.findById(ob.get_id());
		EventDetails eventDetails = findById.get();
		eventDetails.setCastAndCrew(ob.getCastAndCrew());
		eventDetails.setEventDescription(ob.getEventDescription());
		eventDetails.setEventDuration(ob.getEventDuration());
		eventDetails.setEventName(ob.getEventName());
		eventDetails.setEventRating(ob.getEventRating());
		eventDetails.setEventType(ob.getEventType());
		eventDetails.setGenreOfEvent(ob.getGenreOfEvent());
		eventDetails.setReleasedLanguages(ob.getReleasedLanguages());
		eventDetails.setReleaseDate(ob.getReleaseDate());
		eventDetails.setUserRating(ob.getUserRating());
		
		return eventDetails;
	}


	@Override
	public EventDetails showEvent(long id) {
		// TODO Auto-generated method stub
		Optional<EventDetails> findById = repo.findById(id);
		EventDetails eventDetails = findById.get();
		
		return eventDetails;
		//return repo.findById(id).get();
	}

	@Override
	public EventDetails eventRegis(EventDetails ob,MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		
		if(repo.count() == 0) {
			ob.set_id(1);
		}
		else {
			List<Long> ids = repo.findAll().stream().map(object -> object.get_id()).collect(Collectors.toList()); 
			Long maxValue = ids.stream().max(Long::compareTo).get(); 
			ob.set_id(maxValue + 1);  
		}
		
		
		/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
		LocalDate localDate = LocalDate.parse(ob.getReleateDate());
		ob.setReleateDate(localDate);
		*/
		
		ob.setImage(file.getBytes());
		return repo.save(ob);
		//return null;
	}


	@Override
	public List<EventDetails> getEvents() {
		// TODO Auto-generated method stub
		return repo.findAll();
		//return null;
	}


	@Override
	public List<EventDetails> getEventByEventType(String eventType) {
		// TODO Auto-generated method stub
		/*
		Optional<EventDetails> findById = repo.findAll("eventType");
		EventDetails eventDetails = findById.get();
		*/
		List<EventDetails> l1 = new ArrayList<>();
		
		l1 = repo.findByEventType(eventType);
		
		/*List<EventDetails> findAll = repo.findAll();
		for(EventDetails e:findAll) {
			if(e.getEventType() == ob)
				l1.add(e);
		}*/
		return l1;
		
		
		
		//return null;
	}


	@Override
	public double toUpdateRating(UserRating userOb,long id) throws IOException{
		// TODO Auto-generated method stub
		Optional<EventDetails> findById = repo.findById(id);
		EventDetails eventDetails = findById.get();
		List<UserRating> l1 = eventDetails.getUserRating();

		if(l1 == null) {
			l1 = new ArrayList<>();
			eventDetails.setUserRating(l1);
		}
		int flag = 0;
		double rating = 0;
		//if(l1.size() > 0) {
		for(UserRating e : l1) {
			if(e.getEmailId().equals(userOb.getEmailId())){
				flag =1;
				break;
			}
			rating = rating + e.getUserRating();
		}
		//}
		if(flag == 0) {
			eventDetails.getUserRating().add(userOb);
			int size = l1.size();
			rating = rating + userOb.getUserRating();
			rating = rating / size;
			eventDetails.setEventRating(rating);
			eventDetails.setUserRating(l1);
			repo.save(eventDetails);
			return rating;
		}

		return 0;
	}

}
