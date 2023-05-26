package com.stackroute.eventdetailsservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.eventdetailsservice.model.EventDetails;
import com.stackroute.eventdetailsservice.model.UserRating;
import com.stackroute.eventdetailsservice.service.EventService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/evento/events")
public class MyController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/image")
	public byte[] test(@RequestParam(value = "image") MultipartFile file)throws IOException {
		//evOb.eventRegis(ob);
		//EventDetails ob = new ObjectMapper().readValue(inputString,EventDetails.class);
		return file.getBytes();
	}
	
	//EventDetails Service 
	
	//show all registered events
	@GetMapping("/getevents")
	public List<EventDetails> showEvents(){
		return this.eventService.getEvents();
	}
	
	//show event by event id
	@GetMapping("/eventId/{eventId}")
	public EventDetails showEvent(@PathVariable String eventId) {
		return this.eventService.showEvent(Long.parseLong(eventId));
	}

	//register event
	@PostMapping("/register-event")
	public EventDetails toRegisterEvent(@RequestParam(value = "event") String inputString,@RequestParam(value = "image") MultipartFile file)throws IOException {
		//evOb.eventRegis(ob);
		//System.out.println(inputString);
		EventDetails ob = new ObjectMapper().readValue(inputString,EventDetails.class);
		//System.out.println(ob);
		return this.eventService.eventRegis(ob,file);
	}
	
	//update event
	@PutMapping("/update/update-event")
	public EventDetails toUpdateEvent(@RequestBody EventDetails ob) {
		return this.eventService.toUpdateEvent(ob);
	}

	@GetMapping("/eventType/{eventType}")
	public List<EventDetails> getEventByEventType(@PathVariable String eventType) {
		return this.eventService.getEventByEventType(eventType);
		//return this.eventService.showEventType("Movie");
	}
	
	@PutMapping("/event/rating/{id}")
	public double toUpdateRating(@RequestBody UserRating userOb,@PathVariable long id)throws IOException{
		return this.eventService.toUpdateRating(userOb,id);
	}
}
