package com.bluejund.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "variations")
public class Variation {
	@Id
	private String id;
	private String variation_name;

	private List<String> variation_option;

	public Variation(String variation_name, List<String> variation_option) {
		this.variation_name = variation_name;
		this.variation_option = variation_option;
	}

	public String getId() {
		return id;
	}

	public String getVariation_name() {
		return variation_name;
	}

	public List<String> getVariation_option() {
		return variation_option;
	}

	public void setVariation_name(String variation_name) {
		this.variation_name = variation_name;
	}

	public void setVariation_option(List<String> variation_option) {
		this.variation_option = variation_option;
	}

	@Override
	public String toString() {
		return "Variation [" +
			"id=" + id +
			", variation_name=" + variation_name +
			", variation_option=" + variation_option +
			"]";
	}
}
