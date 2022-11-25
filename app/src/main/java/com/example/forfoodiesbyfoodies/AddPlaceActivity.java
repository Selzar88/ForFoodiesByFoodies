package com.example.forfoodiesbyfoodies;

import static com.example.forfoodiesbyfoodies.R.id.btnAddPlace;
import static com.example.forfoodiesbyfoodies.R.id.checkRestaurant;
import static com.example.forfoodiesbyfoodies.R.id.imageView;
import static com.example.forfoodiesbyfoodies.R.id.veganFriendly;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class AddPlaceActivity extends AppCompatActivity {

    private Button btnBrowseImage;
    private Button btnUpload;
    private ImageView imageRestaurantImage;
    private FirebaseAuth mAth;
    private EditText name, localisation, description;

    private CheckBox vegan;
    private Button add;
    private ImageView imageRestaurantView;
    private final int PICK_IMAGE_REQUEST = 22;
    private String filePath;
    private RadioGroup radioGroup;
    private RadioButton restaurant, caterning, street;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference fDatabaseRef;
    private String admin;
    private String UserID;
    private Uri imageUri;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        //admin ID
        admin ="5j3GcPJV9gaJSKRYT77xxYvAWw63";
        UserID =  FirebaseAuth.getInstance().getCurrentUser().getUid();


        restaurant = findViewById(checkRestaurant);
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


        //only admin can add restaurants
        if (admin.equals(UserID)){
            restaurant.setEnabled(true);
        }

        storageReference = FirebaseStorage.getInstance().getReference("images");
        fDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Image");


        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlace(imageUri);
            }
        });

        // on pressing btnSelect SelectImage() is called
        btnBrowseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectImage();
            }
        });
    }

    // Select Image method
    private void SelectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Checking for request code
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the Uri of data
            imageUri = data.getData();
            imageRestaurantImage.setImageURI(imageUri);
        }
    }





    // UploadImage method
    /*private void uploadImage() {
        if (imageUri != null) {
            // progress popup
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // child for storageReference

            StorageReference storage = storageReference.child(UUID.randomUUID() + getFileExtension(filePath));

            // adding listeners on upload
            // or failure of image
            storageReference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Image uploaded successfully
                            // Dismiss dialog
                            progressDialog.dismiss();
                            Toast.makeText(AddPlaceActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    /*filePath= Uri.parse(uri.toString());
                                    String id = fDatabaseRef.push().getKey();
                                    fDatabaseRef.child(id).setValue(filePath);
                                }
                            });
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            progressDialog.dismiss();Toast.makeText(AddPlaceActivity.this, "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        // Progress Listener for loading
                        // percentage on the dialog box
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }}*/

        /*private void AddPlace () {

            String placeName = name.getText().toString().trim();
            String placeLoc = localisation.getText().toString().trim();
            String placeDesc = description.getText().toString().trim();
            Boolean isVegan = vegan.isChecked();

            String Vfriends = "";

            if (isVegan == true) {
                Vfriends = "Vegan Friendly";
            }
            String option;

            caterning = findViewById(R.id.checkCatering);
            street = findViewById(R.id.checkStreetFood);

            if (restaurant.isChecked()) {
                option = "Restaurant";
            } else if (caterning.isChecked()) {
                option = "Catering";
            } else if (street.isChecked()) {
                option = "StreetFood";
            } else {
                option = "empty";
                Toast.makeText(getApplicationContext(), "Choose the food place type", Toast.LENGTH_LONG).show();
            }


            if (placeName.isEmpty() || placeName.length() < 4) {
                Toast.makeText(getApplicationContext(), "Entry too short", Toast.LENGTH_LONG).show();
            } else if (placeLoc.isEmpty() || placeLoc.length() < 7) {
                Toast.makeText(getApplicationContext(), "Entry too short, provide at least full post code", Toast.LENGTH_LONG).show();
            } else if (placeDesc.isEmpty() || placeDesc.length() < 30) {
                Toast.makeText(getApplicationContext(), "Entry too short, please provide more details about the place", Toast.LENGTH_LONG).show();
            }else if(placeDesc.length() > 100){
                Toast.makeText(getApplicationContext(), "Entry too long, please provide less details", Toast.LENGTH_LONG).show();
            } else if (option.equals("empty")) {
                Toast.makeText(getApplicationContext(), "Choose the food place type", Toast.LENGTH_LONG).show();
            } else {

                FoodPlace foodPlace = new FoodPlace(placeName, placeLoc, placeDesc, "5", Vfriends, filePath);

                FirebaseDatabase.getInstance().getReference(option).child(placeName).setValue(foodPlace);
                Toast.makeText(AddPlaceActivity.this, "New food Place added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddPlaceActivity.this, MenuActivity.class));
            }
        }*/

        private void addPlace(Uri uri){
                StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
                fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String placeName = name.getText().toString().trim();
                                String placeLoc = localisation.getText().toString().trim();
                                String placeDesc = description.getText().toString().trim();
                                Boolean isVegan = vegan.isChecked();

                                String Vfriends = "";

                                if (isVegan == true) {
                                    Vfriends = "Vegan Friendly";
                                }
                                String option;

                                caterning = findViewById(R.id.checkCatering);
                                street = findViewById(R.id.checkStreetFood);

                                if (restaurant.isChecked()) {
                                    option = "Restaurant";
                                } else if (caterning.isChecked()) {
                                    option = "Catering";
                                } else if (street.isChecked()) {
                                    option = "StreetFood";
                                } else {
                                    option = "empty";
                                    Toast.makeText(getApplicationContext(), "Choose the food place type", Toast.LENGTH_LONG).show();
                                }

                                if (placeName.isEmpty() || placeName.length() < 4) {
                                    Toast.makeText(getApplicationContext(), "Entry too short", Toast.LENGTH_LONG).show();
                                } else if (placeLoc.isEmpty() || placeLoc.length() < 7) {
                                    Toast.makeText(getApplicationContext(), "Entry too short, provide at least full post code", Toast.LENGTH_LONG).show();
                                } else if (placeDesc.isEmpty() || placeDesc.length() < 30) {
                                    Toast.makeText(getApplicationContext(), "Entry too short, please provide more details about the place", Toast.LENGTH_LONG).show();
                                }else if(placeDesc.length() > 100){
                                    Toast.makeText(getApplicationContext(), "Entry too long, please provide less details", Toast.LENGTH_LONG).show();
                                } else if (option.equals("empty")) {
                                    Toast.makeText(getApplicationContext(), "Choose the food place type", Toast.LENGTH_LONG).show();
                                } else {

                                    FoodPlace foodPlace = new FoodPlace(placeName, placeLoc, placeDesc, "5", Vfriends, uri.toString());
                                    String foodPlaceId = fDatabaseRef.push().getKey();
                                    fDatabaseRef.child(option).setValue(foodPlace);

                                    FirebaseDatabase.getInstance().getReference(option).child(placeName).setValue(foodPlace);
                                    Toast.makeText(AddPlaceActivity.this, "New food Place added", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddPlaceActivity.this, MenuActivity.class));
                                }
                                Toast.makeText(AddPlaceActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPlaceActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
                    }
                });
        }

    private String getFileExtension(Uri mUri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(mUri));
    }
    }







