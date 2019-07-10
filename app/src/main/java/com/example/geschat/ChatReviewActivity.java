package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.geschat.adapters.ChatReviewAdapter;
import com.example.geschat.models.Chat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatReviewActivity extends AppCompatActivity implements ChatReviewAdapter.OnChatListListener {

    ArrayList<Chat> chats;
    DatabaseReference chatRef;
    RecyclerView rvChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_review);
        //header icon , color
        Toolbar toolbar = findViewById(R.id.review_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("gesChat");
        chats = new ArrayList<>();
        rvChats = findViewById(R.id.chatApprovalrv);
        chatRef = FirebaseDatabase.getInstance().getReference().child("Chat");
        rvChats.setLayoutManager(new LinearLayoutManager(this));
        populateChats();
    }

    private void populateChats(){

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chats = new ArrayList<>();

                for (DataSnapshot dataSnapshotChat : dataSnapshot.getChildren()) {
                    Boolean fin = (Boolean) dataSnapshotChat.child("finished").getValue();
                    Boolean aprov = (Boolean) dataSnapshotChat.child("approvedProposal").getValue();

                    Boolean rejected;
                    if(dataSnapshotChat.hasChild("rejected")){
                        rejected = true;
                    } else{
                        rejected = false;
                    }

                    if (!fin && !aprov && !rejected) {

                        String chatName = dataSnapshotChat.child("title").getValue(String.class);
                        String facilitator = dataSnapshotChat.child("facilitator").getValue(String.class);
                        String dateEpoch = dataSnapshotChat.child("date").getValue(String.class);
                        String level = dataSnapshotChat.child("level").getValue(String.class);
                        String startHour = dataSnapshotChat.child("startHour").getValue(String.class);
                        String endHour = dataSnapshotChat.child("endHour").getValue(String.class);
                        String comments = dataSnapshotChat.child("comments").getValue(String.class);
                        String presentation = dataSnapshotChat.child("presentation").getValue(String.class);


                        final Chat chat = new Chat(chatName, fin, facilitator, level, dateEpoch);
                        chat.setKeyDB(dataSnapshotChat.getKey());
                        chat.setStartTime(startHour);
                        chat.setEndTime(endHour);
                        chat.setComments(comments);
                        chat.setPresentation(presentation);

                        FirebaseDatabase.getInstance().getReference().child("Users").child(facilitator).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                chat.setFacilitatorName(dataSnapshot.getValue(String.class));
                                chats.add(chat);
                                setChatAdapter();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*@Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        populateChats();

    }*/

    @Override
    public void onChatClick(int position) {

        //Obtengo el chat que estoy tocando
        Chat chat = chats.get(position);

        //Navegaremos a nueva Activity cuando el user toque
        Intent intent = new Intent(this, ChatApproval.class);

        //Esto es para debuggear, se debe parsear y enviar un objeto chat
        intent.putExtra("chatname", chat.getChatName());
        intent.putExtra("facilitador", chat.getFacilitatorName());
        intent.putExtra("fecha", chat.getDate());
        intent.putExtra("nivel", chat.getLevel());
        intent.putExtra("keyDB", chat.getKeyDB());
        intent.putExtra("startHour", chat.getStartTime());
        intent.putExtra("endHour", chat.getEndTime());
        intent.putExtra("comments", chat.getComments());
        intent.putExtra("presentation", chat.getPresentation());

        startActivity(intent);

    }

    public void setChatAdapter() {
        ChatReviewAdapter adapter = new ChatReviewAdapter(chats, this);
        rvChats.setAdapter(adapter);

    }
}