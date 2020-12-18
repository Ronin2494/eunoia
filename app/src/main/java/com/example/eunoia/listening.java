package com.example.eunoia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class listening extends AppCompatActivity {

    MediaPlayer player, player1, player2;
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
                player.pause();
                player1.pause();
                player2.pause();
            }
        });


    }

    public void play(View v) {
        if (player == null && player1 == null && player2 == null) {
            player = MediaPlayer.create(this, R.raw.meditation1);
            player1 = MediaPlayer.create(this, R.raw.meditation2);
            player2 = MediaPlayer.create(this, R.raw.meditation_3);

        }
        player.start();
        player1.start();
        player2.start();
        Toast.makeText(listening.this, "Now Playing!", Toast.LENGTH_SHORT).show();

    }



    public void pause(View v) {
        if (player != null && player2 != null && player1 != null) {
            player.pause();
            player1.pause();
            player2.pause();
            Toast.makeText(listening.this, "Stopped Playing!", Toast.LENGTH_SHORT).show();
        }
    }
}

