package com.bluejund.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "items")
public class Item {
  @Id
  private String id;
  private String sku;
  private Integer quantity_in_stock;
  private Long price;
  private Image image;
  private List<Variation> variations;

  public Item(String sku,
              Integer quantity_in_stock,
              Long price,
              Image image,
              List<Variation> variations) {
    this.sku = sku;
    this.quantity_in_stock = quantity_in_stock;
    this.price = price;
    this.image = image;
    this.variations = variations;
  }

  public String getId() {
    return id;
  }

  public String getSku() {
    return sku;
  }

  public Integer getQuantity_in_stock() {
    return quantity_in_stock;
  }

  public Long getPrice() {
    return price;
  }

  public Image getImage() {
    return image;
  }

  public List<Variation> getVariations() {
    return variations;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setQuantity_in_stock(Integer quantity_in_stock) {
    this.quantity_in_stock = quantity_in_stock;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  //	@JsonProperty("image")
  public void setImage(Image image) {
    this.image = image;
  }

  public void setVariations(List<Variation> variations) {
    this.variations = variations;
  }

  @Override
  public String toString() {
    return "Item [" +
           "id=" + id +
           ", sku=" + sku +
           ", quantity_in_stock=" + quantity_in_stock +
           ", price=" + price +
           ", image=" + image +
           ", variations=" + variations +
           "]";
  }
}
