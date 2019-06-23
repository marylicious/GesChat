package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AddAnnouncementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.announcementToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Announcement");
    }
}
