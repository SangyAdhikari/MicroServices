package com.stackroute.bookingticketservice.Repositories;

import com.stackroute.bookingticketservice.Models.BookTickets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTicketsRepo extends MongoRepository<BookTickets,String> {
    List<BookTickets> findByUserEmailId(String userEmail);    //To get booking details by user email ID
}
