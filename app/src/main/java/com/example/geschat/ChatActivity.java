package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.models.Chat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ChatActivity extends AppCompatActivity {
    String keyDB,facilitatorDB,chatName,level,facilitatorName,date,startHour,endHour,amountPeople,presentation,comments,userUuid;
    ArrayList<String> assistanceList;
    Button subscribeBtn, unsubscribeBtn;
    Boolean filled,isSuscribed,isThisChatFacilitator;

    DatabaseReference db;

    private TextView chatTitleTv,levelTv,facNameTv,dateTv,startHourTv,endHourTv,amountPeopleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        db = FirebaseDatabase.getInstance().getReference();
        userUuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        chatTitleTv= findViewById(R.id.chat_title_name);
        levelTv = findViewById(R.id.chat_inf_level);
        facNameTv = findViewById(R.id.chat_inf_facilitator);
        dateTv=findViewById(R.id.chat_inf_date);
        startHourTv =findViewById(R.id.chat_inf_starthour);
        endHourTv = findViewById(R.id.chat_inf_endHour);
        amountPeopleTv = findViewById(R.id.chat_info_amountPeople);
        subscribeBtn = findViewById(R.id.chat_inf_subscribe);
        unsubscribeBtn = findViewById(R.id.chat_inf_unsubscribe);
        assistanceList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();

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
            filled = bundle.getBoolean("filled");


            chatTitleTv.setText(chatName);
            levelTv.setText(level);
            facNameTv.setText(facilitatorName);
            dateTv.setText(date);
            startHourTv.setText(startHour);
            endHourTv.setText(endHour);
            amountPeopleTv.setText(amountPeople);

        }

        //Hide buttons
        subscribeBtn.setVisibility(View.GONE);
        unsubscribeBtn.setVisibility(View.GONE);


        retrieveAssList();


        subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe();
                toggleSubscriptionButtons();
                if(isThisChatFacilitator){
                    Toast.makeText(getApplicationContext(), "You can't subscribe to your own chat", Toast.LENGTH_LONG).show();
                }
            }
        });


        unsubscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unsubscribe();
                toggleSubscriptionButtons();

            }
        });



        //header icon , color
        Toolbar toolbar = findViewById(R.id.chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat Details");



    }


    public void subscribe(){
        assistanceList.add(userUuid);

        if (assistanceList.size() >= 8){
            filled = true;
            setChatFilledOnDB(true);
            hideButtonsIfFilled();
        }

        addUserToChat();
    }

    public void unsubscribe(){
        assistanceList.remove(userUuid);

        if (assistanceList.size() < 8){
            filled = false;
            setChatFilledOnDB(false);
            hideButtonsIfFilled();
        }

        removeUserFromChat();

    }

    public void retrieveAssList(){

        db.child("Chat").child(keyDB).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("assistanceList").exists()){

                    String userKey;
                    ArrayList<String> helper = new ArrayList<>();

                    for (DataSnapshot userInChat : dataSnapshot.child("assistanceList").getChildren()) {
                       userKey = userInChat.getValue(String.class);
                       helper.add(userKey);
                   }

                    assistanceList = helper;

                } else {
                    assistanceList = null;
                    assistanceList = new ArrayList<>();
                }

                checkSubscriptionStatus();
                toggleSubscriptionButtons();
                hideButtonsIfFilled();
                amountPeopleTv.setText(Integer.toString(assistanceList.size()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                intent.putExtra("presentation", presentation);
                intent.putExtra("comments",comments);
                intent.putExtra("facilitatorDB",facilitatorDB);
                intent.putExtra("filled", filled);
                startActivity(intent);

                return true;

            case R.id.chat_menu_delete:
                finish();
                deleteChat();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void deleteChat(){
        deleteChatFromIncomingChatOfUsers();

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

    private void deleteChatFromIncomingChatOfUsers(){

       for(String userID : assistanceList){

           db.child("Users").child(userID).child("incomingChats").child(keyDB).removeValue();

       }

    }

    private void hideButtonsIfFilled(){

        if(filled || assistanceList.size()>=8){
            unsubscribeBtn.setVisibility(View.GONE);
            subscribeBtn.setVisibility(View.GONE);
        } else{
            checkSubscriptionStatus();
            toggleSubscriptionButtons();
        }

    }



    private void checkSubscriptionStatus(){
        isSuscribed = false;
        isThisChatFacilitator = false;

       if(facilitatorDB.equals(userUuid) == false){

            if(assistanceList.isEmpty() == false){

                for(String user: assistanceList){
                    if(userUuid.equals(user)){
                        isSuscribed=true;
                    }
                }
            }

        } else {
            isThisChatFacilitator=true;
        }

    }

    private void toggleSubscriptionButtons(){

        if(isSuscribed){
            unsubscribeBtn.setVisibility(View.VISIBLE);
            subscribeBtn.setVisibility(View.GONE);
        } else {
            subscribeBtn.setVisibility(View.VISIBLE);
            unsubscribeBtn.setVisibility(View.GONE);
        }

    }

    private void addUserToChat(){
        db.child("Chat").child(keyDB).child("assistanceList").child(userUuid).setValue(userUuid).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                   addChatToUserIncomingChats();
                }

            }
        });
    }

    private void removefromAbandonedChat(){
        db.child("Users").child(userUuid).child("abandonedChats").child(keyDB).removeValue();
    }

    private void addChatToUserIncomingChats() {

        db.child("Users").child(userUuid).child("incomingChats").child(keyDB).setValue(keyDB).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                removefromAbandonedChat();
            }
        });

    }

    private void removeUserFromChat(){

        db.child("Users").child(userUuid).child("incomingChats").child(keyDB).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                db.child("Users").child(userUuid).child("abandonedChats").child(keyDB).setValue(keyDB).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        db.child("Chat").child(keyDB).child("assistanceList").child(userUuid).removeValue();
                    }
                });
            }
        });
    }

    private void setChatFilledOnDB(Boolean value){
        db.child("Chat").child(keyDB).child("filled").setValue(value);
    }


}
