package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.geschat.adapters.FacChatCompletedDetailsAdapter;
import com.example.geschat.models.FacChatCompletedDetailsModel;

import java.util.ArrayList;

public class FacChatCompletedDetailsActivity extends AppCompatActivity {

    ArrayList<FacChatCompletedDetailsModel> listaCompletados;
    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_chat_completed_details);

        listaCompletados = new ArrayList<>();
        rcv = findViewById(R.id.completed_details_rv);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        llenar();

        FacChatCompletedDetailsAdapter adap = new FacChatCompletedDetailsAdapter(listaCompletados);
        rcv.setAdapter(adap);

        //header icon , color
        Toolbar toolbar = findViewById(R.id.chat_completed_details_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Completed Chats");
    }

    public void llenar(){
        for(int i = 1; i <= 15 ; i++){
            listaCompletados.add(new FacChatCompletedDetailsModel("123", R.mipmap.ic_launcherv2_round));
        }

    }
}
