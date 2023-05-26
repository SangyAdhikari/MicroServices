package com.stackroute.eventdetailsservice.model;

//import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
*/

//import java.time.LocalTime;
import java.time.LocalDate;
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
@Document(collection = "EventsDB")
public class EventDetails {
	
	@Id
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	/*
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;*/
	private long _id;//eventId;
	private String eventName;
	private String eventType;//( Movie, Drama, MusicConcerts, Standup);
	private List<String> releasedLanguages;
	private String eventDuration;
	private List<String> castAndCrew;
	private String eventDescription;
	private String genreOfEvent;
	private double eventRating;//average of userrating
	private List<UserRating> userRating;//5,1
	private byte[] image;
	private String releaseDate;
	
	
	
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public long get_id() {
		return _id;
	}
	public void set_id(long l) {
		this._id = l;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public List<String> getReleasedLanguages() {
		return releasedLanguages;
	}
	public void setReleasedLanguages(List<String> releasedLanguages) {
		this.releasedLanguages = releasedLanguages;
	}
	public String getEventDuration() {
		return eventDuration;
	}
	public void setEventDuration(String eventDuration) {
		this.eventDuration = eventDuration;
	}
	public List<String> getCastAndCrew() {
		return castAndCrew;
	}
	public void setCastAndCrew(List<String> castAndCrew) {
		this.castAndCrew = castAndCrew;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getGenreOfEvent() {
		return genreOfEvent;
	}
	public void setGenreOfEvent(String genreOfEvent) {
		this.genreOfEvent = genreOfEvent;
	}
	public double getEventRating() {
		return eventRating;
	}
	public void setEventRating(double eventRating) {
		this.eventRating = eventRating;
	}
	public List<UserRating> getUserRating() {
		return userRating;
	}
	public void setUserRating(List<UserRating> userRating) {
		this.userRating = userRating;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public EventDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EventDetails(long _id, String eventName, String eventType, List<String> releasedLanguages,
			String eventDuration, List<String> castAndCrew, String eventDescription, String genreOfEvent,
			double eventRating, List<UserRating> userRating,String releaseDate,byte[] image) {
		super();
		this._id = _id;
		this.eventName = eventName;
		this.eventType = eventType;
		this.releasedLanguages = releasedLanguages;
		this.eventDuration = eventDuration;
		this.castAndCrew = castAndCrew;
		this.eventDescription = eventDescription;
		this.genreOfEvent = genreOfEvent;
		this.eventRating = eventRating;
		this.userRating = userRating;
		this.releaseDate = releaseDate;
		this.image = image;
	}
	
	
	
}
