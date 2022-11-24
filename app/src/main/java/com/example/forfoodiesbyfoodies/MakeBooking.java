package com.example.forfoodiesbyfoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.forfoodiesbyfoodies.User.RvUsers;

public class MakeBooking extends AppCompatActivity {
    Button book;
    CalendarView calendarView;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_booking);

        ImageView imageView = findViewById(R.id.settingDrawerBooking);
        registerForContextMenu(imageView);

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


                Uri uri = Uri.parse("https://www.opentable.com/s?dateTime="+data+"T19%3A00%3A00&covers=2&metroId=3143&regionIds%5B0%5D=4461&neighborhoodIds%5B0%5D=&term=&originCorrelationId=5cc77d6b-e9b7-4a31-bb7b-133d7c495ca8");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        DropDownMenu();
    }

    public void DropDownMenu(){
        ImageView imageView = findViewById(R.id.settingDrawerBooking);
        registerForContextMenu(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MakeBooking.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btnProfile:
                                Intent intent1 = new Intent(MakeBooking.this, ProfileActivity.class);
                                startActivity(intent1);
                                return true;
                            case R.id.btnUserList:
                                Intent intent2 = new Intent(MakeBooking.this, RvUsers.class);
                                startActivity(intent2);
                                return true;
                            case R.id.btnAddPlace:
                                Intent intent3 = new Intent(MakeBooking.this, AddPlaceActivity.class);
                                startActivity(intent3);
                                return true;
                            case R.id.btnLogout:
                                Intent intent4 = new Intent(MakeBooking.this, MainActivity.class);
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