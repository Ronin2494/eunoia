package com.example.eunoia;

import android.graphics.Bitmap;

public class userProfileAdapter {

    private int profileImage;

    public userProfileAdapter(){}

    public userProfileAdapter(int profileImage){
        this.profileImage = profileImage;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }
}
