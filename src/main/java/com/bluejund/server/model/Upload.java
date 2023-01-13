package com.bluejund.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "upload")
public class Upload {
	@Id
	private String id;
	private String file_name;
	//	@JsonFormat(shape = JsonFormat.Shape.)
//	@JsonSubTypes.Type(type = "org.hibernate.type.ImageType")
//	@JsonProperty("file")
	private Object file;
	private String upload_time;

	public Upload() {
		super();
	}

	public Upload(String file_name, Object file, String upload_time) {
		this.file_name = file_name;
		this.file = file;
		this.upload_time = upload_time;
	}

	public String getId() {
		return id;
	}

	public String getFile_name() {
		return file_name;
	}

	public Object getFile() {
		return file;
	}

	public String getUpload_time() {
		return upload_time;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setFile(Object file) {
		this.file = file;
	}

	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}

	@Override
	public String toString() {
		return "Upload [" +
			"id=" + id +
			", file_name=" + file_name +
			", file=" + file +
			", upload_time=" + upload_time +
			"]";
	}
}
