package com.example.forfoodiesbyfoodies;

import static com.example.forfoodiesbyfoodies.R.id.checkRestaurant;
import static com.example.forfoodiesbyfoodies.R.id.veganFriendly;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
        imageRestaurantImage = findViewById(R.id.imagePlaceIImage);


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

                                    String foodPlaceId = fDatabaseRef.push().getKey();
                                    FoodPlace foodPlace = new FoodPlace(placeName, placeLoc, placeDesc, "5", Vfriends, uri.toString());
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







