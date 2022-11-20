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

    Button btnProfile, btnRestaurant, btnCatering, btnStreetinsert, insert;
    DatabaseReference dataPlaces;
    TextView name, local, descr, rate;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnRestaurant = findViewById(R.id.btnRestaurant);
        dataPlaces = FirebaseDatabase.getInstance().getReference();

//        insert= findViewById(R.id.rbuton);
//        name= findViewById(R.id.rname);
//        local = findViewById(R.id.rlocal);
//        descr = findViewById(R.id.rdesc);
//        rate = findViewById(R.id.rrate);


//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String lname= name.getText().toString();
//                String lloacl = local.getText().toString();
//                String ldes = descr.getText().toString();
//                String lrate = rate.getText().toString();
//                String id = dataPlaces.push().getKey();
//
//
//                FoodPlace foodPlace = new FoodPlace(lname,ldes,lloacl,lrate,"vegan friendly");
//                dataPlaces.child("place").child(id).setValue(foodPlace);
//
//            }
//        });


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