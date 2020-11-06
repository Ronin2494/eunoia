package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class progress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);



        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.progress);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()){
                    case R.id.homie:

                    case R.id.activity:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;


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
                        startActivity(new Intent(getApplicationContext(), home.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;


                }
                return false;
            }
        });

    }
}
