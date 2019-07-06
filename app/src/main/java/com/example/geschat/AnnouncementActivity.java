package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnnouncementActivity extends AppCompatActivity {

    private String keyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.announcementToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Announcement Description");

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String title = bundle.getString("title");
            String body = bundle.getString("body");
            String author = bundle.getString("author");
            keyDB = bundle.getString("keyDB");
            TextView titleTV = findViewById(R.id.acann_title);
            TextView bodyTV = findViewById(R.id.acann_body);
            TextView authorTV = findViewById(R.id.acann_author);

            getSupportActionBar().setTitle(title);
            titleTV.setText(title);
            bodyTV.setText(body);
            authorTV.setText(author);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ann_settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch(item.getItemId()){
           case R.id.ann_menu_edit:
               finish();
               Intent in = new Intent(getApplicationContext(), AddAnnouncementActivity.class);
               in.putExtra("edit",true);
               in.putExtra("keyDB", keyDB);
               startActivity(in);
               return true;

           case R.id.ann_menu_delete:
               finish();
               deleteAnn();
               return true;

           default:
               return super.onOptionsItemSelected(item);
       }


    }


    private void deleteAnn(){
        DatabaseReference annRef = FirebaseDatabase.getInstance().getReference().child("Announcement");
        annRef.child(keyDB).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Announcement deleted sucessfully", Toast.LENGTH_LONG).show();


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
