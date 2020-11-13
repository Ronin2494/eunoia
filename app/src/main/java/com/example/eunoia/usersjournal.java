package com.example.eunoia;

public class usersjournal {


    private String journal;
    private  String time;


    public usersjournal() {
    }

    public usersjournal(String journal, String time) {
        this.journal = journal;
        this.time = time;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
