package com.example.geschat;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AddAnnouncementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.announcementToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Announcement");

        //Boton de cerrar
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.btn_cancelAnnouncement);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
