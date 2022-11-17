package com.example.majhashetkari.buyOption;

import java.io.Serializable;

public class ProductsModel implements Serializable {
    String description, image_url, name, rating, status, type;
    int price;

    public ProductsModel() {

    }

    public ProductsModel(String description, String image_url, String name, String rating, String status, String type, int price) {
        this.description = description;
        this.image_url = image_url;
        this.name = name;
        this.rating = rating;
        this.status = status;
        this.type = type;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
