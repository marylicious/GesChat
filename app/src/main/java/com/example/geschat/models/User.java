package com.example.geschat.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {
    public String name, email, level,role,id;

    public User(){

    }
    //QUITAR: photo
    //AGREGAR: level

    public User(String name, String email, String id, String level, String role) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.level = level;
        this.role = role;
    }


}