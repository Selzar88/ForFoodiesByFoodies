package com.example.forfoodiesbyfoodies.Entities;

import android.media.Image;

public class FoodPlace {

    private String name, describtion, location;
    private boolean vegan;
    private int rate;
//    private Image picture;

    public FoodPlace() {
    }

    public FoodPlace(String name, String describtion, String location, boolean vegan, int rate) {
        this.name = name;
        this.describtion = describtion;
        this.location = location;
        this.vegan = vegan;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
