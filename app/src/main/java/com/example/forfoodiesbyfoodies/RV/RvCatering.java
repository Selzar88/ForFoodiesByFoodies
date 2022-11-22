package com.example.forfoodiesbyfoodies.RV;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.DetailsView;
import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.example.forfoodiesbyfoodies.MyAdapter;
import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RvCatering extends AppCompatActivity implements RecycleViewInterface {




    DatabaseReference dataPlaces;
    RecyclerView recyclerView;
    ArrayList<FoodPlace> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;
    String place= "Catering";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_catering);


        dataPlaces = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.recycle_catering);
        databaseReference= FirebaseDatabase.getInstance().getReference(place);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, list,this);
        recyclerView.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    FoodPlace foodPlace =dataSnapshot.getValue(FoodPlace.class);
                    list.add(foodPlace);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(RvCatering.this, DetailsView.class);
        startActivity(intent);

    }
}