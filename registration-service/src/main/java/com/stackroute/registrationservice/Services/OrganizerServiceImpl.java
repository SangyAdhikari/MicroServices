package com.stackroute.registrationservice.Services;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import com.stackroute.registrationservice.Entity.RegisterOrganizer;
import com.stackroute.registrationservice.Entity.TheatreDetails;
import com.stackroute.registrationservice.ExceptionHandling.OrganizerException;

import com.stackroute.registrationservice.RabbitMQ.RabbitMqClass;
import com.stackroute.registrationservice.Repository.OrganiserRepository;
import com.stackroute.registrationservice.Repository.OrganizerRepository;
import com.stackroute.registrationservice.Repository.TheatreDetailsRepository;
import com.stackroute.registrationservice.Repository.UserRepository;

import java.util.Collection;
import java.util.Optional;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.client.result.UpdateResult;

@Service
public class OrganizerServiceImpl implements OrganizerService {
	@Autowired
	private OrganizerRepository OrganizerRepo;
	@Autowired
	private OrganiserRepository Orepo;
	@Autowired
	private SequenceGeneratorService sequenceService;
	@Autowired
	private TheatreDetailsRepository Trepo;
	@Autowired
	private UserRepository UserRepo;

	
	
	@Autowired
	RabbitMqClass rabbit;
	@Override
	public RegisterOrganizer newOrganiser(RegisterOrganizer registerOrganizer) throws OrganizerException {
		if(OrganizerRepo.findById(registerOrganizer.getEmailId()).isEmpty() && UserRepo.findById(registerOrganizer.getEmailId()).isEmpty()){
			   rabbit.organizerData(registerOrganizer);
			   return OrganizerRepo.save(registerOrganizer);
			}
		else
		{
			throw new OrganizerException("Email Already Exist");
		}
	}

	@Override
	public RegisterOrganizer updateOrganizerPassword(String emailId, RegisterOrganizer password) throws OrganizerException {
		 if(OrganizerRepo.findById(emailId).isEmpty())
		 {
			 throw new OrganizerException("No Organizer Found With given EmailId");
		 }
		 else
		 {
			 Optional<RegisterOrganizer> Organizer = (OrganizerRepo.findById(emailId));
		RegisterOrganizer registerOrganizer = Organizer.get();
		rabbit.updateOrganizerData(emailId, password.getPassword());
		registerOrganizer.setPassword(password.getPassword());
		return registerOrganizer;
		}
		
	}

	@Override
	public void saveUpdatePassword(RegisterOrganizer updateOrganizerPassword) {
		OrganizerRepo.save(updateOrganizerPassword);

	}

	@Override
	public RegisterOrganizer updateOrganizerInfo(String emailId, String organizerName, long mobileNo) throws OrganizerException {
		if(OrganizerRepo.findById(emailId).isEmpty())
		{

			throw new OrganizerException("No Organizer Found with given EmailId");
		}
		else
		{
			Optional<RegisterOrganizer> OrganizerInfo = (OrganizerRepo.findById(emailId));
		RegisterOrganizer updatedOrganizer = OrganizerInfo.get();
		updatedOrganizer.setOrganizerName(organizerName).setMobileNo(mobileNo);
		return updatedOrganizer;
		}
		

	}

	@Override
	public void saveUpdatedOrganizerInfo(RegisterOrganizer updateOrganizerInfo) {
		OrganizerRepo.save(updateOrganizerInfo);

	}

	@Override
	public List<RegisterOrganizer> getAllOrganizer() throws OrganizerException {
		 if(OrganizerRepo.findAll().isEmpty())
		 {
			 throw new OrganizerException("No Organizers data Found");
		 }
		 else
		 {
				List<RegisterOrganizer> AllOrganizer = OrganizerRepo.findAll();
				return AllOrganizer;
		 }
		
	}

	@Override
	public RegisterOrganizer getOneOrganizer(String emailId) throws OrganizerException {
		if(OrganizerRepo.findById(emailId).isEmpty())
		{
			throw new OrganizerException("No Organizer Found With Given EmailId");
		}
		else
		{
			Optional<RegisterOrganizer> Organizer = (OrganizerRepo.findById(emailId));
		RegisterOrganizer registerOrganizer = Organizer.get();	
		return registerOrganizer;
		}

	}
	
	  @Override 
	  public RegisterOrganizer updateTheatreDetails(String emailId,TheatreDetails updateTheatreDetails) throws OrganizerException {
	if(OrganizerRepo.findById(emailId).isEmpty())
			  {
		throw new OrganizerException("No Record Found");
			  }
	else
	{
		 Optional<RegisterOrganizer> details =(OrganizerRepo.findById(emailId));
	  RegisterOrganizer theatreDetails = details.get();
	  updateTheatreDetails.setTheatreId(sequenceService.generateSequence(TheatreDetails.SEQUENCE_NAME));
	  List<TheatreDetails> list=new ArrayList<>();
	  list=theatreDetails.getList();
	  if(list==null)
	  {
		  List<TheatreDetails> list1=new ArrayList<>();
	  list1.add(updateTheatreDetails);
	  theatreDetails.setList(list1);
	  }else {
		  list.add(updateTheatreDetails);
		  theatreDetails.setList(list);
	  }
	  Trepo.save(updateTheatreDetails);
	  return Orepo.save(theatreDetails);
	  }
	  }
	 

	@Override
	public TheatreDetails getTheatreDetails(int id) throws OrganizerException {
		if(Trepo.findById(id).isEmpty())
		{
			throw new OrganizerException("No Record Found With The Given Id");
		}
		else
		{
			Optional<TheatreDetails> theatreDetails = (Trepo.findById(id));
		TheatreDetails details = theatreDetails.get();	
		return details;
		}
		
	}

	@Override
	public TheatreDetails updateTheatreDetailsById( String emailId,int id,TheatreDetails details) throws OrganizerException {
		if(Trepo.findById(id).isEmpty())
		{
			throw new OrganizerException("No Record Found");
		}
		else
		{
			Optional<TheatreDetails> tids = Trepo.findById(id);
		TheatreDetails theatreDetails = tids.get();
		theatreDetails.setCity(details.getCity()).setdistrict(details.getdistrict()).setLandMark(details.getLandMark()).setNumberOfSeats(details.getNumberOfSeats())
		.setPinCode(details.getPinCode()).setState(details.getState()).setStreet(details.getStreet()).setTheatreName(details.getTheatreName());
		Trepo.save(theatreDetails);
		Optional<RegisterOrganizer> findById = OrganizerRepo.findById(emailId);
		RegisterOrganizer registerOrganizer = findById.get();
		List<TheatreDetails> list = registerOrganizer.getList();
		//System.out.println(list);
		  for(int i=0;i<list.size();i++) 
		  { 
			  System.out.println(list.get(i).getTheatreId());
		  
			  if(list.get(i).getTheatreId()==id) {
				  list.set(i, details);
			  
		  } 
		  }
		  registerOrganizer.setList(list);
		  System.out.println(list);
		  OrganizerRepo.save(registerOrganizer);
		return theatreDetails;
	}
	}
	
	}
