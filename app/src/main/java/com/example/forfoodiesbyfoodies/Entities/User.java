package com.example.forfoodiesbyfoodies.Entities;

public class User {

    public  String firstname, surname, email, password, role;

    public User() {
    }

    public User(String firstname, String surname, String email, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.password =password ;
        role = "user";
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
