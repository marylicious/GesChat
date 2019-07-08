package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FacChatRevisionStatusDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_chat_revision_status_details);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String title = bundle.getString("title");
            String facilitador = bundle.getString("facilitador");
            String fecha = bundle.getString("fecha");
            String nivel = bundle.getString("nivel");


            TextView titleTV = findViewById(R.id.status_details_debugtv);
            TextView facilitadortv = findViewById(R.id.status_details_facilitador);
            TextView fechatv = findViewById(R.id.status_details_fecha);
            TextView niveltv = findViewById(R.id.status_details_nivel);


            titleTV.setText(title);
            facilitadortv.setText(facilitador);
            fechatv.setText(fecha);
            niveltv.setText(nivel);


        }


        //header icon , color
        Toolbar toolbar = findViewById(R.id.status_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat Status");
    }
}
