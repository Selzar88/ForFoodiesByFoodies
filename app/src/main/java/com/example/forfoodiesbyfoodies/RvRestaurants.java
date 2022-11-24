package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RvRestaurants extends AppCompatActivity implements RecycleViewInterface
{


    DatabaseReference dataPlaces;
    RecyclerView recyclerView;
    ArrayList<FoodPlace> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;
    String place;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_restaurants);

        Bundle x=getIntent().getExtras();
        if (x!=null) {
            place = x.getString("name");
        } else{
            place="Restaurant";
        }

        dataPlaces = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.recycle_restaurants);
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
        Intent intent = new Intent(RvRestaurants.this, DetailsView.class);
        FoodPlace x = list.get(position);
        String name = x.getName();
        String desc = x.getDescription();
        String rate = x.getRate();
        String vegan =x.getVegan();
        String location = x.getLocation();

        intent.putExtra("name", name);
        intent.putExtra("desc", desc);
        intent.putExtra("rate", rate);
        intent.putExtra("vegan", vegan);
        intent.putExtra("location", location);
        startActivity(intent);

    }
}
