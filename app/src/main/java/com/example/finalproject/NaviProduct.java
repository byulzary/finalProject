package com.example.finalproject;

public class NaviProduct {
    String name;
    int loc_x, loc_y, loc_z, id, amount;

    public NaviProduct(String name, int loc_x, int loc_y, int loc_z, int id, int amount) {
        this.name = name;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
        this.loc_z = loc_z;
        this.id = id;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoc_x() {
        return loc_x;
    }

    public void setLoc_x(int loc_x) {
        this.loc_x = loc_x;
    }

    public int getLoc_y() {
        return loc_y;
    }

    public void setLoc_y(int loc_y) {
        this.loc_y = loc_y;
    }

    public int getLoc_z() {
        return loc_z;
    }

    public void setLoc_z(int loc_z) {
        this.loc_z = loc_z;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
