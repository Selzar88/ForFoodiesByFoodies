package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class MakeBooking extends AppCompatActivity {
    Button book;
    CalendarView calendarView;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_booking);

        book = findViewById(R.id.book);
        calendarView=findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                data = year+"-"+(month+1)+"-"+day;
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                                                // \/(czas)
                Uri uri = Uri.parse("https://www.opentable.com/s?dateTime="+data+"T19%3A00%3A00&covers=2&metroId=3143&regionIds%5B0%5D=4461&neighborhoodIds%5B0%5D=&term=&originCorrelationId=5cc77d6b-e9b7-4a31-bb7b-133d7c495ca8");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }
}