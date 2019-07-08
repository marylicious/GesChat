package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ChatApproval extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_approval);


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String str =(String) bundle.getString("chatname");
            String facilitador = bundle.getString("facilitador");
            String fecha = bundle.getString("fecha");
            String nivel = bundle.getString("nivel");

            TextView debugTextView= (TextView) findViewById(R.id.debugtv);
            TextView facilitadortv = findViewById(R.id.chat_approval_facilitador);
            TextView fechatv = findViewById(R.id.chat_approval_fecha);
            TextView niveltv = findViewById(R.id.chat_approval_nivel);

            debugTextView.setText(str);
            facilitadortv.setText(facilitador);
            fechatv.setText(fecha);
            niveltv.setText(nivel);

        }


    }
}
