package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.models.Suggestion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePageUpdateActivity extends AppCompatActivity {

    private TextView bookTxt, wordTxt, phraseTxt;
    private FloatingActionButton sendBtn, cancelBtn;
    private ProgressBar progressBar;
    DatabaseReference suggestionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_update);

        Toolbar toolbar = findViewById(R.id.homePageUpdateToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Page Update");

        bookTxt = findViewById(R.id.hup_book);
        wordTxt = findViewById(R.id.hup_word);
        phraseTxt = findViewById(R.id.hup_phrase);
        sendBtn = findViewById(R.id.hup_sendSugg);
        cancelBtn =  findViewById(R.id.hup_cancel);
        progressBar = findViewById(R.id.hup_progressbar);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        suggestionRef = FirebaseDatabase.getInstance().getReference().child("Suggestion");

        loadCurrentInfo();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendSuggestionToDB();
            }
        });
    }

    private void loadCurrentInfo(){

        suggestionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String book = dataSnapshot.child("book").getValue(String.class);
                String phrase = dataSnapshot.child("phrase").getValue(String.class);
                String word = dataSnapshot.child("word").getValue(String.class);

                bookTxt.setText(book);
                phraseTxt.setText(phrase);
                wordTxt.setText(word);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    private void enableInput(boolean enable){

        cancelBtn.setEnabled(enable);
        sendBtn.setEnabled(enable);

        if(enable){
            wordTxt.setFocusableInTouchMode(true);
            bookTxt.setFocusableInTouchMode(true);
            phraseTxt.setFocusableInTouchMode(true);
        }
        else {
            wordTxt.setFocusable(false);
            bookTxt.setFocusable(false);
            phraseTxt.setFocusable(false);
        }

    }


    private void sendSuggestionToDB(){

       enableInput(false);
       progressBar.setVisibility(View.VISIBLE);

       String word = wordTxt.getText().toString();
       String phrase = phraseTxt.getText().toString();
       String book = bookTxt.getText().toString() ;

       //TODO hacer que el error aparezca en el campo que se dejo vacio

       if(word.replaceAll("\\s+","").isEmpty() || phrase.replaceAll("\\s+","").isEmpty() || book.replaceAll("\\s+","").isEmpty()){
           Toast.makeText(getApplicationContext(), "All fields must be filled", Toast.LENGTH_SHORT).show();
           progressBar.setVisibility(View.GONE);
           enableInput(true);

       } else {
           Suggestion newSugg = new Suggestion(book, phrase, word);

           suggestionRef.setValue(newSugg).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {

                   if (task.isSuccessful()) {
                       Toast.makeText(getApplicationContext(), "Suggestions updated successfully", Toast.LENGTH_LONG).show();
                       progressBar.setVisibility(View.GONE);
                       enableInput(true);

                   } else {
                       Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                   }
               }
           });


       }

    }
}
