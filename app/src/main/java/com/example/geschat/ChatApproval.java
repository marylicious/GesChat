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

        TextView debugTextView= (TextView) findViewById(R.id.debugtv);
        Bundle bundle = getIntent().getExtras();

        //header icon , color
        Toolbar toolbar = findViewById(R.id.chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat Details");

        if(bundle != null) {
            String str =(String) bundle.getString("chatname");
            debugTextView.setText(str);
        }


    }
}
