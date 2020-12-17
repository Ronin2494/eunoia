package com.example.eunoia;

import java.text.SimpleDateFormat;

public class usersfeeling {

    //CLass to set and get user feeling activity data

    private String feeling;
    private String time;


    public usersfeeling(String feeling, String time) {
        this.feeling = feeling;
        this.time = time;
    }

    public usersfeeling() {

    }

    public String getFeeling() {
        return feeling;
    }

    public usersfeeling(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }


}
