package com.example.eunoia;

public class users {

    //Class for storing user info into the database
    public String name, email, password, confirm_password;

    public users(String name, String email, String  password, String confirm_password){
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirm_password = confirm_password;
    }

}
