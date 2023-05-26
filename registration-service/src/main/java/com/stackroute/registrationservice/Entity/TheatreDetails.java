package com.stackroute.registrationservice.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Theatredetails")
public class TheatreDetails {
	
	@Transient
	public static final String SEQUENCE_NAME="user_sequence";
	@Id
	private int theatreId;
    private String theatreName;
    private Integer numberOfSeats;
    private String street;
    private String pinCode;
    private String landMark;
    private String district;
    private String state;
    
    private String city;
	public String getCity() {
		return city;
		
	}
	public TheatreDetails() {
		super();
		
	}
	
	public TheatreDetails(int theatreId, String theatreName, Integer numberOfSeats, String street, String pinCode,
			String landMark, String district, String state, String city) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.numberOfSeats = numberOfSeats;
		this.street = street;
		this.pinCode = pinCode;
		this.landMark = landMark;
		this.district = district;
		this.state = state;
		this.city = city;
	}
	public TheatreDetails setCity(String city) {
		this.city = city;
		return this;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public TheatreDetails setTheatreName(String theatreName) {
		this.theatreName = theatreName;
		return this;
	}
	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}
	public TheatreDetails setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
		return this;
	}
	public String getStreet() {
		return street;
	}
	public TheatreDetails setStreet(String street) {
		this.street = street;
		return this;
	}
	public String getPinCode() {
		return pinCode;
	}
	public TheatreDetails setPinCode(String pinCode) {
		this.pinCode = pinCode;
		return this;
	}
	public String getLandMark() {
		return landMark;
	}
	public TheatreDetails setLandMark(String landMark) {
		this.landMark = landMark;
		return this;
	}
	public String getdistrict() {
		return district;
	}
	public TheatreDetails setdistrict(String district) {
		this.district = district;
		return this;
	}
	public String getState() {
		return state;
	}
	public TheatreDetails setState(String state) {
		this.state = state;
		return this;
	}
	@Override
	public String toString() {
		return "TheatreDetails [theatreId=" + theatreId + ", theatreName=" + theatreName + ", numberOfSeats="
				+ numberOfSeats + ", street=" + street + ", pinCode=" + pinCode + ", landMark=" + landMark
				+ ", district=" + district + ", state=" + state + ", city=" + city + "]";
	}
	
	
	
	
	
    


}
