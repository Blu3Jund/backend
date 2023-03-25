package com.bluejund.server.controller;

import com.bluejund.server.model.*;
import com.bluejund.server.repository.OrderRepository;
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
public class OrderController {
  @Autowired
  OrderRepository orderRepository;

  @GetMapping("/orders")
  public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String name) {
    try {
      List<Order> orders = new ArrayList<Order>();
      if (name == null)
        orderRepository.findAll().forEach(orders::add);
//      else
//        orderRepository.findByNameContaining(name).forEach(orders::add);
      if (orders.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(orders, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/orders/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable("id") String id) {
    Optional<Order> orderData = orderRepository.findById(id);
    if (orderData.isPresent()) {
      return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/orders")
  // Might be a list after all not sure
  public ResponseEntity<List<Order>> createOrder(@RequestBody Order[] orders) {
    try {
      List<Order> _orders = new ArrayList<>();
      for (Order order : orders) {
        Order _order = orderRepository.save(new Order(order.getEmail(),
          order.getItems()));
        _orders.add(_order);
      }
      if (_orders.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(_orders, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/order")
  public ResponseEntity<Order> createOrder(@RequestBody Order order) {
    try {
      Order _order = orderRepository.save(new Order(order.getEmail(),
        order.getItems()));
      return new ResponseEntity<>(_order, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/orders/{id}")
  public ResponseEntity<Order> updateOrder(@PathVariable("id") String id,
                                           @RequestBody Order order) {
    Optional<Order> orderData = orderRepository.findById(id);
    if (orderData.isPresent()) {
      Order _order = orderData.get();
      _order.setEmail(order.getEmail());
      _order.setItems(order.getItems());
      return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/orders/{id}")
  public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") String id) {
    try {
      orderRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/orders")
  public ResponseEntity<HttpStatus> deleteAllOrders() {
    try {
      orderRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
