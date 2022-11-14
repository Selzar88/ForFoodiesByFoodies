package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password, rePassword;
    private Button register;
    private TextView regMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.login_field);
        password = findViewById(R.id.pass_field);
        rePassword = findViewById(R.id.repass_field);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });


    }
    private void Register() {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String rePass = rePassword.getText().toString().trim();

        if(user.isEmpty()) {
            email.setError("Email address cannot be empty!");
        }else if(pass.isEmpty()){
            password.setError("Password cannot be empty!");
        }else if(rePass!= pass){
            rePassword.setError("Passwords not match");
            password.setError("Passwords not matched");
        }else{
            mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(Register.this, "You are now registered as a user", Toast.LENGTH_SHORT).show();
                          //startActivity(new Intent(Register));
                      } else {
                          Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                      }
                }
            });
        }
        // validation fo existing user requied!

    }

}