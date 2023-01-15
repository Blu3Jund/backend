package com.bluejund.server.controller;

import com.bluejund.server.model.Item;
import com.bluejund.server.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600, origins = {"https://bluejund.com", "http://localhost:8081", "http://localhost:8080", "http://localhost:4200/"}, allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/api")
public class ItemController {
	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String sku) {
		try {
			List<Item> items = new ArrayList<Item>();

			if (sku == null)
				itemRepository.findAll().forEach(items::add);
			else
				itemRepository.findBySkuContaining(sku).forEach(items::add);

			if (items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") String id) {
		Optional<Item> itemData = itemRepository.findById(id);

		if (itemData.isPresent()) {
			return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/items")
	public ResponseEntity<Item> createItem(@RequestBody Item item) {
		try {
			Item _item = itemRepository.save(new Item(
				item.getSku(),
				item.getQuantity_in_stock(),
				item.getPrice(),
				item.getUpload(),
				item.getVariations()));
			return new ResponseEntity<>(_item, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") String id, @RequestBody Item item) {
		Optional<Item> itemData = itemRepository.findById(id);

		if (itemData.isPresent()) {
			Item _item = itemData.get();
			_item.setSku(item.getSku());
			_item.setQuantity_in_stock(item.getQuantity_in_stock());
			_item.setPrice(item.getPrice());
			_item.setUpload(item.getUpload());
			_item.setVariations(item.getVariations());
			return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") String id) {
		try {
			itemRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/items")
	public ResponseEntity<HttpStatus> deleteAllItems() {
		try {
			itemRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
