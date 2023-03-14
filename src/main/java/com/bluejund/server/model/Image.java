package com.bluejund.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
public class Image {
  @Id
  private String id;
  private String image_name;
  private String image_url;

  public Image(String image_name, String image_url) {
    this.image_name = image_name;
    this.image_url = image_url;
  }

  public String getId() {
    return id;
  }

  public String getImage_name() {
    return image_name;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_name(String image_name) {
    this.image_name = image_name;
  }

  public void setImage_url(String image) {
    this.image_url = image;
  }

  @Override
  public String toString() {
    return "Image [" +
           "id=" + id +
           ", image_name=" + image_name +
           ", image_url=" + image_url +
           "]";
  }
}
