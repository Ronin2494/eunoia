package com.example.eunoia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class Activity_routinesleep extends AppCompatActivity {

    Button btn1;
    ImageView img;
    FirebaseAuth fAuth;
    private String currentUserId;
    RadioButton first,second,third,fourth,fifth;
    FirebaseDatabase database;
    DatabaseReference ref;
    sleepHours hr;
    int i=0;
    int count1, count2, count3, count4, count5 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routinesleep);

        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();

        Toast.makeText(Activity_routinesleep.this, "Please Select an option!", Toast.LENGTH_SHORT).show();

        btn1 = findViewById(R.id.button_submit);


        img = findViewById(R.id.backArrow);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
            }
        });


        first = findViewById(R.id.less_then_4);
        second = findViewById(R.id.four_to_six);
        third = findViewById(R.id.six_to_eight);
        fourth = findViewById(R.id.more_then8);
        fifth = findViewById(R.id.no_sleep);

        ref = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hr = new sleepHours();

                String sh1 = first.getText().toString();
                String sh2 = second.getText().toString();
                String sh3 = third.getText().toString();
                String sh4 = fourth.getText().toString();
                String sh5 = fifth.getText().toString();

                String time = DateFormat.getDateTimeInstance().format(new Date());

                if (first.isChecked()) {
                    hr= new sleepHours(sh1, time);
                    ref.child("Users_Sleep").push().setValue(hr);
                } else if (second.isChecked()) {
                    hr= new sleepHours(sh2, time);
                    ref.child("Users_Sleep").push().setValue(hr);
                } else if (third.isChecked()) {
                    hr= new sleepHours(sh3, time);
                    ref.child("Users_Sleep").push().setValue(hr);
                } else if (fourth.isChecked()) {
                    hr= new sleepHours(sh4, time);
                    ref.child("Users_Sleep").push().setValue(hr);
                } else {
                    hr= new sleepHours(sh5, time);
                    ref.child("Users_Sleep").push().setValue(hr);


                }
                Toast.makeText(Activity_routinesleep.this, "Submitted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Activity_routinemeals.class));
                finish();
            }


        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.activity);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()){
                    case R.id.homie:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.activity:
                        break;

                    case R.id.progress:
                        startActivity(new Intent(getApplicationContext(), progress.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(), progress.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.doctor:
                        startActivity(new Intent(getApplicationContext(), expert.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                }
                return false;
            }
        });

    }
    @Override
    public void onBackPressed() {
    }
}