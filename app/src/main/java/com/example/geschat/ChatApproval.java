package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatApproval extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_approval);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String name = bundle.getString("chatname");
            String facilitador = bundle.getString("facilitador");
            String fecha = bundle.getString("fecha");
            String nivel = bundle.getString("nivel");

            TextView nameTv= findViewById(R.id.chat_title_name);
            TextView facilitadortv = findViewById(R.id.chat_approval_facilitador);
            TextView fechatv = findViewById(R.id.chat_approval_fecha);
            TextView niveltv = findViewById(R.id.chat_approval_nivel);

            nameTv.setText(name);
            facilitadortv.setText(facilitador);
            fechatv.setText(fecha);
            niveltv.setText(nivel);

        }


    }
}
