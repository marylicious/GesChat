package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatApproval extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_approval);

        TextView debugTextView= (TextView) findViewById(R.id.chat_title_name);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String str =(String) bundle.getString("chatname");
            debugTextView.setText(str);
        }


    }
}
