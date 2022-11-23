package com.example.forfoodiesbyfoodies.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.example.forfoodiesbyfoodies.Entities.User;
import com.example.forfoodiesbyfoodies.MyAdapter;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.RV.RecycleViewInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RvUsers extends AppCompatActivity implements RecycleViewInterface {
    private DatabaseReference dataPlaces;
    private RecyclerView recyclerView;
    private ArrayList<User> userlist;
    private DatabaseReference databaseReference;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_users);

        dataPlaces = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.recycle_users);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        userlist = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(this, userlist,this);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user =dataSnapshot.getValue(User.class);
                    userlist.add(user);
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

    }
}