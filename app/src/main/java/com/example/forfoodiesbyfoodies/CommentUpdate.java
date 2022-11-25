package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CommentUpdate extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_update);

        Bundle comment = getIntent().getExtras();
//        if (comment!=null) {
//            name= data.getString("name");
//            desc= data.getString("desc");
//            location= data.getString("location");
//            rate= data.getString("rate");
//            veg= data.getString("vegan");
//            path =data.getString("path");
//        } else{
//            name= "name";
//            desc= "desc";
//            location= "location";
//            rate ="rate";
//            veg = "Vegan Friendly";
//        }
    }
}