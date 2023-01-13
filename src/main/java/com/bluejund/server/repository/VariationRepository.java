package com.bluejund.server.repository;

import com.bluejund.server.model.Category;
import com.bluejund.server.model.Variation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface VariationRepository extends MongoRepository<Variation, String> {
//	List<Variation> findByVariation_nameContaining(String variation_name);
}
