package com.example.forfoodiesbyfoodies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MakeBooking extends AppCompatActivity {
    Button book;
    Ca

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_booking);

        book = findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data ="2022-11-25%2022%";
                Uri uri = Uri.parse("https://www.opentable.com/s/?covers=2&dateTime" + data + "3A00&metroId=72&regionIds=5316&pinnedRids%5B0%5D=87967&enableSimpleCuisines=true&includeTicketedAvailability=true&pageType=0 ");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }
}