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
import android.widget.RadioButton;

import com.example.forfoodiesbyfoodies.User.RvUsers;

public class MakeBooking extends AppCompatActivity {
    private Button book;
    private CalendarView calendarView;
    private String data, time;
    private RadioButton time18, time19, time20;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_booking);

        ImageView imageView = findViewById(R.id.settingDrawerBooking);
        registerForContextMenu(imageView);

        book = findViewById(R.id.book);
        calendarView=findViewById(R.id.calendarView);
        time18 = findViewById(R.id.th18);
        time19 = findViewById(R.id.th19);
        time20 = findViewById(R.id.th20);




        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                data = year+"-"+(month+1)+"-"+day;
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time18.isChecked())
                {time = "18";}
                if (time19.isChecked())
                {time ="19";}
                if(time19.isChecked())
                {time="20";}


                Uri uri = Uri.parse("https://www.opentable.com/s?dateTime="+data+"T"+time+"%3A00%3A00&covers=2&metroId=3143&regionIds%5B0%5D=4461&neighborhoodIds%5B0%5D=&term=&originCorrelationId=5cc77d6b-e9b7-4a31-bb7b-133d7c495ca8");
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