package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.models.Announcement;
import com.example.geschat.models.Suggestion;
import com.example.geschat.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddAnnouncementActivity extends AppCompatActivity {
    private FloatingActionButton cancelbtn,sendbtn;
    private TextView titleTV,bodyTV,authorTV;
    private ProgressBar progressBar;
    DatabaseReference AnnRef;
    private String keyDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);
        Bundle bundle = getIntent().getExtras();

        //header icon , color
        Toolbar toolbar = findViewById(R.id.announcementToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Announcement");

        titleTV = findViewById(R.id.addann_title);
        bodyTV = findViewById(R.id.addann_descrip);
        authorTV = findViewById(R.id.addann_name);
        sendbtn = findViewById(R.id.addann_sendann);
        cancelbtn = findViewById(R.id.addann_cancel);
        progressBar = findViewById(R.id.addann_progressBar);
        AnnRef= FirebaseDatabase.getInstance().getReference().child("Announcement");
        keyDB = Long.toString(System.currentTimeMillis()); //inicializa la keyDB

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInfoToDB();
            }
        });

        if(bundle != null && bundle.getBoolean("edit")==true) {
            keyDB = bundle.getString("keyDB");
            loadAnnInformation();
        }

    }

    private void enableInput(boolean enable){

        cancelbtn.setEnabled(enable);
        sendbtn.setEnabled(enable);

        if(enable){
            titleTV.setFocusableInTouchMode(true);
            bodyTV.setFocusableInTouchMode(true);
            authorTV.setFocusableInTouchMode(true);
        }
        else {
            titleTV.setFocusable(false);
            bodyTV.setFocusable(false);
            authorTV.setFocusable(false);
        }

    }

    //TODO chequear cada campo

    public void sendInfoToDB(){
        enableInput(false);
        progressBar.setVisibility(View.VISIBLE);

        final String title = titleTV.getText().toString();
        final String author = authorTV.getText().toString();
        final String body = bodyTV.getText().toString() ;


        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        final String date= sdf.format(currentDate);

        if(title.replaceAll("\\s+","").isEmpty() || author.replaceAll("\\s+","").isEmpty() || body.replaceAll("\\s+","").isEmpty()){
            Toast.makeText(getApplicationContext(), "All fields must be filled", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            enableInput(true);

        } else {

                Announcement newAnn = new Announcement(title,author,body,date);

                AnnRef.child(keyDB).setValue(newAnn).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        enableInput(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Process completed successfully", Toast.LENGTH_LONG).show();


                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

    }

    private void loadAnnInformation(){

        AnnRef.child(keyDB).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Announcement ann = dataSnapshot.getValue(Announcement.class);
                titleTV.setText(ann.getTitle());
                authorTV.setText(ann.getAuthor());
                bodyTV.setText(ann.getBody());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "There was an error fetching announcements", Toast.LENGTH_SHORT).show();
            }
        });
    }


} //fin clase
