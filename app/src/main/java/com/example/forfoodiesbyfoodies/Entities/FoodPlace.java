package com.example.forfoodiesbyfoodies.Entities;

import android.media.Image;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FoodPlace {

    String name;
    String location;
    String description;
    String rate;
    String vegan;

    public FoodPlace(String name, String location, String description, String rate, String vegan) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.rate = rate;
        this.vegan = vegan;
    }

    public FoodPlace() {
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getRate() {
        return rate;
    }
}
