package com.bluejund.server.repository;

import com.bluejund.server.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface OrderRepository extends MongoRepository<Order, String> {
//  List<Order> findByNameContaining(String email);
}
