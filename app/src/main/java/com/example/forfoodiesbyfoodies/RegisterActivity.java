package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password, rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.txtUsernameReg);
        password = findViewById(R.id.txtPasswordReg);
        rePassword = findViewById(R.id.txtRePasswordReg);

        Button regBut = findViewById(R.id.btnRegister);
        regBut.setOnClickListener(new View.OnClickListener() {
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
        }
        else if (pass.equals(rePass)){
            mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
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