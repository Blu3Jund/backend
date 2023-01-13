package com.bluejund.server.controller;

import com.bluejund.server.model.Category;
import com.bluejund.server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600, origins = {"http://localhost:8081", "http://localhost:8080"}, allowedHeaders = "Requester-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String name) {
		try {
			List<Category> categories = new ArrayList<Category>();

			if (name == null)
				categoryRepository.findAll().forEach(categories::add);
			else
				categoryRepository.findByNameContaining(name).forEach(categories::add);

			if (categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
		Optional<Category> categoryData = categoryRepository.findById(id);

		if (categoryData.isPresent()) {
			return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/categories")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		System.out.println("hi post categories");

		try {
			Category _category = categoryRepository.save(new Category(category.getParent_id(), category.getName()));
			return new ResponseEntity<>(_category, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, @RequestBody Category category) {
		Optional<Category> categoryData = categoryRepository.findById(id);

		if (categoryData.isPresent()) {
			Category _category = categoryData.get();
			_category.setParent_id(category.getParent_id());
			_category.setName(category.getName());
			return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") String id) {
		try {
			categoryRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/categories")
	public ResponseEntity<HttpStatus> deleteAllCategories() {
		try {
			categoryRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
