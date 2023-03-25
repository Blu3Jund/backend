package com.bluejund.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Order {
  @Id
  private String id;
  private String email;
  private List<Item> items;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public Order(String email, List<Item> items) {
    this.email = email;
    this.items = items;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Order [" +
           "id=" + id +
           ", email=" + email +
           ", items=" + items +
           "]";
  }
}
