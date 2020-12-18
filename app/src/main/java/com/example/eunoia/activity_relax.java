package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class activity_relax extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    Button btn1, btn2, btn3;
    ImageView img;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);

        fAuth = FirebaseAuth.getInstance();

        btn1 = findViewById(R.id.button2);
        btn2 = findViewById(R.id.button3);
        btn3 = findViewById(R.id.button4);

        img = findViewById(R.id.backArrow);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_fun.class));
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), breathing.class));
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), listening.class));
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
                startActivity(new Intent(getApplicationContext(), userProfile.class));
                return true;

            case R.id.sign_out:
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();

            default:
                return  super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
    }

}
