package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ChatApproval extends AppCompatActivity {

    String keyDB;
    DatabaseReference chatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_approval);
        Button approve = findViewById(R.id.chat_approval_aprBtn);
        Button reject = findViewById(R.id.chat_approval_rejectBtn);

        Bundle bundle = getIntent().getExtras();
        chatRef= FirebaseDatabase.getInstance().getReference().child("Chat");

        if(bundle != null) {
            String name = bundle.getString("chatname");
            String facilitador = bundle.getString("facilitador");
            String fecha = bundle.getString("fecha");
            String nivel = bundle.getString("nivel");
            keyDB = bundle.getString("keyDB");
            String startHour = bundle.getString("startHour");
            String endHour = bundle.getString("endHour");
            String presentation  = bundle.getString("presentation");
            String comments = bundle.getString("comments");

            TextView nameTv= findViewById(R.id.chat_title_name);
            TextView facilitadortv = findViewById(R.id.chat_approval_facilitador);
            TextView fechatv = findViewById(R.id.chat_approval_fecha);
            TextView niveltv = findViewById(R.id.chat_approval_nivel);
            TextView startHourTv = findViewById(R.id.chat_approval_start);
            TextView endHourTv = findViewById(R.id.chat_approval_end);
            TextView presentationTv = findViewById(R.id.chat_approval_presentation);
            TextView commentsTv = findViewById(R.id.chat_approval_additional);

            nameTv.setText(name);
            facilitadortv.setText(facilitador);
            fechatv.setText(fecha);
            niveltv.setText(nivel);
            startHourTv.setText(startHour);
            endHourTv.setText(endHour);
            presentationTv.setText(presentation);
            commentsTv.setText(comments);

            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chatRef.child(keyDB).child("approvedProposal").setValue(true);
                    Toast.makeText(getApplicationContext(), "Chat Approved", Toast.LENGTH_LONG).show();
                    onBackPressed();

                }
            });

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chatRef.child(keyDB).child("rejected").setValue(true);
                    Toast.makeText(getApplicationContext(), "Chat Rejected", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            });

        }


    }
}
