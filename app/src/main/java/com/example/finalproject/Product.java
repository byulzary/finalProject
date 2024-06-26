package com.example.finalproject;

public class Product {

    private String name;
    private String description;
    private int id;
    private float price;

    public Product(String name, String description, int id, float price) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
