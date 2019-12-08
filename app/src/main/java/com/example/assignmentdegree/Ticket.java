package com.example.assignmentdegree;

import java.util.ArrayList;

public class Ticket {
    private String movie_name;
    private String time;
    private String date;
    private ArrayList<Integer> seat;

    public Ticket(String movie_name, String time, String date, ArrayList<Integer> seat) {
        this.movie_name = movie_name;
        this.time = time;
        this.date = date;
        this.seat = seat;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Integer> getSeat() {
        return seat;
    }


}
