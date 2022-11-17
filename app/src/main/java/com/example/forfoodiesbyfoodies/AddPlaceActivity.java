package com.example.forfoodiesbyfoodies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class AddPlaceActivity extends AppCompatActivity {

    Button btnBrowseImage;
    Button btnUpload;
    Button btnAddPlace;
    EditText editTextResName, editTextResLocation, editTextTypeOfFood, editTextResDescription;
    ImageView imageRestaurantImage;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    List<All_restaurant> Restaurants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        imageRestaurantImage = findViewById(R.id.imageRestaurantImage);
        btnBrowseImage = findViewById(R.id.btnBrowseImage);
        btnUpload = findViewById(R.id.btnUpload);
        btnAddPlace = findViewById(R.id.btnAddPlace);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //image browser
        btnBrowseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosePicture();
            }
        });

        //image upload
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPicture();
            }
        });

        // Add all data to firebase
        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlace();
            }
        });



    }

    private void chosePicture() {

        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageRestaurantImage.setImageURI(imageUri);
            uploadPicture();

        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference restaurants = storageReference.child("Restaurants/" + randomKey);

        restaurants.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Progress: " + (int) progressPercent + "%");
                    }
                });


    }

    private void AddPlace() {

        String ResName = editTextResName.getText().toString().trim();
        String ResLoc = editTextResLocation.getText().toString().trim();
        String TypoOfFood = editTextTypeOfFood.getText().toString().trim();
        String ResDes = editTextResDescription.getText().toString().trim();

        if (ResName.isEmpty() || ResName.length() < 4) {
            Toast.makeText(getApplicationContext(), "Entry too short", Toast.LENGTH_LONG).show();
        } else if (ResLoc.isEmpty() || ResLoc.length() < 7) {
            Toast.makeText(getApplicationContext(), "Entry too short, provide at least full post code", Toast.LENGTH_LONG).show();
        } else if (TypoOfFood.isEmpty() || TypoOfFood.length() < 4) {
            Toast.makeText(getApplicationContext(), "Entry too short, enter vegetarian, non-vegetarian or both", Toast.LENGTH_LONG).show();
        } else if (ResDes.isEmpty() || ResDes.length() < 30) {
            Toast.makeText(getApplicationContext(), "Entry too short", Toast.LENGTH_LONG).show();
        }
    }
}