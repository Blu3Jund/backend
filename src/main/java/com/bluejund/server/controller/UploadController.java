package com.bluejund.server.controller;

import com.bluejund.server.model.Upload;
import com.bluejund.server.model.Upload;
import com.bluejund.server.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600, origins = {"http://localhost:8081", "http://localhost:8080"}, allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/api")
public class UploadController {
	@Autowired
	UploadRepository uploadRepository;

//	@GetMapping("/upload")
//	public ResponseEntity<Upload> getAllUpload(@RequestParam(required = false) String file_name) {
//		try {
//			Upload upload = new ArrayUpload();
//
//			if (file_name == null)
//				uploadRepository.findAll().forEach(upload::add);
//			else
//				uploadRepository.findByFile_nameContaining(file_name).forEach(upload::add);
//
//			if (upload.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(upload, HttpStatus.OK);
//		System.o
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping("/upload/{id}")
	public ResponseEntity<Upload> getUploadById(@PathVariable("id") String id) {
		Optional<Upload> uploadData = uploadRepository.findById(id);

		if (uploadData.isPresent()) {
			return new ResponseEntity<>(uploadData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/upload")
	public ResponseEntity<Upload> createUpload(@RequestBody Upload upload) {
		try {
			Upload _upload = uploadRepository.save(new Upload(
				upload.getFile_name(),
				upload.getFile(),
				upload.getUpload_time()));
			return new ResponseEntity<>(_upload, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/upload/{id}")
	public ResponseEntity<Upload> updateUpload(@PathVariable("id") String id, @RequestBody Upload upload) {
		Optional<Upload> uploadData = uploadRepository.findById(id);

		if (uploadData.isPresent()) {
			Upload _upload = uploadData.get();
			_upload.setFile_name(upload.getFile_name());
			_upload.setFile(upload.getFile());
			_upload.setUpload_time(upload.getUpload_time());
			return new ResponseEntity<>(uploadRepository.save(_upload), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/upload/{id}")
	public ResponseEntity<HttpStatus> deleteUpload(@PathVariable("id") String id) {
		try {
			uploadRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/upload")
	public ResponseEntity<HttpStatus> deleteAllUpload() {
		try {
			uploadRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
