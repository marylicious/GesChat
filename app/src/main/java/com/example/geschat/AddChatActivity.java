package com.example.geschat;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.models.Chat;
import com.example.geschat.models.ChatFirebaseStyled;
import com.example.geschat.models.DateValidator;
import com.example.geschat.models.Time12HoursValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AddChatActivity extends AppCompatActivity {

    TextView chatTitleTV, levelTV,facilitatorTV,dateTV, startHourTV,endHourTV,presentationTV,commentsTV;
    String title,level,facilitator,date,startHour,endHour,presentation,comments, facilitatorUID,editedChatKeyDB,userRole,chatID;
    FloatingActionButton sendbtn;
    Boolean newChat,approvedProposal,finished,isFilled;
    DatabaseReference db, chatRef, chatPush;
    ArrayList<String> assistanceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);
        Bundle bundle = getIntent().getExtras();

         chatTitleTV = findViewById(R.id.add_chat_title);
         levelTV = findViewById(R.id.add_chat_level);
         facilitatorTV = findViewById(R.id.add_chat_facilitator);
         dateTV = findViewById(R.id.add_chat_date);
         startHourTV = findViewById(R.id.add_chat_hourstart);
         endHourTV = findViewById(R.id.add_chat_hourend);
         presentationTV = findViewById(R.id.add_chat_presentation);
         commentsTV = findViewById(R.id.add_chat_comments);
         sendbtn = findViewById(R.id.add_chat_send_button);




         db = FirebaseDatabase.getInstance().getReference();
         chatRef = db.child("Chat");

        //header icon , color
        Toolbar toolbar = findViewById(R.id.add_chat_Toolbar);
        setSupportActionBar(toolbar);




        if(bundle != null && bundle.getBoolean("edit")==true) {

            editedChatKeyDB = bundle.getString("keyDB");
            title = bundle.getString("title");
            level = bundle.getString("level");
            date = bundle.getString("date");
            startHour = bundle.getString("startHour");
            endHour = bundle.getString("endHour");
            presentation = bundle.getString("presentation");
            comments = bundle.getString("comments");
            facilitatorUID = bundle.getString("facilitatorDB");
            isFilled = bundle.getBoolean("filled");
            newChat=false;
            getSupportActionBar().setTitle("Edit Chat");
            loadChatInformation();

        } else {
            newChat = true;
            isFilled = false;
            getSupportActionBar().setTitle("Add New Chat");
        }

        approvedProposal = false;
        setApproved();
        finished = false;



        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareChatToDB();
            }
        });


        FloatingActionButton floatingActionButton = findViewById(R.id.btn_cancelAnnouncement);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(!newChat){
            retrieveAssList();
        }


    }

    public void retrieveAssList(){
        assistanceList = new ArrayList<>();

        db.child("Chat").child(editedChatKeyDB).addValueEventListener(new ValueEventListener() {
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
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    private Boolean validateInput(){

        DateValidator dateValidator = new DateValidator();
        Time12HoursValidator time12HoursValidator = new Time12HoursValidator();

        if(title.replaceAll("\\s+","").isEmpty()){
            chatTitleTV.setError("This field must be filled");
            chatTitleTV.requestFocus();
            return false;
        }

        if(level.replaceAll("\\s+","").isEmpty()){
            levelTV.setError("This field must be filled");
            levelTV.requestFocus();
            return false;
        }

        if(facilitator.replaceAll("\\s+","").isEmpty()){
            facilitatorTV.setError("This field must be filled");
            facilitatorTV.requestFocus();
            return false;
        }

        if(date.replaceAll("\\s+","").isEmpty()){
            dateTV.setError("This field must be filled");
            dateTV.requestFocus();
            return false;
        }

        if(startHour.replaceAll("\\s+","").isEmpty()){
            startHourTV.setError("This field must be filled");
            startHourTV.requestFocus();
            return false;
        }

        if(endHour.replaceAll("\\s+","").isEmpty()){
            endHourTV.setError("This field must be filled");
            endHourTV.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(facilitator).matches()) {
            facilitatorTV.setError("This should be a valid email");
            facilitatorTV.requestFocus();
            return false;
        }

        if(!dateValidator.validate(date)){
            dateTV.setError("Insert a valid date");
            dateTV.requestFocus();
            return false;
        }

        //TODO validacion hora

        /*if(!time12HoursValidator.validate(endHour)){
            endHourTV.setError("Insert a valid hour");
            endHourTV.requestFocus();
            return false;
        }

        if(!time12HoursValidator.validate(startHour)){
            startHourTV.setError("Insert a valid hour");
            startHourTV.requestFocus();
            return false;
        }*/

        return true;

    }


    private void setApproved(){
        //Si eres supervisor automaticamente esta aprobado

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            db.child("Users").child(uid).child("role").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userRole = dataSnapshot.getValue(String.class);
                    if(userRole.equals("supervisor")){
                        approvedProposal = true;
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

    }


    private void prepareChatToDB(){

        title = chatTitleTV.getText().toString().trim();
        level = levelTV.getText().toString().trim();
        facilitator =  facilitatorTV.getText().toString().trim();
        date = dateTV.getText().toString().trim();
        startHour = startHourTV.getText().toString().trim();
        endHour = endHourTV.getText().toString().trim();
        presentation = presentationTV.getText().toString().trim();
        comments = commentsTV.getText().toString().trim();


        Boolean valid = validateInput();

        if(valid){


                Query emailQuery = db.child("Users").orderByChild("email").equalTo(facilitator);

                emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                facilitatorUID = data.getKey();
                            }

                            sendChatToDB(newChat);

                        } else {
                            facilitatorTV.setError("This is not a valid email");
                            facilitatorTV.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Error fetching facilitator, please try again", Toast.LENGTH_LONG).show();
                    }
                });

            }

        }



    private void sendChatToDB(Boolean newChat){

        ChatFirebaseStyled chat = new ChatFirebaseStyled(approvedProposal,finished,isFilled,comments,date,endHour,facilitatorUID,level,presentation,startHour,title);


        if(newChat){
            chatPush = chatRef.push();
            chatPush.setValue(chat).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        chatID = chatPush.getKey();
                        addToFacilitatorChatToModerate();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });

        } else {

            chatRef.child(editedChatKeyDB).setValue(chat).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        addAssListToChat();
                        redirectToMain();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }


    }


    private void addAssListToChat(){

        for(String userID: assistanceList){
            chatRef.child(editedChatKeyDB).child("assistanceList").child(userID).setValue(userID);
        }

    }

    private void redirectToMain(){
        Toast.makeText(getApplicationContext(), "Process completed successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    private void addToFacilitatorChatToModerate(){

        db.child("Users").child(facilitatorUID).child("chatsToModerate").child(chatID).setValue(chatID).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    redirectToMain();
                }

            }
        });
    }



    private void loadChatInformation(){

        //Pull email de facilitador

        chatTitleTV.setText(title);
        levelTV.setText(level);
        dateTV.setText(date);
        startHourTV.setText(startHour);
        endHourTV.setText(endHour);
        presentationTV.setText(presentation);
        commentsTV.setText(comments);




        db.child("Users").child(facilitatorUID).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                facilitator = dataSnapshot.getValue(String.class);
                facilitatorTV.setText(facilitator);
                facilitatorTV.setFocusableInTouchMode(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "There was an error fetching announcements", Toast.LENGTH_SHORT).show();
            }
        });
    }

}






