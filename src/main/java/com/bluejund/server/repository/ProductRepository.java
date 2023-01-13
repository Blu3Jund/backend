package com.bluejund.server.repository;

import com.bluejund.server.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	List<Product> findByNameContaining(String name);
}
