package com.example.geschat.models;

public class User {
    public String name, email, photo,role,id;

    public User(){

    }

    public User(String name, String email, String id, String photo, String role) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.photo = photo;
        this.role = role;
    }
}