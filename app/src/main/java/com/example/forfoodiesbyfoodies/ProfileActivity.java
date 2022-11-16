package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.Entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String UserID;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        UserID= user.getUid();


        final TextView username = (TextView) findViewById(R.id.userName);
        final TextView email = (TextView) findViewById(R.id.userEmail);
        final TextView password = (TextView) findViewById(R.id.userPassword);
        final TextView role = (TextView) findViewById(R.id.userRole);

        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String fullname = userProfile.firstname + " "+ userProfile.surname;
                    String mail = userProfile.email;
                    String Urole = userProfile.role;
                    String pass = userProfile.password;

                    username.setText(fullname);
                    email.setText(mail);
                    password.setText(pass);
                    role.setText(Urole);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this , "An error occure...", Toast.LENGTH_LONG);
            }
        });
    }
}