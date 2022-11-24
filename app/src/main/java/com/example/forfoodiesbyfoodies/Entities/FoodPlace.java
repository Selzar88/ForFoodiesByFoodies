package com.example.forfoodiesbyfoodies.Entities;

import android.net.Uri;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FoodPlace {

    String name;
    String location;
    String description;
    String rate;
    String vegan;
    String filePath;







    public FoodPlace(String name, String location, String description, String rate, String vegan, Uri filePath) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.rate = rate;
        this.vegan = vegan;
        this.filePath = String.valueOf(filePath);


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

    public String getVegan() {return  vegan;}

    public String getFilePath() {return  filePath;}


}
