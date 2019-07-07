package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.geschat.adapters.IncomingChatAdapter;
import com.example.geschat.models.IncomingChatModel;

import java.util.ArrayList;

public class ChatIncomingActivity extends AppCompatActivity implements IncomingChatAdapter.OnIncomingChatListener {

    ArrayList<IncomingChatModel> listaChatsProximos;
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

    }

    private void llenarListaIncoming(){
        for (int i = 1; i <= 5; i++) {
            listaChatsProximos.add(new IncomingChatModel("2", "chat Num "+i, "Useless", "BASICO", "14/20019"));
        }
    }

    @Override
    public void onIncomingChatClick(int position) {
        listaChatsProximos.get(position);
        Intent intent = new Intent(this, IncomingChatDetailsActivity.class);
        startActivity(intent);
    }
}
