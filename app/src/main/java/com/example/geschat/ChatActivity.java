package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.example.geschat.R;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    private TextView debugTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        debugTextView= (TextView) findViewById(R.id.debugtv);
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
