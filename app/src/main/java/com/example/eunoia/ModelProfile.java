package com.example.eunoia;

import android.graphics.drawable.Drawable;

public class ModelProfile {

    private int profileImage;
    private String firstName, lastName, city, qualification, speciality, description;

    public ModelProfile(){}

    public ModelProfile(int image, String firstName, String lastName, String city, String qualification, String speciality, String description){
        this.profileImage = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.qualification = qualification;
        this.speciality = speciality;
        this.description = description;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
