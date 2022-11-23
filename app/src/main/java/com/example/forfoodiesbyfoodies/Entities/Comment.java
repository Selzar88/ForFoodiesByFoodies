package com.example.forfoodiesbyfoodies.Entities;

public class Comment {

    private String userName;
    private String text;

    public Comment() {
    }

    public Comment(String userName, String text) {
        this.userName = userName;
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }
}
