package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CommentUpdate extends AppCompatActivity {


    private TextView  mail, placeName, comText;
    private String place, email, text;
    private Button update, delete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_update);

        mail= findViewById(R.id.commentMail);
        placeName = findViewById(R.id.commentPlace);
        comText = findViewById(R.id.commentBox);
        update = findViewById(R.id.commentUpdate);
        delete = findViewById(R.id.commentDelete);



        Bundle comment = getIntent().getExtras();
        if (comment!=null) {
           place = comment.getString("place");
           email = comment.getString("mail");
           text = comment.getString("text");
        } else{
            place = "user";
            email = "email";
            text = "text";
        }

        mail.setText(email);
        placeName.setText(place);
        comText.setText(text);
    }


//       private boolean deleteUser(String id) {
//        FBprofile = FirebaseAuth.getInstance().getCurrentUser();
//        UserID = FBprofile.getUid();
//        DatabaseReference deleteReferance = FirebaseDatabase.getInstance().getReference("Users").child(UserID);
//        deleteReferance.removeValue();
//        return true;
//    }
}