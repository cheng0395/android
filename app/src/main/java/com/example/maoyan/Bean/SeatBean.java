package com.example.maoyan.Bean;

public class SeatBean {
    private String img;
    private String movieName;
    private String username;
    private int seatNumber;
    private boolean selected;
    private String totalPrice;

    public SeatBean() {
    }

    public SeatBean(String img, String movieName, String username, int seatNumber, boolean selected, String totalPrice) {
        this.img = img;
        this.movieName = movieName;
        this.username = username;
        this.seatNumber = seatNumber;
        this.selected = selected;
        this.totalPrice = totalPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
