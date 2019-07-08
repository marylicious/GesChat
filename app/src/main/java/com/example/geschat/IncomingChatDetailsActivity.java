package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.geschat.adapters.IncomingChatDetailsAdapter;
import com.example.geschat.models.IncomingChatDetailsModel;

import org.w3c.dom.Text;

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


        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            String title = bundle.getString("title");
            String fecha = bundle.getString("fecha");
            String inscritos = bundle.getString("inscritos");
            String nivel = bundle.getString("nivel");
            String facilitador = bundle.getString("facilitador");

            TextView titletv = findViewById(R.id.chat_incoming_details_debugtv);
            TextView fechatv= findViewById(R.id.chat_incoming_details_fecha);
            TextView inscritostv = findViewById(R.id.chat_incoming_details_inscritos);
            TextView niveltv = findViewById(R.id.chat_incoming_details_nivel);
            TextView facilitadortv = findViewById(R.id.chat_incoming_details_facilitador);

            titletv.setText(title);
            fechatv.setText(fecha);
            inscritostv.setText(inscritos);
            niveltv.setText(nivel);
            facilitadortv.setText(facilitador);

        }



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
