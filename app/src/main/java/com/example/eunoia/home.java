package com.example.eunoia;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class home extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button submit;
    EditText feel;
    SeekBar seekBar;
    TextView textView;
    DatabaseReference reference;
    usersfeeling feeling1;
    FirebaseAuth fAuth;
    private String currentUserId;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);


        seekBar = findViewById(R.id.seekBar);
        textView= findViewById(R.id.progressText);

        fAuth = FirebaseAuth.getInstance();
        currentUserId = fAuth.getCurrentUser().getUid();// for current user id

        textView.setText(""+seekBar.getProgress());

        //progress bar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Bottom Navigation View to switch between activities
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homie);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()){
                    case R.id.homie:
                        break;

                    case R.id.activity:
                        startActivity(new Intent(getApplicationContext(), activity_fun.class));
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
                        startActivity(new Intent(getApplicationContext(), expert.class));
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                }
                return false;
            }
        });


        //Database
        //getReference is used to set path
        reference = FirebaseDatabase.getInstance().getReference("users_data").child(currentUserId);

        submit = findViewById(R.id.buttonSubmit);
        feel = findViewById(R.id.enterFeel);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feeling1 = new usersfeeling();//object of the class usersfeeling

                String description = feel.getEditableText().toString();
                String time = DateFormat.getDateTimeInstance().format(new Date());
                //String time2 = time1.replace(",", " ");
                //String time = time2.replace(":", " ");

                feeling1 = new usersfeeling(description, time);//passing value to feeling1
                reference.child("Users_Feeling").push().setValue(feeling1);
                feel.setText("");
                Toast.makeText(home.this, "Message Submitted", Toast.LENGTH_SHORT).show();

            }
        });



    }



    //Drop-down pop-up menu
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


}
