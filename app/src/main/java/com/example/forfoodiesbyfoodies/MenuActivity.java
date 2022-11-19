package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<RestaurantClass> restaurantList;

    Button profil;


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
        database = FirebaseDatabase.getInstance().getReference("RestaurantList");

        List<RestaurantClass> items = new ArrayList<RestaurantClass>();
        items.add(new RestaurantClass("The Ledbury","British",R.drawable.the_ledbury,
                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
        items.add(new RestaurantClass("Indian Moment","Great",R.drawable.indian_moment,
                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
        items.add(new RestaurantClass("Tapajax","Great",R.drawable.tapajax,
                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
        items.add(new RestaurantClass("Palace Spice","Great",R.drawable.palace_spices,
                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));
        items.add(new RestaurantClass("Palace Spice","Great",R.drawable.palace_spices,
                R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_star_half_24,R.drawable.ic_baseline_read_more_24));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));


        profil = findViewById(R.id.btnRestaurants);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,ProfileActivity.class));

            }
        });

    }





}