package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class activity_journal extends AppCompatActivity {

    Button btnSubmit, list;
    ImageView img;
    DatabaseReference ref;
    EditText feel;
    usersjournal journal;
    FirebaseAuth fAuth;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();


        img = findViewById(R.id.backArrow);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
            }
        });

        btnSubmit = findViewById(R.id.buttonSubmit);

        feel = findViewById(R.id.enterFeel);

        ref = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                journal = new usersjournal();

                String description = feel.getEditableText().toString();
                String time = DateFormat.getDateTimeInstance().format(new Date());

                journal = new usersjournal(description);

                //here time act as a unique key
                ref.child("Users_Journal").child(time).setValue(journal);
                feel.setText("");


            }
        });

        //to view journal data list
        list = findViewById(R.id.journal_list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_journal_2.class));
                finish();
            }
        });


    }
}
