package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button sub;
    private int flag =1;

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
        final TextView surname = (TextView) findViewById(R.id.userSurname);
        final TextView email = (TextView) findViewById(R.id.userEmail);
        final TextView password = (TextView) findViewById(R.id.userPassword);
        final TextView role = (TextView) findViewById(R.id.userRole);




        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String fullname = userProfile.firstname;
                    String surn =userProfile.surname;
                    String mail = userProfile.email;
                    String Urole = userProfile.role;
                    String pass = userProfile.password;

                    //assign FB to textboxes
                    username.setText(fullname);
                    surname.setText(surn);
                    email.setText(mail);
                    password.setText(pass);
                    role.setText(Urole);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this , "An error occur...", Toast.LENGTH_LONG);
            }
        });

        Button edit;
        sub =findViewById(R.id.editSub);
        edit = findViewById(R.id.editUser);
        edit.setOnClickListener(new View.OnClickListener() {

            // enable profile edit
            @Override
            public void onClick(View view) {

                if (flag ==1) {
                    username.setEnabled(true);
                    surname.setEnabled(true);
                    password.setEnabled(true);
                    sub.setVisibility(View.VISIBLE);
                    flag =0;
                }
                else{
                    username.setEnabled(false);
                    surname.setEnabled(false);
                    password.setEnabled(false);
                    sub.setVisibility(View.INVISIBLE);
                    flag=1;
                }

                int flag =1;

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            //submit changes
            @Override
            public void onClick(View view) {
                updateUser(username.getText().toString().trim(),
                           surname.getText().toString().trim(),
                           email.getText().toString().trim(),
                           password.getText().toString().trim());

                username.setEnabled(false);
                surname.setEnabled(false);
                password.setEnabled(false);
                sub.setVisibility(View.INVISIBLE);
                flag =1;
            }

        });


    }
    private boolean updateUser(String newname, String newsurname, String newemail, String newpassword ){


        user = FirebaseAuth.getInstance().getCurrentUser();
        UserID= user.getUid();
        DatabaseReference updateReferance = FirebaseDatabase.getInstance().getReference("Users").child(UserID);


        User user = new User(newname, newsurname, newemail,newpassword);
        updateReferance.setValue(user);
        Toast.makeText(getApplicationContext(), "Account "+ newemail+ "updated!",Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id){
        //nahh cannot be current !!!!!!!!!!!!!!
        user = FirebaseAuth.getInstance().getCurrentUser();
        UserID= user.getUid();
        DatabaseReference deleteReferance = FirebaseDatabase.getInstance().getReference("Users").child(UserID);
        deleteReferance.removeValue();
        return true;
    }
}