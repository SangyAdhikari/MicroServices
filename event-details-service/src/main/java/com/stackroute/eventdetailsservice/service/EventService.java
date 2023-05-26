package com.stackroute.eventdetailsservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stackroute.eventdetailsservice.model.EventDetails;
import com.stackroute.eventdetailsservice.model.UserRating;

public interface EventService {
	public EventDetails eventRegis(EventDetails ob,MultipartFile file)throws IOException;
	public List<EventDetails> getEvents();
	public EventDetails toUpdateEvent(EventDetails ob);
	public EventDetails showEvent(long id);
	public List<EventDetails> getEventByEventType(String eventType);
	public double toUpdateRating(UserRating userOb,long id)throws IOException;
}
