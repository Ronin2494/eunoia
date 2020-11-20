package com.example.eunoia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_routinesleep extends AppCompatActivity {

    Button btn1;
    ImageView img;
    RadioButton first,second,third,fourth,fifth;
    FirebaseDatabase database;
    DatabaseReference ref;
    sleepHours hr;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routinesleep);

        btn1 = findViewById(R.id.button_submit);


        img = findViewById(R.id.backArrow);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
            }
        });


        first=findViewById(R.id.less_then_4);
        second=findViewById(R.id.four_to_six);
        third=findViewById(R.id.six_to_eight);
        fourth=findViewById(R.id.more_then8);
        fifth=findViewById(R.id.no_sleep);

        ref = database.getInstance().getReference().child("user");
        hr = new sleepHours();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    i=(int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String sh1= first.getText().toString();
                String sh2= second.getText().toString();
                String sh3= third.getText().toString();
                String sh4= fourth.getText().toString();
                String sh5= fifth.getText().toString();

                if(first.isChecked()){
                    hr.setHours(sh1);
                    ref.child(String.valueOf(i+1)).setValue(hr);
                }
                else if(second.isChecked()){
                    hr.setHours(sh2);
                    ref.child(String.valueOf(i+1)).setValue(hr);
                }
                else if(third.isChecked()){
                    hr.setHours(sh3);
                    ref.child(String.valueOf(i+1)).setValue(hr);
                }
                else if(fourth.isChecked()){
                    hr.setHours(sh4);
                    ref.child(String.valueOf(i+1)).setValue(hr);
                }
                else {
                    hr.setHours(sh5);
                    ref.child(String.valueOf(i+1)).setValue(hr);
                }



                startActivity(new Intent(getApplicationContext(), Activity_routinemeals.class));
                finish();
            }
        });



    }
}