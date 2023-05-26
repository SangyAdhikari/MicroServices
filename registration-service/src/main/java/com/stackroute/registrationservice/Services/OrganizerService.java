package com.stackroute.registrationservice.Services;

import java.util.List;

import com.stackroute.registrationservice.Entity.RegisterOrganizer;
import com.stackroute.registrationservice.Entity.TheatreDetails;
import com.stackroute.registrationservice.ExceptionHandling.OrganizerException;

public interface OrganizerService {
	public RegisterOrganizer newOrganiser(RegisterOrganizer registerOrganizer) throws OrganizerException;
	public RegisterOrganizer updateOrganizerPassword(String emailId, RegisterOrganizer password) throws OrganizerException;
	public void saveUpdatePassword(RegisterOrganizer updateOrganizerPassword);
	public RegisterOrganizer updateOrganizerInfo(String emailId, String organizerName, long mobileNo) throws OrganizerException;
	public void saveUpdatedOrganizerInfo(RegisterOrganizer updateOrganizerInfo);
	public List<RegisterOrganizer> getAllOrganizer() throws OrganizerException;
	public RegisterOrganizer getOneOrganizer(String emailId) throws OrganizerException;
	
	public RegisterOrganizer updateTheatreDetails(String emailId, TheatreDetails updateTheatreDetails) throws OrganizerException;
	public TheatreDetails getTheatreDetails(int id) throws OrganizerException;
	//public void saveDetailsInDb(TheatreDetails updateTheatreDetails, String emailId);
	//public TheatreDetails registerTheatre(TheatreDetails theatreDetails);
	public TheatreDetails updateTheatreDetailsById( String emailId,int id,TheatreDetails details) throws OrganizerException;
}
