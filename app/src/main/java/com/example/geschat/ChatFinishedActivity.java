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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_finished);
        //header icon , color
        Toolbar toolbar = findViewById(R.id.finished_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("gesChat");

        RecyclerView rvChats = (RecyclerView) findViewById(R.id.chatFinishedrv);

        //Populamos unos ejemplos de chat

        chats = Chat.createChatList(20);

        // Se crea el adaptador pasando los chats de ejemplo

        ChatFinishedAdapter adapter = new ChatFinishedAdapter(chats,this);


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
        Intent intent = new Intent(this, ChatFinishedDetailsActivity.class);

        //Esto es para debuggear, se debe parsear y enviar un objeto chat
        intent.putExtra("chatname", chat.getChatName());


        startActivity(intent);

    }

}
