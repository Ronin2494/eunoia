package com.example.eunoia;

public class usersfeeling {

    private String feeling;
    private  String time;


    public usersfeeling() {
    }

    public usersfeeling(String feeling, String time) {
        this.feeling = feeling;
        this.time = time;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
