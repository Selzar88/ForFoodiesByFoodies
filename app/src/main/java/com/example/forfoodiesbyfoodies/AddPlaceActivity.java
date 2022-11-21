package com.example.forfoodiesbyfoodies;

import static com.example.forfoodiesbyfoodies.R.id.veganFriendly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AddPlaceActivity extends AppCompatActivity {

    private Button btnBrowseImage;
    private Button btnUpload;
    private ImageView imageRestaurantImage;
    private FirebaseAuth mAth;
    private EditText name, localisation, description;
    private CheckBox vegan;
    private Button add;
    private ImageView imageRestaurantView;
    int SELECT_PICTURE = 200;
    private RadioGroup radioGroup;
    private RadioButton restaurant, caterning, street;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);


        radioGroup = findViewById(R.id.radioGroup);
        name = findViewById(R.id.editTextResName);
        localisation = findViewById(R.id.editTextResLocation);
        description = findViewById(R.id.editTextResDescription);
        vegan = findViewById(veganFriendly);
        mAth = FirebaseAuth.getInstance();
        add = findViewById(R.id.btnAddPlace);
        btnBrowseImage = findViewById(R.id.btnBrowseImage);
        btnUpload = findViewById(R.id.btnUpload);
        imageRestaurantImage = findViewById(R.id.imageRestaurantImage);


        btnBrowseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3 );
            }



            {
                imageChooser();
            }

            private void imageChooser() {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }



            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                AddPlaceActivity.super.onActivityResult(requestCode, resultCode, data);


                if (resultCode == RESULT_OK && data != null) {

                    // compare the resultCode with the
                    // SELECT_PICTURE constant
                    if (requestCode == SELECT_PICTURE) {
                        // Get the url of the image from data
                        Uri selectedImageUri = data.getData();
                        if (null != selectedImageUri) {
                            imageRestaurantImage.setImageURI(selectedImageUri);
                        }
                    }
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPlace();
            }
        });
    }


    private void AddPlace() {

        String placeName = name.getText().toString().trim();
        String placeLoc = localisation.getText().toString().trim();
        String placeDesc = description.getText().toString().trim();
        Boolean isVegan = vegan.isChecked();
        String Vfriends="";

        if(isVegan==true) {
            Vfriends = "Vegan Friendly";
        }
        String option;

          restaurant = findViewById(R.id.checkRestaurant);
          caterning =findViewById(R.id.checkCatering);
          street = findViewById(R.id.checkStreetFood);

        if (restaurant.isChecked()){
            option = "Restaurant";
        }else if (caterning.isChecked()){
            option = "Catering";
        }else if(street.isChecked()){
            option= "StreetFood";
        }else{
            option= "empty";
            Toast.makeText(getApplicationContext(), "Choose the food place type", Toast.LENGTH_LONG).show();
        }



        if (placeName.isEmpty() || placeName.length() < 4) {
            Toast.makeText(getApplicationContext(), "Entry too short", Toast.LENGTH_LONG).show();
        } else if (placeLoc.isEmpty() || placeLoc.length() < 7) {
            Toast.makeText(getApplicationContext(), "Entry too short, provide at least full post code", Toast.LENGTH_LONG).show();
        } else if (placeDesc.isEmpty() || placeDesc.length() < 30) {
            Toast.makeText(getApplicationContext(), "Entry too short, please provide more details about the place", Toast.LENGTH_LONG).show();
        } else if (option.equals("empty")) {
        Toast.makeText(getApplicationContext(), "Choose the food place type", Toast.LENGTH_LONG).show();
         }
        else {
            // tu powinna  byc jeszcze jakas validacja czy to istnieje, albo wybor czy to knajpa czy catering

            FoodPlace foodPlace = new FoodPlace(placeName, placeLoc, placeDesc,"5",Vfriends );

            FirebaseDatabase.getInstance().getReference(option).push().setValue(foodPlace);
            Toast.makeText(AddPlaceActivity.this, "New food Place added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddPlaceActivity.this, MenuActivity.class));


        }


    }
}






