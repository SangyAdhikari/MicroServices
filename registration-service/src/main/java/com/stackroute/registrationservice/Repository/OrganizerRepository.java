package com.stackroute.registrationservice.Repository;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.registrationservice.Entity.RegisterOrganizer;

@Repository
//@EnableMongoRepositories
public interface OrganizerRepository extends MongoRepository<RegisterOrganizer, String> {

	boolean findByEmailId(boolean  b);

	

	/*
	 * RegisterOrganizer findAndModify(Query query, Update update,
	 * FindAndModifyOptions returnNew, Class<RegisterOrganizer> class1);
	 */
	

	

	

}
