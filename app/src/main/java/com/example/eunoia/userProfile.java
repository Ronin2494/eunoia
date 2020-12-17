package com.example.eunoia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        name = findViewById(R.id.accountName);
        email = findViewById(R.id.accountEmail);

        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId).child("user_info");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name1 = snapshot.child("name").getValue().toString();
                String email1 = snapshot.child("email").getValue().toString();

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

            //set image captured
            circleImageView.setImageBitmap(captureImage);


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
}
