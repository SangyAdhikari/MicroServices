package com.stackroute.registrationservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.registrationservice.Entity.RegisterUser;

@Repository
public interface UserRepository extends MongoRepository<RegisterUser, String> {

	


 

	

}
