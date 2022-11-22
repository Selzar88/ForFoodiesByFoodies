package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //encapsulacja zeby nie dalo sie dostac z zewnatrz klasy do danychr
    // !!!ROB KOMENTY PO ANGIELSKU NIE BEDZIEMY MUSIELI ICH ZMIENIAC DO SNIPOW!!!
    private EditText username, password;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    Button btnNoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.txtUsernameLogin);
        password = (EditText) findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        btnNoLogin = findViewById(R.id.btnNoLogin);





        Button noLogin = findViewById(R.id.btnNoLogin);
        noLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MenuActivity
                        .class));
            }
        });



//jakas zmina
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(user.isEmpty()||pass.isEmpty())
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //if statmant after the credentials are checked
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Welcome "+ user, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,MenuActivity.class));
                            }else{
                                Toast.makeText(MainActivity.this, "Sorry user "+ user+ " is not active", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        TextView reg = findViewById(R.id.txtRegister);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });



    }
}

