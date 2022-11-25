package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.Entities.Comment;
import com.example.forfoodiesbyfoodies.User.CommentAdapter;
import com.example.forfoodiesbyfoodies.User.RvUsers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsView extends AppCompatActivity implements RecycleViewInterface {

    private ImageView picture;
    private DatabaseReference dataPlaces;
    private RecyclerView recyclerView;
    private ArrayList<Comment> commentlist;
    private DatabaseReference databaseReference;
    private CommentAdapter adapter;
    private TextView commenttext, Placename, Placelocation, Placedesc,PlaceVegan,PlaceRate;
    private String name, desc, location, rate, veg, path;

    private Button bookking, Btncomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);


        Placename = findViewById(R.id.DetailsPlaceName);
        Placelocation = findViewById(R.id.DetailsPlaceLocation);
        Placedesc= findViewById(R.id.DetailsPlaceDescription);
        PlaceVegan= findViewById(R.id.DetailsVegan);
        PlaceRate = findViewById(R.id.DetailsRate);
        picture = findViewById(R.id.DetailsView);

        Bundle data = getIntent().getExtras();
        if (data!=null) {
             name= data.getString("name");
             desc= data.getString("desc");
             location= data.getString("location");
             rate= data.getString("rate");
             veg= data.getString("vegan");
             path =data.getString("path");
        } else{
            name= "name";
            desc= "desc";
            location= "location";
            rate ="rate";
            veg = "Vegan Friendly";
        }

        Placename.setText(name);
        Placedesc.setText(desc);
        Placelocation.setText(location);
        PlaceVegan.setText(veg);
        PlaceRate.setText(rate);
        Uri uri = Uri.parse(path);
        Picasso.get().load(uri).into(picture);


        commenttext = findViewById(R.id.editTextTextMultiLine);
        Btncomment = findViewById(R.id.AddCommentBtn);
        bookking= findViewById(R.id.make_booking);
        dataPlaces = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.recycleViewDetails);
        databaseReference= FirebaseDatabase.getInstance().getReference("Comments").child(name);

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
                Comment comment = new Comment(commention, commenttext.getText().toString(), name);

                databaseReference.push().setValue(comment);
                Toast.makeText(DetailsView.this, "New comment added", Toast.LENGTH_SHORT).show();

            }
        });

        DropDownMenu();
    }


    @Override
    public void onItemClick(int position) {

    }

    public void DropDownMenu(){
        ImageView imageView = findViewById(R.id.settingsDrawerDetails);
        registerForContextMenu(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(DetailsView.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btnProfile:
                                Intent intent1 = new Intent(DetailsView.this, ProfileActivity.class);
                                startActivity(intent1);
                                return true;
                            case R.id.btnMenu:
                                Intent intent5 = new Intent(DetailsView.this, MenuActivity.class);
                                startActivity(intent5);
                                return true;
                            case R.id.btnUserList:
                                Intent intent2 = new Intent(DetailsView.this, RvUsers.class);
                                startActivity(intent2);
                                return true;
                            case R.id.btnAddPlace:
                                Intent intent3 = new Intent(DetailsView.this, AddPlaceActivity.class);
                                startActivity(intent3);
                                return true;
                            case R.id.btnLogout:
                                Intent intent4 = new Intent(DetailsView.this, MainActivity.class);
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