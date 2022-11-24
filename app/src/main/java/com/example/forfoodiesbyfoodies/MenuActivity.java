package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.example.forfoodiesbyfoodies.User.RvUsers;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    DatabaseReference dataPlaces;
    RecyclerView recyclerView;
    ArrayList<FoodPlace> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;
    String place= "place";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ImageView imageView = findViewById(R.id.settingsDrawer);
        registerForContextMenu(imageView);

        ImageView btnRestaurants = findViewById(R.id.btnRestaurants);
        btnRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RvPlaces.class);
                intent.putExtra("name", "Restaurant");
                startActivity(intent);
            }

        });


        TextView txtRestaurants = findViewById(R.id.txtRestaurantMenu);
        txtRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RvPlaces.class);
                intent.putExtra("name", "Restaurant");
                startActivity(intent);
            }
        });

        ImageView btnStreetFood = findViewById(R.id.btnStreetFood);
        btnStreetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RvPlaces.class);
                intent.putExtra("name", "StreetFood");
                startActivity(intent);;
            }
        });

        TextView txtStreetFood = findViewById(R.id.txtStreetFoodMenu);
        txtStreetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RvPlaces.class);
                intent.putExtra("name", "StreetFood");
                startActivity(intent);
            }
        });

        TextView txtCatering = findViewById(R.id.txtCateringMenu);
        txtCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RvPlaces.class);
                intent.putExtra("name", "Catering");
                startActivity(intent);
            }
        });
        ImageView btnCatering = findViewById(R.id.btnCatering);
        btnCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RvPlaces.class);
                intent.putExtra("name", "Catering");
                startActivity(intent);
            }
        });


        DropDownMenu();
}





    public void DropDownMenu(){
        ImageView imageView = findViewById(R.id.settingsDrawer);
        registerForContextMenu(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MenuActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btnProfile:
                                Intent intent1 = new Intent(MenuActivity.this, ProfileActivity.class);
                                startActivity(intent1);
                                return true;
                            case R.id.btnUserList:
                                Intent intent2 = new Intent(MenuActivity.this, RvUsers.class);
                                startActivity(intent2);
                                return true;
                            case R.id.btnAddPlace:
                                Intent intent3 = new Intent(MenuActivity.this, AddPlaceActivity.class);
                                startActivity(intent3);
                                return true;
                            case R.id.btnLogout:
                                Intent intent4 = new Intent(MenuActivity.this, MainActivity.class);
                                startActivity(intent4);
                                return true;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}