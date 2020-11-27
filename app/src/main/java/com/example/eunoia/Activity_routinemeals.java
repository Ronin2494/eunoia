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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_routinemeals extends AppCompatActivity {

    Button btn1;
    ImageView img;
    RadioButton first,second,third,fourth,fifth;
    FirebaseDatabase database1;
    DatabaseReference ref1;
    mealsTaken mt;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routinemeals);

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

        ref1 = database1.getInstance().getReference().child("UserMeals");
        mt = new mealsTaken();

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

                String sh1 = first.getText().toString();
                String sh2 = second.getText().toString();
                String sh3 = third.getText().toString();
                String sh4 = fourth.getText().toString();
                String sh5 = fifth.getText().toString();

                if (first.isChecked()) {
                    mt.setHours(sh1);
                    ref1.child(String.valueOf(i + 1)).setValue(mt);
                } else if (second.isChecked()) {
                    mt.setHours(sh2);
                    ref1.child(String.valueOf(i + 1)).setValue(mt);
                } else if (third.isChecked()) {
                    mt.setHours(sh3);
                    ref1.child(String.valueOf(i + 1)).setValue(mt);
                } else if (fourth.isChecked()) {
                    mt.setHours(sh4);
                    ref1.child(String.valueOf(i + 1)).setValue(mt);
                } else  {
                    mt.setHours(sh5);
                    ref1.child(String.valueOf(i + 1)).setValue(mt);


                }
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
                finish();
            }


        });
    }

}