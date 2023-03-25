package com.bluejund.server.controller;

import com.bluejund.server.model.*;
import com.bluejund.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600, origins = {"https://bluejund.com", "https://www.bluejund.com",
  "http://localhost:8081", "http://localhost:8080",
  "http://localhost:4200/"}, allowedHeaders = "Content-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/api")
public class ProductController {
  @Autowired
  ProductRepository productRepository;

  @GetMapping("/products")
  public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
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
  // Might be a list after all not sure
  public ResponseEntity<List<Product>> createProduct(@RequestBody Product[] products) {
    try {
      List<Product> _products = new ArrayList<>();
      for (Product product : products) {
        Product _product = productRepository.save(new Product(product.getName(),
          product.getDescription(),
          product.getImage(),
          product.getItems(),
          product.getCategories()));
        _products.add(_product);
      }
      if (_products.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(_products, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable("id") String id,
                                               @RequestBody Product product) {
    Optional<Product> productData = productRepository.findById(id);
    if (productData.isPresent()) {
      Product _product = productData.get();
      _product.setName(product.getName());
      _product.setDescription(product.getDescription());
      _product.setImage(product.getImage());
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
