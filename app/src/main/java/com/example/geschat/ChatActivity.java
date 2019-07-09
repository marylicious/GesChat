package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {
    String keyDB;

    private TextView chatTitleTv,levelTv,facNameTv,dateTv,startHourTv,endHourTv,amountPeopleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatTitleTv= findViewById(R.id.chat_title_name);
        levelTv = findViewById(R.id.chat_inf_level);
        facNameTv = findViewById(R.id.chat_inf_facilitator);
        dateTv=findViewById(R.id.chat_inf_date);
        startHourTv =findViewById(R.id.chat_inf_starthour);
        endHourTv = findViewById(R.id.chat_inf_endHour);
        amountPeopleTv = findViewById(R.id.chat_info_amountPeople);


        Bundle bundle = getIntent().getExtras();

        //header icon , color
        Toolbar toolbar = findViewById(R.id.chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat Details");

        if(bundle != null) {
            String chatName = bundle.getString("chatname");
            String level = bundle.getString("level");
            String facilitatorName = bundle.getString("facilitatorName");
            String date = bundle.getString("date");
            String startHour = bundle.getString("startHour");
            String endHour = bundle.getString("endHour");
            String amountPeople = bundle.getString("amountPeople");
            keyDB = bundle.getString("keyDB");

            chatTitleTv.setText(chatName);
            levelTv.setText(level);
            facNameTv.setText(facilitatorName);
            dateTv.setText(date);
            startHourTv.setText(startHour);
            endHourTv.setText(endHour);
            amountPeopleTv.setText(amountPeople);
        }

    }
}
