package com.bluejund.server.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Document(collection = "products")
public class Product {
	@Id
	private String id;
	private String name;
	private String description;

	//	@JsonProperty("upload")
	private Upload upload;
	private List<Item> items;
	private List<Category> categories;

	public Product(String name, String description, Upload upload, List<Item> items, List<Category> categories) {
		this.name = name;
		this.description = description;
		this.upload = upload;
		this.items = items;
		this.categories = categories;
	}

//	@SuppressWarnings("unchecked")
//	@JsonProperty("upload")
//	private void unpackNested(Map<String, Object> upload) {
//		this.upload = (String) upload.get("name");
//		Map<String, String> owner = (Map<String, String>) upload.get("owner");
//		this.ownerName = owner.get("name");
//	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Upload getUpload() {
		return upload;
	}

	public List<Item> getItems() {
		return items;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//	@JsonProperty("upload")
	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Product [" +
			"id=" + id +
			", name=" + name +
			", description=" + description +
			", upload=" + upload +
			", items=" + items +
			", categories=" + categories +
			"]";
	}
}
