package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_journal_2 extends AppCompatActivity {

    ListView myList;

    DatabaseReference ref;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_2);

        img = findViewById(R.id.backArrow);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_journal.class));
            }
        });

        final ArrayList<String> myArrayList = new ArrayList<>();

        final ArrayAdapter myArrayAdapter = new ArrayAdapter<String>(activity_journal_2.this, android.R.layout.simple_list_item_1, myArrayList);


        myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(myArrayAdapter);

        ref = FirebaseDatabase.getInstance().getReference().child("UsersJournal");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                myArrayList.clear();

                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    myArrayList.add(snapshot.child("journal").getValue().toString());
                }
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
