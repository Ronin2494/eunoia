package com.example.eunoia;

public class mealsTaken {

    private String Meals;
    private String time;

    public mealsTaken(String meals, String time) {
        Meals = meals;
        this.time = time;
    }

    public mealsTaken() {}

    public String getMeals() {
        return Meals;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMeals(String meals) {
        Meals = meals;
    }
}
