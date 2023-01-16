package com.bluejund.server.controller;

import com.bluejund.server.model.Variation;
import com.bluejund.server.repository.VariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600, origins = {"https://bluejund.com", "https://www.bluejund.com", "http://localhost:8081", "http://localhost:8080", "http://localhost:4200/"}, allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header	")
@RestController
@RequestMapping("/api")
public class VariationController {
	@Autowired
	VariationRepository variationRepository;

//	@GetMapping("/variations")
//	public ResponseEntity<List<Variation>> getAllVariations(@RequestParam(required = false) String variation_name) {
//		try {
//			List<Variation> variations = new ArrayList<Variation>();
//
//			if (variation_name == null)
//				variationRepository.findAll().forEach(variations::add);
//			else
//				variationRepository.findByVariation_nameContaining(variation_name).forEach(variations::add);
//
//			if (variations.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(variations, HttpStatus.OK);
//		System.out.println(e);

//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping("/variations/{id}")
	public ResponseEntity<Variation> getVariationById(@PathVariable("id") String id) {
		Optional<Variation> variationData = variationRepository.findById(id);

		if (variationData.isPresent()) {
			return new ResponseEntity<>(variationData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/variations")
	public ResponseEntity<Variation> createVariation(@RequestBody Variation variation) {
		try {
			Variation _variation = variationRepository.save(new Variation(
				variation.getVariation_name(), variation.getVariation_option()));
			return new ResponseEntity<>(_variation, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/variations/{id}")
	public ResponseEntity<Variation> updateVariation(@PathVariable("id") String id, @RequestBody Variation variation) {
		Optional<Variation> variationData = variationRepository.findById(id);

		if (variationData.isPresent()) {
			Variation _variation = variationData.get();
			_variation.setVariation_name(variation.getVariation_name());
			_variation.setVariation_option(variation.getVariation_option());
			return new ResponseEntity<>(variationRepository.save(_variation), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/variations/{id}")
	public ResponseEntity<HttpStatus> deleteVariation(@PathVariable("id") String id) {
		try {
			variationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/variations")
	public ResponseEntity<HttpStatus> deleteAllVariations() {
		try {
			variationRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
