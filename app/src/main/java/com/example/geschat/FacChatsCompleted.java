package com.example.geschat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.geschat.adapters.FacChatCompletedAdapter;
import com.example.geschat.models.FacChatCompletedModel;

import java.util.ArrayList;

public class FacChatsCompleted extends AppCompatActivity implements FacChatCompletedAdapter.OnCompletedListener {

    ArrayList<FacChatCompletedModel> listaCompletados;
    RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_chats_completed);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.completed_chat_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("gesChat");

        listaCompletados = new ArrayList<>();
        rcv = findViewById(R.id.chatcompletedrv);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        llenar();

        FacChatCompletedAdapter adaptador = new FacChatCompletedAdapter(listaCompletados,this);
        rcv.setAdapter(adaptador);
    }

    public void llenar(){
        for(int i = 1; i<= 20 ;i++){
            listaCompletados.add(new FacChatCompletedModel("Chat # ", "Tu", "Basico", "1234543","6"));
        }
    }

    @Override
    public void onCompletedClick(int position) {
        FacChatCompletedModel fCC = listaCompletados.get(position);

        Intent intent = new Intent(this, FacChatCompletedDetailsActivity.class);
        intent.putExtra("title", fCC.getChatName());
        intent.putExtra("facilitador", fCC.getFacName());
        intent.putExtra("fecha", fCC.getFecha());
        intent.putExtra("nivel", fCC.getNivel());
        intent.putExtra("inscritos", fCC.getInscritos());



        startActivity(intent);
    }
}
