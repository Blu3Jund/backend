package com.bluejund.server.repository;

import com.bluejund.server.model.Category;
import com.bluejund.server.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
	List<Item> findBySkuContaining(String sku);
}
