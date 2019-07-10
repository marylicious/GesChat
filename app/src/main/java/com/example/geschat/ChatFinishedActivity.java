package com.example.geschat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.geschat.adapters.ChatFinishedAdapter;
import com.example.geschat.adapters.ChatReviewAdapter;
import com.example.geschat.models.Chat;

import java.util.ArrayList;

public class ChatFinishedActivity extends AppCompatActivity implements ChatFinishedAdapter.OnChatListListener{

    ArrayList<Chat> chats;
    RecyclerView rvChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_finished);
        Toolbar toolbar = findViewById(R.id.finished_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("gesChat");

        rvChats =findViewById(R.id.chatFinishedrv);
        rvChats.setLayoutManager(new LinearLayoutManager(this));




    }

    @Override
    public void onChatClick(int position){

        //Obtengo el chat que estoy tocando
        Chat chat = chats.get(position);

        //Navegaremos a nueva Activity cuando el user toque
        Intent intent = new Intent(this, ChatFinishedDetailsActivity.class);

        //Esto es para debuggear, se debe parsear y enviar un objeto chat
        intent.putExtra("chatname", chat.getChatName());


        startActivity(intent);

    }

    private void setChatAdapter(){
        ChatFinishedAdapter adapter = new ChatFinishedAdapter(chats,this);
        rvChats.setAdapter(adapter);
    }

}
