package com.example.geschat;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomePageUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_update);

        Toolbar toolbar = findViewById(R.id.homePageUpdateToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Page Update");

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
