package com.stackroute.registrationservice.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.stackroute.registrationservice.Entity.RegisterOrganizer;

@Repository
public class OrganiserRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	

	public RegisterOrganizer save(RegisterOrganizer theatreDetails) {
		// TODO Auto-generated method stub
		return mongoTemplate.save(theatreDetails);
	}



	
	}
	
	
	
	


