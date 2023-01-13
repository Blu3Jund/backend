package com.bluejund.server.repository;

import com.bluejund.server.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
	List<Category> findByNameContaining(String name);
}
