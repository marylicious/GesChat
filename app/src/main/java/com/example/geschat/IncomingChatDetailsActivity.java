package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.geschat.adapters.IncomingChatDetailsAdapter;
import com.example.geschat.models.IncomingChatDetailsModel;

import java.util.ArrayList;

public class IncomingChatDetailsActivity extends AppCompatActivity {

    ArrayList<IncomingChatDetailsModel> listaIncoming;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_chat_details);

        listaIncoming = new ArrayList<>();
        rv = findViewById(R.id.chat_incoming_details_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        llenarLista();
        IncomingChatDetailsAdapter adapter = new IncomingChatDetailsAdapter(listaIncoming);
        rv.setAdapter(adapter);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.chat_incoming_details_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Incoming Chat Details");
    }

    public void llenarLista(){
        for (int i = 1; i <=8; i++){
            listaIncoming.add(new IncomingChatDetailsModel("123456" ,R.mipmap.ic_launcherv2_round));

        }

    }
}
