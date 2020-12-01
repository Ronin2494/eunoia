package com.example.eunoia;

public class usersjournal {

//It is used to fetch data from database for journal activity

    private String journal;
    //private  String time;


    public usersjournal() {
    }

    public usersjournal(String journal) {
        this.journal = journal;
        //this.time = time;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }


}
