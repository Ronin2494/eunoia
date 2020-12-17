package com.example.eunoia;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class changeImage extends AppCompatActivity {


    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_image);

        profile = findViewById(R.id.imageBig);
        //setting image to the imageview
        Bitmap image1 = (Bitmap) getIntent().getExtras().get("data");

        profile.setImageBitmap(image1);

    }
}
