package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.geschat.adapters.IncomingChatAdapter;
import com.example.geschat.models.Chat;

import java.util.ArrayList;
import java.util.Collections;

public class ChatIncomingActivity extends AppCompatActivity implements IncomingChatAdapter.OnIncomingChatListener {

    ArrayList<Chat> listaChatsProximos;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_incoming);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.incoming_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("gesChat");

        //Recycler
        listaChatsProximos = new ArrayList<>();
        rv = findViewById(R.id.chatIncomingrv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        llenarListaIncoming();

        IncomingChatAdapter adapter = new IncomingChatAdapter(listaChatsProximos,this);

        rv.setAdapter(adapter);

        //sortview();

    }

    private void llenarListaIncoming(){
        /*for (int i = 1; i <= 5; i++) {*/
            listaChatsProximos.add(new Chat(5, "chat Num ", "Useless", "BASICO", "1"));
        listaChatsProximos.add(new Chat(5, "chat Num ", "false", "BASICO", "9"));
        listaChatsProximos.add(new Chat(5, "chat Num ", "Useless", "BASICO", "6"));
        listaChatsProximos.add(new Chat(5, "chat Num ", "false", "BASICO", "4"));
       /* }*/
    }


   /* public void sortview(){
        Collections.sort(listaChatsProximos,Chat.ByStatus);
    }*/

    @Override
    public void onIncomingChatClick(int position) {

        Intent intent = new Intent(this, IncomingChatDetailsActivity.class);

        Chat prox = listaChatsProximos.get(position);
        intent.putExtra("title", prox.getChatName());
        intent.putExtra("fecha", prox.getDate());
        intent.putExtra("inscritos", Integer.toString(prox.getAmountPeople()));
        intent.putExtra("nivel", prox.getLevel());
        intent.putExtra("facilitador", prox.getFacilitatorName());




        startActivity(intent);
    }
}
