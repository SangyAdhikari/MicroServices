package com.stackroute.paymentservice.Repositories;

import com.stackroute.paymentservice.Models.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepo extends MongoRepository<OrderDetails,String> {
    List<OrderDetails> findByUserEmailId(String emailId);
}
