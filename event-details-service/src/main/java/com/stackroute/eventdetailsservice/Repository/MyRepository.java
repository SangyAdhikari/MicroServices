package com.stackroute.eventdetailsservice.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.eventdetailsservice.model.EventDetails;

@Repository
public interface MyRepository extends MongoRepository<EventDetails, Long> {
	public List<EventDetails> findByEventType(String eventType);
}
