package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.Entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password, rePassword, surname, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.txtUsernameReg);
        password = findViewById(R.id.txtPasswordReg);
        rePassword = findViewById(R.id.txtRePasswordReg);
        surname = findViewById(R.id.txtSurnameReg);
        name = findViewById(R.id.txtFirstReg);



        Button regBut = findViewById(R.id.btnRegister);
        regBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });




}

    private void Register() {

        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String rePass = rePassword.getText().toString().trim();
        String first = name.getText().toString().trim();
        String sur = surname.getText().toString().trim();

        if(first.isEmpty()|| first.length()< 2){
            name.setError("Name cannot be empty!");
        }
        else if(sur.isEmpty()|| sur.length() < 2){
            surname.setError("Surname cannot be empty!");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()||mail.isEmpty()) {
            email.setError("Email address cannot be empty!");
        }
        else if(pass.isEmpty() || pass.length()<6){
            password.setError("Password cannot be empty!");
        }
        else if (pass.equals(rePass)){

            mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        User user = new User(first, sur, mail, pass);

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user);

                        Toast.makeText(RegisterActivity.this, "You are now registered as a user", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();}

    }
 }