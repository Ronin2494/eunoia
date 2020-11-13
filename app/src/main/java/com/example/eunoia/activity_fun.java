package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_fun extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);

        btn1 = findViewById(R.id.btnRoutine);
        btn2 = findViewById(R.id.btnRelax);
        btn3 = findViewById(R.id.btnJournal);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_relax.class));
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Activity_routinesleep.class));
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



    public void showPopup(View v){

        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.dropdown);
        popup.show();
    }

    //Drop down Item Click listener
    @Override
    public  boolean onMenuItemClick(MenuItem item){
        switch (item.getItemId()){

            case R.id.item1:
                Toast.makeText(this, "Item1 is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item2:
                startActivity(new Intent(getApplicationContext(), progress.class));
                Toast.makeText(this, "Item2 is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item3:
                Toast.makeText(this, "Item3 is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item4:
                Toast.makeText(this, "Item4 is selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return  super.onOptionsItemSelected(item);

        }
    }
}
