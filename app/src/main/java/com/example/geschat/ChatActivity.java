package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.example.geschat.R;
import android.os.Bundle;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    private TextView debugTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        debugTextView= (TextView) findViewById(R.id.debugtv);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String str =(String) bundle.getString("chatname");
            debugTextView.setText(str);
        }
    }
}
