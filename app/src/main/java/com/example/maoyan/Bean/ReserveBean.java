package com.example.maoyan.Bean;

public class ReserveBean {
    private String username;
    private String type;
    private String movieName;
    private String time;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ReserveBean(String username, String type, String movieName, String time) {
        this.username = username;
        this.type = type;
        this.movieName = movieName;
        this.time = time;
    }
}
