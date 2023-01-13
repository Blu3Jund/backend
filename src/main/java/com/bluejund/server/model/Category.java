package com.bluejund.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {
	@Id
	private String id;
	private String parent_id;
	private String name;

	public Category(String parent_id, String name) {
		this.parent_id = parent_id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [" +
			"id=" + id +
			", parent_id=" + parent_id +
			", name=" + name +
			"]";
	}
}


