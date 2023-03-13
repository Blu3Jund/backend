package com.bluejund.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
public class Product {
  @Id
  private String id;
  private String name;
  private String description;
  private List<String> images;
  private List<Item> items;
  private List<Category> categories;

  public Product(String name,
                 String description,
                 List<String> images,
                 List<Item> items,
                 List<Category> categories) {
    this.name = name;
    this.description = description;
    this.images = images;
    this.items = items;
    this.categories = categories;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getImages() {
    return images;
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

  public void setImages(List<String> images) {
    this.images = images;
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
           ", images=" + images +
           ", items=" + items +
           ", categories=" + categories +
           "]";
  }
}
