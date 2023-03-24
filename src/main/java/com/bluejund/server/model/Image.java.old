package com.bluejund.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
public class Image {
  @Id
  private String id;
  private String image_name;
  private String image;

  public Image(String image_name, String image) {
    this.image_name = image_name;
    this.image = image;
  }

  public String getId() {
    return id;
  }

  public String getImage_name() {
    return image_name;
  }

  public String getImage() {
    return image;
  }

  public void setImage_name(String image_name) {
    this.image_name = image_name;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "Image [" +
           "id=" + id +
           ", image_name=" + image_name +
           ", image=" + image +
           "]";
  }
}
