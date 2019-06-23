package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AddChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.add_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create New Chat");
    }
}
