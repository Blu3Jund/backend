package com.bluejund.server.repository;

import com.bluejund.server.model.Upload;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository
public interface UploadRepository extends MongoRepository<Upload, String> {
//	Upload findByFile_nameContaining(String file_name);
}
