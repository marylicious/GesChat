package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.geschat.adapters.FacChatCompletedDetailsAdapter;
import com.example.geschat.models.UserListChatDetailsModel;

import java.util.ArrayList;

public class FacChatCompletedDetailsActivity extends AppCompatActivity {

    ArrayList<UserListChatDetailsModel> listaCompletados;
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

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String title = bundle.getString("title");
            String facilitador = bundle.getString("facilitador");
            String fecha = bundle.getString("fecha");
            String nivel = bundle.getString("nivel");
            String inscritos = bundle.getString("inscritos");

            TextView t = findViewById(R.id.chat_completed_details_debugtv);
            TextView facilitadortv = findViewById(R.id.chat_completed_details_facilitador);
            TextView fechatv = findViewById(R.id.chat_completed_details_fecha);
            TextView niveltv = findViewById(R.id.chat_completed_details_nivel);
            TextView inscritostv = findViewById(R.id.chat_completed_details_inscritos);

            t.setText(title);
            facilitadortv.setText(facilitador);
            fechatv.setText(fecha);
            niveltv.setText(nivel);
            inscritostv.setText(inscritos);

        }
    }

    public void llenar(){
        for(int i = 1; i <= 15 ; i++){
            listaCompletados.add(new UserListChatDetailsModel("123", R.mipmap.ic_launcherv2_round));
        }

    }
}
