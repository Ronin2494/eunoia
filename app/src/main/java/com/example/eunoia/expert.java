package com.example.eunoia;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class expert extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ViewPager viewPager;
    profileAdapter adapter;
    List<ModelProfile> modelProfiles;
    ModelProfile modelProfile;
    DatabaseReference db;
    FirebaseAuth fAuth;
    Button viewProfile;

    /*String[] fName = {"Dr Jordan", "Dr Rose", "Dr Amanda", "Dr Wilson"};
    String[] lName = {"Gilbert", "Parker", "Cerney", "Avenue"};
    String[] city =  {"Kitchener", "cambridge", "Waterloo", "Toronto"};
    int[] image = {R.drawable.dr1, R.drawable.dr2, R.drawable.dr3, R.drawable.dr4};
    String[] Qualification = {"Psyhcologist, Phd, CPsych", "Psyhcologist, Phd","Psyhcologist, Phd, CPsych", "Psyhcologist, Phd" };*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert);

        viewProfile = findViewById(R.id.visit);

        fAuth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance().getReference("Experts_data");
        modelProfile = new ModelProfile();


        modelProfiles = new ArrayList<>();
        modelProfiles.add(new ModelProfile(R.drawable.dr1, "Dr Rose", "Parker", "Waterloo, ON", "Psyhcologist, Phd, CPsych", "Speciality: Anxiety, Depression, PTSD", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"));
        modelProfiles.add(new ModelProfile(R.drawable.dr2, "Dr Jordan", "Gilbert", "Kitchener, ON", "Psyhcologist, Phd", "Speciality: Anxiety, Depression", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"));
        modelProfiles.add(new ModelProfile(R.drawable.dr3, "Dr Amanda", "Cerney", "Cambridge, ON", "Psyhcologist, Phd, CPsych", "Speciality: Anxiety, Depression, PTSD", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"));
        modelProfiles.add(new ModelProfile(R.drawable.dr4, "Dr Wilson", "James", "Toronto, ON", "Psyhcologist, Phd", "Speciality: Anxiety, Depression", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"));
        modelProfiles.add(new ModelProfile(R.drawable.kate, "Dr Kate", "Spere", "Kitchener, ON", "C.PSYCH, Phd", "Speciality: Anxiety, Depression", "Dr. Spere is passionate about her work and is committed to people reach their full potential. She provides therapy to people for a variety of concerns including anxiety, depression and stress"));
        modelProfiles.add(new ModelProfile(R.drawable.dr1, "Dr Wilson", "James", "Toronto, ON", "Psyhcologist, Phd", "Speciality: Anxiety, Depression", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"));


        db.setValue(modelProfiles);

        adapter = new profileAdapter(modelProfiles, this);

            viewPager = findViewById(R.id.viewPager);
            viewPager.setAdapter(adapter);
            viewPager.setPadding(150, 0, 150, 0);



        //Bottom Navigation View to switch between activities
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.doctor);

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
                        break;

                }
                return false;
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



