package com.example.eunoia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

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
                    mt.setMeals(sh1);
                    ref1.child("User_Meals").child(time).setValue(mt);
                } else if (second.isChecked()) {
                    mt.setMeals(sh2);
                    ref1.child("User_Meals").child(time).setValue(mt);
                } else if (third.isChecked()) {
                    mt.setMeals(sh3);
                    ref1.child("User_Meals").child(time).setValue(mt);
                } else if (fourth.isChecked()) {
                    mt.setMeals(sh4);
                    ref1.child("User_Meals").child(time).setValue(mt);
                } else  {
                    mt.setMeals(sh5);
                    ref1.child("User_Meals").child(time).setValue(mt);


                }
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
                finish();
            }


        });
    }

}