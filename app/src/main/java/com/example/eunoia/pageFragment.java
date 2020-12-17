package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class pageFragment extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    FirebaseAuth fAuth;
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_fragment);
        fAuth = FirebaseAuth.getInstance();



        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.progress);

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
                        startActivity(new Intent(getApplicationContext(), activity_fun.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;


                    case R.id.progress:


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
    public  boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item1:
                Toast.makeText(this, "Item1 is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item2:
                Toast.makeText(this, "Item2 is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.sign_out:
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                if(x1 < x2){
                    Intent i = new Intent(pageFragment.this, progress.class);
                    startActivity(i);
                }
                break;

        }
        return false;
    }


}

