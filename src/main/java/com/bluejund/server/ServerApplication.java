package com.bluejund.server;

import com.bluejund.server.model.Product;
import com.bluejund.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ServerApplication implements CommandLineRunner {

//	@Autowired
//	ProductRepository productRepository;
//	@Autowired
//	CategoryRepository categoryRepository;
//
//	@Autowired
//	ItemRepository itemRepository;
//
//	@Autowired
//	UploadRepository uploadRepository;
//
//	@Autowired
//	VariationRepository variationRepository;

	//Create mock items
//	void createVariationItems() {
//		System.out.println("Data creation started...");
//		productRepository.save(new Product(""))
//	}


	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (String arg : args) {
			System.out.println(arg);
		}
	}
}
