package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RvPlaces extends AppCompatActivity implements RecycleViewInterface
{


    DatabaseReference dataPlaces;
    RecyclerView recyclerView;
    ArrayList<FoodPlace> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;
    String place;
    TextView banner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_places);

        Bundle x=getIntent().getExtras();
        if (x!=null) {
            place = x.getString("name");
        } else{
            place="Restaurant";
        }

        dataPlaces = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.recycle_restaurants);
        databaseReference= FirebaseDatabase.getInstance().getReference(place);
        banner = findViewById(R.id.banner);
        banner.setText(place);

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
                Collections.sort(list, new Comparator<FoodPlace>() {
                    @Override
                    public int compare(FoodPlace foodPlace, FoodPlace t1) {
                        return String.valueOf(foodPlace.getName()).compareTo(String.valueOf(t1.getName()));
                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(RvPlaces.this, DetailsView.class);
        FoodPlace x = list.get(position);
        String name = x.getName();
        String desc = x.getDescription();
        String rate = x.getRate();
        String vegan =x.getVegan();
        String location = x.getLocation();
        String path = x.getFilePath();

        intent.putExtra("name", name);
        intent.putExtra("desc", desc);
        intent.putExtra("rate", rate);
        intent.putExtra("vegan", vegan);
        intent.putExtra("location", location);
        intent.putExtra("path", path );
        startActivity(intent);

    }
}
