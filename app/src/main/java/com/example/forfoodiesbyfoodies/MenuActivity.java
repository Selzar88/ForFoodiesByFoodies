package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference restaurant, street, catering;
    MyAdapter myAdapter;
//    DatabaseReference reference;
    ArrayList<FoodPlace> restaurantList;

    Button profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);




        ImageView reg = findViewById(R.id.settingsDraw);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,AddPlaceActivity.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycleRestaurant);
        restaurant = FirebaseDatabase.getInstance().getReference("Restaurant");
        street = FirebaseDatabase.getInstance().getReference("Street");
        catering = FirebaseDatabase.getInstance().getReference("Catering");



        ArrayList<FoodPlace> items = new ArrayList<FoodPlace>();


        for (int i=0; i < 10; i++){



            FoodPlace o1 = new FoodPlace("name","desc","local",true,2);

            items.add(o1);
        }





//        items.add(new RestaurantClass("The Ledbury","British",R.drawable.the_ledbury,
//                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
//        items.add(new RestaurantClass("Indian Moment","Great",R.drawable.indian_moment,
//                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
//        items.add(new RestaurantClass("Tapajax","Great",R.drawable.tapajax,
//                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
//        items.add(new RestaurantClass("Palace Spice","Great",R.drawable.palace_spices,
//                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
//        items.add(new RestaurantClass("Palace Spice","Great",R.drawable.palace_spices,
//                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));



        profile = findViewById(R.id.btnRestaurants);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,ProfileActivity.class));

            }
        });




    }





}