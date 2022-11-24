package com.example.forfoodiesbyfoodies.Entities;


import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FoodPlace {

    String name;
    String location;
    String description;
    String rate;
    String vegan;
    String url;



    public FoodPlace(String name, String location, String description, String rate, String vegan, String url) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.rate = rate;
        this.vegan = vegan;
        this.url = url;


    }

    public FoodPlace(EditText name, EditText localisation, EditText description, CheckBox vegan, String s) {
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

    public String getImageUrl() {return  url;}



}
