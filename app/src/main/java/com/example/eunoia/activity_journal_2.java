package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_journal_2 extends AppCompatActivity {

    DatabaseReference ref;
    ImageView img;
    RecyclerView recyclerView;
    ArrayList<usersjournal> list;
    journalAdapter journalAdapter1;
    FirebaseAuth fAuth;
    private String currentUserId;

    //This activity containes data sent from activity_journal to database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_2);

        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();

        img = findViewById(R.id.backArrow);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_journal.class));
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<usersjournal>();

        ref = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId).child("Users_Journal");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    usersjournal U = snapshot.getValue(usersjournal.class);
                    list.add(U);
                }
                journalAdapter1 = new journalAdapter(activity_journal_2.this, list);
                recyclerView.setAdapter(journalAdapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//read/write code for android*/
    }
}
/*"users": {
      "$uid": {
        // grants write access to the owner of this user account
        // whose uid must exactly match the key ($user_id)
        ".read": "$uid === auth.uid",
        ".write": "$uid === auth.uid"*/