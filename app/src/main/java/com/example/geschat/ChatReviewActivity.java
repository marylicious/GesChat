package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.geschat.adapters.ChatReviewAdapter;
import com.example.geschat.models.Chat;

import java.util.ArrayList;

public class ChatReviewActivity extends AppCompatActivity implements ChatReviewAdapter.OnChatListListener{

    ArrayList<Chat> chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_review);
        //header icon , color
        Toolbar toolbar = findViewById(R.id.review_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("gesChat");

        RecyclerView rvChats = (RecyclerView) findViewById(R.id.chatApprovalrv);

        //Populamos unos ejemplos de chat

        chats = Chat.createChatList(20);

        // Se crea el adaptador pasando los chats de ejemplo

        ChatReviewAdapter adapter = new ChatReviewAdapter(chats,this);

        //Unimos el adaptador y el RecyclerView
        rvChats.setAdapter(adapter);

        //Le agregamos un LayoutManager por defecto
        rvChats.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onChatClick(int position){

        //Obtengo el chat que estoy tocando
        Chat chat = chats.get(position);

        //Navegaremos a nueva Activity cuando el user toque
        Intent intent = new Intent(this, ChatApproval.class);

        //Esto es para debuggear, se debe parsear y enviar un objeto chat
        intent.putExtra("chatname", chat.getChatName());
        intent.putExtra("facilitador",chat.getFacilitatorName());
        intent.putExtra("fecha",chat.getDate());
        intent.putExtra("nivel",chat.getLevel());



        startActivity(intent);

    }

}
