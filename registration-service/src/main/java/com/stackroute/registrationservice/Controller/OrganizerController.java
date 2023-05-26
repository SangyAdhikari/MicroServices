package com.stackroute.registrationservice.Controller;


import com.stackroute.registrationservice.Entity.RegisterOrganizer;
import com.stackroute.registrationservice.Entity.TheatreDetails;
import com.stackroute.registrationservice.ExceptionHandling.OrganizerException;
import com.stackroute.registrationservice.Services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento/register")
//@CrossOrigin(origins = "*")
public class OrganizerController {
	@Autowired
	private OrganizerService service;

	@PostMapping("/register-organizer")
	public ResponseEntity<RegisterOrganizer> newOrganizer(@RequestBody RegisterOrganizer registerOrganizer) throws OrganizerException

	{
		
			RegisterOrganizer newOrganiser = service.newOrganiser(registerOrganizer);
			return new ResponseEntity<>(newOrganiser,HttpStatus.CREATED);
	}

	@PutMapping("/update/update-organizer-password/{emailId}")
	public ResponseEntity<RegisterOrganizer> updateOrganizerPassword(@PathVariable("emailId")String emailId,@RequestBody RegisterOrganizer password) throws OrganizerException

	{
		
		RegisterOrganizer updateOrganizerPassword = service.updateOrganizerPassword(emailId,password);
		service.saveUpdatePassword(updateOrganizerPassword);
		return new ResponseEntity<>(updateOrganizerPassword ,HttpStatus.CREATED);
	}

	@PutMapping("/update/update-organizer/{emailId}/{organizerName}/{mobileNo}")
	public ResponseEntity<RegisterOrganizer> updateOrganizerInfo(@PathVariable("emailId")String emailId,@PathVariable("organizerName")String organizerName,@PathVariable("mobileNo")long mobileNo) throws OrganizerException

	{
		
		RegisterOrganizer updateOrganizerInfo = service.updateOrganizerInfo(emailId,organizerName,mobileNo);
		service.saveUpdatedOrganizerInfo(updateOrganizerInfo);
		return new ResponseEntity<>(updateOrganizerInfo ,HttpStatus.CREATED);
	}

	@GetMapping("/get/organizers")
	public ResponseEntity<List<RegisterOrganizer>>getAllOrganizer() throws OrganizerException

	{
		List<RegisterOrganizer> allOrganizer = service.getAllOrganizer();
		return new ResponseEntity<>(allOrganizer,HttpStatus.CREATED);
	}

	@GetMapping("/get/organizer/{emailId}")
	public ResponseEntity<RegisterOrganizer> getOneOrganizer(@PathVariable("emailId")String emailId) throws OrganizerException

	{
		RegisterOrganizer oneOrganizer = service.getOneOrganizer(emailId);
		return new ResponseEntity<>(oneOrganizer,HttpStatus.CREATED);
	}
	
	  @PutMapping("/update/theatreDetails/{emailId}")
	  public RegisterOrganizer updateTheatreDetails(@RequestBody TheatreDetails updateTheatreDetails,@PathVariable("emailId")String emailId) throws OrganizerException {
	  RegisterOrganizer updatedDetails =service.updateTheatreDetails(emailId,updateTheatreDetails);
	  return updatedDetails;
	  }


	  @GetMapping("/get/theatreDetails/{id}")
	  public ResponseEntity<TheatreDetails> getTheatreDetailsById(@PathVariable("id")int id) throws OrganizerException { 

		  TheatreDetails theatreDetails = service.getTheatreDetails(id); 
		  return new ResponseEntity<>(theatreDetails,HttpStatus.CREATED); 
		  }	
	  @PutMapping("/update-organizer/theatreDetails/{id}/{emailId}")
	  public TheatreDetails updateTheatreDetailsById(@PathVariable("emailId")String emailId,@PathVariable("id")int id,@RequestBody TheatreDetails details) throws OrganizerException
	  {
		   TheatreDetails detailsById = service.updateTheatreDetailsById( emailId,id,details);
		 return detailsById;
	  }
}
