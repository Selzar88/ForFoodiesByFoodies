package com.example.forfoodiesbyfoodies;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.Entities.User;
import com.example.forfoodiesbyfoodies.User.RvUsers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser FBprofile;
    private DatabaseReference reference;
    private Button sub;
    private int flag = 1;
    private RadioButton adminRB, criticRB, userRB;
    private String UserID, role;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        FBprofile = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        UserID = FBprofile.getUid();




        final TextView username = (TextView) findViewById(R.id.userName);
        final TextView surname = (TextView) findViewById(R.id.userSurname);
        final TextView email = (TextView) findViewById(R.id.userEmail);
        final TextView password = (TextView) findViewById(R.id.userPassword);
        userRB = findViewById(R.id.th20);
        adminRB = findViewById(R.id.th18);
        criticRB = findViewById(R.id.th19);



       DropDownMenu();


        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);


                    if (userProfile != null) {
                        String fullname = userProfile.firstname;
                        String surn = userProfile.surname;
                        String mail = userProfile.email;
                        String Urole = userProfile.role;
                        String pass = userProfile.password;


                        //assign FB to textboxes
                        username.setText(fullname);
                        surname.setText(surn);
                        email.setText(mail);
                        password.setText(pass);
                        if(Urole.equals("user")) {
                            userRB.setEnabled(true);
                            userRB.toggle();
                        }
                        else if(Urole.equals("critic")){
                            criticRB.setEnabled(true);
                            criticRB.toggle();
                        }
                        else if(Urole.equals("admin")){
                            adminRB.setEnabled(true);
                            adminRB.toggle();

                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "An error occur...", Toast.LENGTH_LONG);
            }
        });

        Button edit;
        sub = findViewById(R.id.editSub);
        edit = findViewById(R.id.editUser);
        edit.setOnClickListener(new View.OnClickListener() {
            // enable profile edit
            @Override
            public void onClick(View view) {


                    if (flag == 1) {
                        username.setEnabled(true);
                        surname.setEnabled(true);
                        password.setEnabled(true);
                        sub.setVisibility(View.VISIBLE);
//
                        flag = 0;
                    } else {
                        username.setEnabled(false);
                        surname.setEnabled(false);
                        password.setEnabled(false);
                        sub.setVisibility(View.INVISIBLE);
                        flag = 1;
                    }
                    int flag = 1;
                }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            //submit changes
            @Override
            public void onClick(View view) {
                if(userRB.isChecked()){
                    role="user";
                }else if(criticRB.isChecked()){
                    role="critic";
                }else if(adminRB.isChecked()){
                    role="admin";
                }

                updateUser(username.getText().toString().trim(),
                        surname.getText().toString().trim(),
                        email.getText().toString().trim(),
                        password.getText().toString().trim(),role);
                        username.setEnabled(false);
                        surname.setEnabled(false);
                        password.setEnabled(false);
                        sub.setVisibility(View.INVISIBLE);
                        flag = 1;
            }
        });
    }

    private boolean updateUser(String newname, String newsurname, String newemail, String newpassword, String role) {


        FBprofile = FirebaseAuth.getInstance().getCurrentUser();
        UserID = FBprofile.getUid();
        DatabaseReference updateReferance = FirebaseDatabase.getInstance().getReference("Users").child(UserID);

        //new object with user data
        User user = new User(newname, newsurname, newemail, newpassword, role);
        updateReferance.setValue(user);
        FBprofile.updatePassword(newpassword);
        Toast.makeText(getApplicationContext(), "Account " + newemail + " updated!", Toast.LENGTH_LONG).show();
        return true;
    }

    public void DropDownMenu() {
        ImageView imageView = findViewById(R.id.settingsDraweProfile);
        registerForContextMenu(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btnProfile:
                                Intent intent1 = new Intent(ProfileActivity.this, ProfileActivity.class);
                                startActivity(intent1);
                                return true;
                            case R.id.btnUserList:
                                Intent intent2 = new Intent(ProfileActivity.this, RvUsers.class);
                                startActivity(intent2);
                                return true;
                            case R.id.btnAddPlace:
                                Intent intent3 = new Intent(ProfileActivity.this, AddPlaceActivity.class);
                                startActivity(intent3);
                                return true;
                            case R.id.btnLogout:
                                Intent intent4 = new Intent(ProfileActivity.this, MainActivity.class);
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