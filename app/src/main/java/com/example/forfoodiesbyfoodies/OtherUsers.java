package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtherUsers extends AppCompatActivity {

    private FirebaseUser FBprofile;
    private DatabaseReference reference;
    private Button sub, edit;
    private int flag = 1;
    private RadioButton adminRB, criticRB, userRB;
    private String UserID, role;
    String fullname, surn, mail, Urole, pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_users);


        final TextView username = (TextView) findViewById(R.id.userName);
        final TextView surname = (TextView) findViewById(R.id.userSurname);
        final TextView email = (TextView) findViewById(R.id.userEmail);
        final TextView password = (TextView) findViewById(R.id.userPassword);
        userRB = findViewById(R.id.user);
        adminRB = findViewById(R.id.admin);
        criticRB = findViewById(R.id.critic);


        Bundle userProfile = getIntent().getExtras();
        if (userProfile != null) {
            fullname = userProfile.getString("first");
            surn = userProfile.getString("last");
            mail = userProfile.getString("mail");
            Urole = userProfile.getString("role");
            pass = userProfile.getString("pass");
        } else {
            fullname = "first";
            surn = "last";
            mail = "mail";
            Urole = "role";
            pass = "pass";
        }

        FBprofile = FirebaseAuth.getInstance().getCurrentUser();
        if("admin@fffb.com".equals(FBprofile.getEmail()))
        {
            userRB.setEnabled(true);
            criticRB.setEnabled(true);
            adminRB.setEnabled(true);
            edit.setEnabled(true);
        }


        //assign FB to textboxes
        username.setText(fullname);
        surname.setText(surn);
        email.setText(mail);
        password.setText(pass);
        if (Urole.equals("user")) {
            userRB.setEnabled(true);
            userRB.toggle();
        } else if (Urole.equals("critic")) {
            criticRB.setEnabled(true);
            criticRB.toggle();
        } else if (Urole.equals("admin")) {
            adminRB.setEnabled(true);
            adminRB.toggle();

        }


        sub = findViewById(R.id.editSub);
        edit = findViewById(R.id.editUser);
        edit.setOnClickListener(new View.OnClickListener() {
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

        DropDown();

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


        DatabaseReference updateReferance = FirebaseDatabase.getInstance().getReference("Users").child(mail);

        //new object with user data
        User user = new User(newname, newsurname, newemail, newpassword, role);
        updateReferance.setValue(user);
        Toast.makeText(getApplicationContext(), "Account " + newemail + " updated!", Toast.LENGTH_LONG).show();
        return true;

    }
    public void DropDown() {
        ImageView imageView = findViewById(R.id.settingsDraweProfile);
        registerForContextMenu(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(OtherUsers.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btnProfile:
                                Intent intent1 = new Intent(OtherUsers.this, ProfileActivity.class);
                                startActivity(intent1);
                                return true;
                            case R.id.btnUserList:
                                Intent intent2 = new Intent(OtherUsers.this, RvUsers.class);
                                startActivity(intent2);
                                return true;
                            case R.id.btnAddPlace:
                                Intent intent3 = new Intent(OtherUsers.this, AddPlaceActivity.class);
                                startActivity(intent3);
                                return true;
                            case R.id.btnLogout:
                                Intent intent4 = new Intent(OtherUsers.this, MainActivity.class);
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