package com.example.eunoia;

public class sleepHours {

    //Class to get and set data for sleep-routine activity

        private String Hours;
        private String time;

    public sleepHours(String hours, String time) {
        Hours = hours;
        this.time = time;
    }

    public sleepHours() {}

        public String getHours() {
            return Hours;
        }

        public void setHours(String hours) {
            Hours = hours;
        }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
