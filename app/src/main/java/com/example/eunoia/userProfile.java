package com.example.eunoia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class userProfile extends AppCompatActivity {

    TextView changeImage;
    CircleImageView circleImageView;
    ImageView img;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    private String currentUserId;
    TextView name, email;
    userProfileAdapter adapter;

    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        name = findViewById(R.id.accountName);
        email = findViewById(R.id.accountEmail);

        storageReference = FirebaseStorage.getInstance().getReference();

        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId).child("user_info");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name1 = snapshot.child("name").getValue().toString();
                String email1 = snapshot.child("email").getValue().toString();
                //String url = snapshot.child("image").child("imageUrl").getValue(String.class);
                //reference = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId).
                  //      child("user_info").child("image");
                //Picasso.get().load("imageUrl").into(circleImageView);


                name.setText(name1);
                email.setText(email1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        changeImage = findViewById(R.id.cameraImage);
        circleImageView = findViewById(R.id.profile_image);

        img = findViewById(R.id.backArrow);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), home.class));
            }
        });


        if (ContextCompat.checkSelfPermission(userProfile.this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(userProfile.this, new String[]{
                    Manifest.permission.CAMERA}, 100);
        }


        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open camera
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 100);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //called when image captured from camera

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {

            //to capture image from camera
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            captureImage.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            byte bb[] = bytes.toByteArray();
            //String file = Base64.encodeToString(bb, Base64.DEFAULT);
            //set image captured
            circleImageView.setImageBitmap(captureImage);

            uploadToFirebase(bb);

            /*reference = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId);

           adapter = new userProfileAdapter();

            int profileImage =  (int) data.getExtras().get("data");
            adapter = new userProfileAdapter(profileImage);
            reference.child("user_info").child("image").setValue(adapter);*/




            circleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(userProfile.this, changeImage.class);
                    //to see image in a new activity
                    intent.putExtra("data", captureImage);
                    startActivity(intent);
                }
            });

        }

    }


    public void uploadToFirebase(byte[] bb){
        StorageReference reference = storageReference.child("myimages/user_image.jpg");
        reference.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(userProfile.this, "Upload Successfully", Toast.LENGTH_SHORT).show();

                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId).child("user_info").child("image");
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("imageUrl", String.valueOf(uri));

                        ref.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(userProfile.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
