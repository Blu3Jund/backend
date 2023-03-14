package com.bluejund.server.controller;

import com.bluejund.server.model.Image;
import com.bluejund.server.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(maxAge = 3600,
  origins = {"https://bluejund.com",
    "https://www.bluejund.com",
    "http://localhost:8081",
    "http://localhost:8080",
    "http://localhost:4200/"},
  allowedHeaders = "Requestor-Type",
  exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/api")
public class ImageController {
  @Autowired
  ImageRepository imageRepository;
//	@GetMapping("/image")
//	public ResponseEntity<Image> getAllImage(@RequestParam(required = false) String file_name) {
//		try {
//			Image image = new ArrayImage();
//
//			if (file_name == null)
//				imageRepository.findAll().forEach(image::add);
//			else
//				imageRepository.findByImage_nameContaining(file_name).forEach(image::add);
//
//			if (image.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(image, HttpStatus.OK);
//		System.o
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

  @GetMapping("/image/{id}")
  public ResponseEntity<Image> getImageById(@PathVariable("id") String id) {
    Optional<Image> imageData = imageRepository.findById(id);
    if (imageData.isPresent()) {
      return new ResponseEntity<>(imageData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/image")
  public ResponseEntity<Image> createImage(@RequestBody Image image) {
    try {
      Image _image = imageRepository.save(new Image(
        image.getImage_name(),
        image.getImage_url()));
      return new ResponseEntity<>(_image, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/image/{id}")
  public ResponseEntity<Image> updateImage(@PathVariable("id") String id,
                                           @RequestBody Image image) {
    Optional<Image> imageData = imageRepository.findById(id);
    if (imageData.isPresent()) {
      Image _image = imageData.get();
      _image.setImage_name(image.getImage_name());
      _image.setImage_url(image.getImage_url());
      return new ResponseEntity<>(imageRepository.save(_image), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/image/{id}")
  public ResponseEntity<HttpStatus> deleteImage(@PathVariable("id") String id) {
    try {
      imageRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/image")
  public ResponseEntity<HttpStatus> deleteAllImage() {
    try {
      imageRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
