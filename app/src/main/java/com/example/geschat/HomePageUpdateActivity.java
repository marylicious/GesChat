package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class HomePageUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_update);

        Toolbar toolbar = findViewById(R.id.homePageUpdateToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Page Update");
    }
}
