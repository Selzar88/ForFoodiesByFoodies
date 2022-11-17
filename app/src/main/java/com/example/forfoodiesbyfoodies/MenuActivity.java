package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        items.add(new RestaurantClass("The Ledbury","Great", "5",R.drawable.the_ledbury));
        items.add(new RestaurantClass("Indian Moment","Great", "4",R.drawable.indian_moment));
        items.add(new RestaurantClass("Tapajax","Great","4", R.drawable.tapajax));
        items.add(new RestaurantClass("Palace Spice","Great","3", R.drawable.palace_spices));
        items.add(new RestaurantClass("The Ledbury","Great", "5",R.drawable.the_ledbury));
        items.add(new RestaurantClass("Indian Moment","Great", "4",R.drawable.indian_moment));
        items.add(new RestaurantClass("Tapajax","Great","4", R.drawable.tapajax));
        items.add(new RestaurantClass("Palace Spice","Great","3", R.drawable.palace_spices));
        items.add(new RestaurantClass("The Ledbury","Great", "5",R.drawable.the_ledbury));
        items.add(new RestaurantClass("Indian Moment","Great", "4",R.drawable.indian_moment));
        items.add(new RestaurantClass("Tapajax","Great","4", R.drawable.tapajax));
        items.add(new RestaurantClass("Palace Spice","Great","3", R.drawable.palace_spices));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));



    }



}