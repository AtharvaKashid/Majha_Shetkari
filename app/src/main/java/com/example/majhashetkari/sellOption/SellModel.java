package com.example.majhashetkari.sellOption;

public class SellModel {
    public String name;
    public String url;
    public Double price;
    public String description;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public SellModel(){

    }

    public SellModel(String name, String url, Double price, String description) {
        this.name = name;
        this.url= url;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}

