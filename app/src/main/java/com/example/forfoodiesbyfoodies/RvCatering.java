package com.example.forfoodiesbyfoodies;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class RvCatering extends AppCompatActivity implements RecycleViewInterface {




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_catering);

    }

    @Override
    public void onItemClick(int position) {

    }
}