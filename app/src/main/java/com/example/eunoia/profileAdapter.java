package com.example.eunoia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class profileAdapter extends PagerAdapter {

    private List<ModelProfile> modelProfiles;
    private LayoutInflater layoutInflater;
    private Context context;

    public profileAdapter(List<ModelProfile> modelProfiles, Context context) {
        this.modelProfiles = modelProfiles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelProfiles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_profile, container, false);

        ImageView imageView;
        TextView firstN, lastN, City;
        Button viewProfile;

        imageView = view.findViewById(R.id.profileImage);
        firstN = view.findViewById(R.id.drFirstName);
        lastN = view.findViewById(R.id.drlastName);
        City = view.findViewById(R.id.drCity);
        viewProfile = view.findViewById(R.id.visit);

        imageView.setImageResource(modelProfiles.get(position).getProfileImage());
        firstN.setText(modelProfiles.get(position).getFirstName());
        lastN.setText(modelProfiles.get(position).getLastName());
        City.setText(modelProfiles.get(position).getCity());

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(),expertProfile.class);
                i.putExtra("name", String.valueOf(modelProfiles.get(position).getFirstName()));
                i.putExtra("last", String.valueOf(modelProfiles.get(position).getLastName()));
                i.putExtra("city", String.valueOf(modelProfiles.get(position).getCity()));
                i.putExtra("image", modelProfiles.get(position).getProfileImage());
                i.putExtra("qualification", modelProfiles.get(position).getQualification());
                i.putExtra("speciality", modelProfiles.get(position).getSpeciality());
                i.putExtra("description", modelProfiles.get(position).getDescription());
                
                context.startActivity(i);
            }
        });

        container.addView(view, 0);
        return view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
