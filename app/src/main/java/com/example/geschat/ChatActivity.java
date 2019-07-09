package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    String keyDB,facilitatorDB,chatName,level,facilitatorName,date,startHour,endHour,amountPeople,presentation,comments;
    ArrayList<String> assistanceList;
    Boolean approvedProposal,filled,finished;

    DatabaseReference db;

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
        assistanceList = new ArrayList<>();

        if(bundle != null) {
            chatName = bundle.getString("chatname");
            level = bundle.getString("level");
            facilitatorName = bundle.getString("facilitatorName");
            date = bundle.getString("date");
            startHour = bundle.getString("startHour");
            endHour = bundle.getString("endHour");
            amountPeople = bundle.getString("amountPeople");
            facilitatorDB = bundle.getString("facilitator");
            keyDB = bundle.getString("keyDB");
            presentation  = bundle.getString("presentation");
            comments = bundle.getString("comments");
            assistanceList = bundle.getStringArrayList("assistanceList");


            db = FirebaseDatabase.getInstance().getReference();


            chatTitleTv.setText(chatName);
            levelTv.setText(level);
            facNameTv.setText(facilitatorName);
            dateTv.setText(date);
            startHourTv.setText(startHour);
            endHourTv.setText(endHour);
            amountPeopleTv.setText(amountPeople);

            if(!assistanceList.isEmpty())
                Toast.makeText(getApplicationContext(), "assistance"+ assistanceList.get(0), Toast.LENGTH_LONG).show();

        }



    }


    public void subscribe(){

    }

    public void unsubscribe(){

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.chat_menu_edit:

                finish();
                Intent intent = new Intent(getApplicationContext(), AddChatActivity.class);
                intent.putExtra("edit",true);
                intent.putExtra("keyDB", keyDB);
                intent.putExtra("title", chatName);
                intent.putExtra("level", level );
                intent.putExtra("date", date);
                intent.putExtra("startHour", startHour);
                intent.putExtra("endHour",endHour);
                intent.putExtra("assistanceList", assistanceList);
                intent.putExtra("presentation", presentation);
                intent.putExtra("comments",comments);
                intent.putExtra("facilitatorDB",facilitatorDB);
                startActivity(intent);

                return true;

            case R.id.chat_menu_delete:

                //FALTA: eliminar de los usuarios que tienen que asistiran
                finish();
                deleteChat();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void deleteChat(){
        db.child("Users").child(facilitatorDB).child("chatsToModerate").child(keyDB).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    db.child("Chat").child(keyDB).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Chat deleted sucessfully", Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    });



                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
