package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.geschat.adapters.FacChatRevisionStatusAdapter;
import com.example.geschat.models.Chat;

import java.util.ArrayList;

public class FacChatRevisionStatusActivity extends AppCompatActivity implements FacChatRevisionStatusAdapter.OnStatusListener {

    ArrayList<Chat> listaStatus;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_chat_revision_status);
        //header icon , color
        Toolbar toolbar = findViewById(R.id.revision_status_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("gesChat");

        listaStatus = new ArrayList<>();
        recyclerView = findViewById(R.id.revision_status_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llenarStatus();

        FacChatRevisionStatusAdapter adaptador = new FacChatRevisionStatusAdapter(listaStatus,this);
        recyclerView.setAdapter(adaptador);

    }

    public void llenarStatus(){
        for(int i = 1;i <= 20; i++)
            listaStatus.add(new Chat("a","a","a","a","a"));
    }


    @Override
    public void onStatusClick(int position) {
        Chat Fc = listaStatus.get(position);

        Intent intent = new Intent(this,FacChatRevisionStatusDetails.class);
        intent.putExtra("title", Fc.getChatName());
        intent.putExtra("facilitador", Fc.getFacilitatorName());
        intent.putExtra("fecha", Fc.getDate());
        intent.putExtra("nivel", Fc.getLevel());

        startActivity(intent);
    }
}
