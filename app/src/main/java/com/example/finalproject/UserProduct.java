package com.example.finalproject;

public class UserProduct {


    private String name;
    private String description;
    private int id;
    private float price;
    private int amount;

    public UserProduct(String name, String description, int id, float price, int amount) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
