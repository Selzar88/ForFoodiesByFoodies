package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.sql.Array;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ArrayList<RestaurantClass> restaurantsView = new ArrayList<>();
    int[] restaurantPic = {R.drawable.baner, R.drawable.google, R.drawable.facebook};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        setRestaurants();
        All_restaurant adapter = new All_restaurant(this.restaurantsView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setRestaurants(){
        String[] restaurant = getResources().getStringArray(R.array.restaurants);
        String[] rate= getResources().getStringArray(R.array.rate);
        String[] review= getResources().getStringArray(R.array.review);

        for (int i =0; i< restaurant.length; i++){
            restaurantsView.add(new RestaurantClass(restaurant[i],rate[i],review[i],restaurantPic[i]));
        }
    }
}