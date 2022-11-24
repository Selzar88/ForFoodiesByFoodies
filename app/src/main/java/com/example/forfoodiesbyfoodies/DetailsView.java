package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.Entities.Comment;
import com.example.forfoodiesbyfoodies.Entities.User;
import com.example.forfoodiesbyfoodies.RV.RecycleViewInterface;
import com.example.forfoodiesbyfoodies.User.CommentAdapter;
import com.example.forfoodiesbyfoodies.User.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailsView extends AppCompatActivity implements RecycleViewInterface {

    private DatabaseReference dataPlaces;
    private RecyclerView recyclerView;
    private ArrayList<Comment> commentlist;
    private DatabaseReference databaseReference;
    private CommentAdapter adapter;
    private TextView commenttext;

    private Button bookking, Btncomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);

        commenttext = findViewById(R.id.editTextTextMultiLine);
        Btncomment = findViewById(R.id.AddCommentBtn);
        bookking= findViewById(R.id.make_booking);
        dataPlaces = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.recycleViewDetails);
        databaseReference= FirebaseDatabase.getInstance().getReference("Comments");

        commentlist = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentAdapter(this, commentlist,this);
        recyclerView.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentlist.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Comment comment =dataSnapshot.getValue(Comment.class);
                    commentlist.add(comment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        bookking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsView.this,MakeBooking.class));
            }
        });

        Btncomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commention = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                Comment comment = new Comment(commention, commenttext.getText().toString());

                FirebaseDatabase.getInstance().getReference("Comments").push().setValue(comment);
                Toast.makeText(DetailsView.this, "New comment added", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onItemClick(int position) {

    }

}