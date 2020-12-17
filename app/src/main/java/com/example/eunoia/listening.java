package com.example.eunoia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class listening extends AppCompatActivity {

    MediaPlayer player;
    ImageView imageView1, imageView2, imageView3, imageView4;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listening);

        imageView1 = findViewById(R.id.imageView5);
        imageView2 = findViewById(R.id.imageView6);
        imageView3 = findViewById(R.id.imageView7);
        imageView4 = findViewById(R.id.imageView8);


        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_relax.class));
            }
        });


    }

    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.meditation1);
            player = MediaPlayer.create(this, R.raw.meditation2);
            player = MediaPlayer.create(this, R.raw.meditation_3);

        }
        player.start();

    }

    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }
}

