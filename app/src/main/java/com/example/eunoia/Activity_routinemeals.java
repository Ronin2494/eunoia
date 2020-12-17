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

public class Activity_routinemeals extends AppCompatActivity {

    Button btn1;
    ImageView img;
    FirebaseAuth fAuth;
    private String currentUserId;
    RadioButton first,second,third,fourth,fifth;
    DatabaseReference ref1;
    mealsTaken mt;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routinemeals);

        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();

        Toast.makeText(Activity_routinemeals.this, "Please Select an option!", Toast.LENGTH_SHORT).show();

        btn1 = findViewById(R.id.button_submit);

        img = findViewById(R.id.backArrow);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
            }
        });

        first = findViewById(R.id.just_1_meal);
        second = findViewById(R.id.just_2_meal);
        third = findViewById(R.id.just_3_meal);
        fourth = findViewById(R.id.just_2_1);
        fifth = findViewById(R.id.just_1_2);

        ref1 = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId);
        //ref1 = FirebaseDatabase.getInstance().getReference().child("UserMeals");


        ref1.addValueEventListener(new ValueEventListener() {
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

                mt = new mealsTaken();

                String sh1 = first.getText().toString();
                String sh2 = second.getText().toString();
                String sh3 = third.getText().toString();
                String sh4 = fourth.getText().toString();
                String sh5 = fifth.getText().toString();

                String time = DateFormat.getDateTimeInstance().format(new Date());



                if (first.isChecked()) {
                    mt = new mealsTaken(sh1,time);
                    ref1.child("User_Meals").push().setValue(mt);
                } else if (second.isChecked()) {
                    mt = new mealsTaken(sh2,time);
                    ref1.child("User_Meals").push().setValue(mt);
                } else if (third.isChecked()) {
                    mt = new mealsTaken(sh3,time);
                    ref1.child("User_Meals").push().setValue(mt);
                } else if (fourth.isChecked()) {
                    mt = new mealsTaken(sh4,time);
                    ref1.child("User_Meals").push().setValue(mt);
                } else  {
                    mt = new mealsTaken(sh5,time);
                    ref1.child("User_Meals").push().setValue(mt);


                }
                Toast.makeText(Activity_routinemeals.this, "Submitted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
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

}