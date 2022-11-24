package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.Entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class PasswordRecovery extends AppCompatActivity {

    private Button btnRecover;
    private EditText email;

    private FirebaseAuth authEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_recovery);



        EditText email = findViewById(R.id.emailRecover);
        Button btnRecover = findViewById(R.id.btnRecover);

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailRecover = email.getText().toString();


                if (emailRecover.isEmpty()) {
                    Toast.makeText(PasswordRecovery.this, "Please enter registered user email", Toast.LENGTH_SHORT).show();
                    email.setError("Email required");
                }else{
                    resetPassword(emailRecover);
                }
            }
        });

    }

    private void resetPassword(String email) {
        authEmail = FirebaseAuth.getInstance();
        authEmail.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(PasswordRecovery.this, "Recovery link sent!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PasswordRecovery.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }
}



