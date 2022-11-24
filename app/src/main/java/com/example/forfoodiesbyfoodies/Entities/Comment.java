package com.example.forfoodiesbyfoodies.Entities;

public class Comment {

    private String userName;
    private String text;


    private String place;

    public Comment() {
    }

    public Comment(String userName, String text, String place) {
        this.userName = userName;
        this.text = text;
        this.place = place;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public String getPlace() {return place;}
}
