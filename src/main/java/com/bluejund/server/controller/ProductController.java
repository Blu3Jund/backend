package com.bluejund.server.controller;

import com.bluejund.server.model.ApiResponse;
import com.bluejund.server.model.Item;
import com.bluejund.server.model.Product;
import com.bluejund.server.model.Upload;
import com.bluejund.server.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600, origins = {"https://bluejund.com", "https://www.bluejund.com", "http://localhost:8081", "http://localhost:8080", "http://localhost:4200/"}, allowedHeaders = "Content-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
		System.out.println("hi from prod");
		try {
			List<Product> products = new ArrayList<Product>();

			if (name == null)
				productRepository.findAll().forEach(products::add);
			else
				productRepository.findByNameContaining(name).forEach(products::add);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
		Optional<Product> productData = productRepository.findById(id);

		if (productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<List<Product>> createProduct(@RequestBody Product[] product) {
		System.out.println("hi from post products");
		try {
			List<Product> _product = new ArrayList<>();
			for (int i = 0; i < product.length; i++) {
//				System.out.println();
//				ObjectMapper mapper = new ObjectMapper();

//				Upload upload = mapper.reader()
//					.forType(Upload.class).readValue(product[i].getUpload().getFile().getData());
				_product.add(productRepository.save(new Product(
					product[i].getName(),
					product[i].getDescription(),
//					product[i].getUpload(),
					(new Upload(
						product[i].getName(),
						product[i].getUpload().getFile(),
						product[i].getUpload().getUpload_time())),
//					product[i].getUpload(),
//					upload,
//					(new Upload(
//						product[i].getName(),
////						new Binary(BsonBinarySubType.BINARY, product[i].getUpload().getFile().getData()),
//						product[i].getUpload().getUpload_time())),
////					product[i].getUpload(),
//					null,
					product[i].getItems(),
					product[i].getCategories())));

			}

			return new ResponseEntity<>(_product, HttpStatus.CREATED);
//			Product _product = productRepository.save(new Product(
//				product.getName(),
//				product.getDescription(),
//				product.getUpload(),
//				product.getItems(),
//				product.getCategories()));
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		Optional<Product> productData = productRepository.findById(id);

		if (productData.isPresent()) {
			Product _product = productData.get();
			_product.setName(product.getName());
			_product.setDescription(product.getDescription());
			_product.setUpload(product.getUpload());
			_product.setItems(product.getItems());
			_product.setCategories(product.getCategories());
			return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllProducts() {
		try {
			productRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
