package com.example.geschat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AnnouncementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.announcementToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Announcement Description");

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String title = bundle.getString("title");
            String body = bundle.getString("body");
            String author = bundle.getString("author");

            TextView titleTV = findViewById(R.id.acann_title);
            TextView bodyTV = findViewById(R.id.acann_body);
            TextView authorTV = findViewById(R.id.acann_author);

            getSupportActionBar().setTitle(title);
            titleTV.setText(title);
            bodyTV.setText(body);
            authorTV.setText(author);

        }


    }
}
