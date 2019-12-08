package com.example.assignmentdegree;

public class Movie {
    private String movie_name;
    private int movieImageId;
    private String description;

    public Movie(int movieImageId,String movie_name,String description) {
        this.movie_name = movie_name;
        this.movieImageId = movieImageId;
        this.description=description;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public int getMovieImageId() {
        return movieImageId;
    }

    public String getDescription() {
        return description;
    }

}
