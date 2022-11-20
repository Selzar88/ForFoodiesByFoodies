package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {

    Button btnProfile, btnRestaurant;
    DatabaseReference dataPlaces;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnRestaurant = findViewById(R.id.btnRestaurant);
        dataPlaces = FirebaseDatabase.getInstance().getReference();

        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place= "place";
                Intent i = new Intent(getApplicationContext(),FoodPlacesList.class);
                i.putExtra("PLACE",place);
                startActivity(i);
            }
        });



        ImageView reg = findViewById(R.id.settingsDraw);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getApplicationContext(),AddPlaceActivity.class);
                add.putExtra("PLACE","place");
                startActivity(add);
            }
        });

        btnProfile = findViewById(R.id.btnprofile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,ProfileActivity.class));

            }
        });




    }





}