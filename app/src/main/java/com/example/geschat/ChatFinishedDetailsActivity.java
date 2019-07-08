package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.geschat.adapters.FinishedDetailsUserListAdapter;
import com.example.geschat.models.FinishedDetailsUserList;

import java.util.ArrayList;

public class ChatFinishedDetailsActivity extends AppCompatActivity {

    private TextView debugTextView;

    ArrayList<FinishedDetailsUserList> listaUsuarios;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_finished_details);

        debugTextView= (TextView) findViewById(R.id.chat_finished_details_debugtv);
        Bundle bundle = getIntent().getExtras();

        //header icon , color
        Toolbar toolbar = findViewById(R.id.chat_finished_details_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Finished Chat Details");

        if(bundle != null) {
            String str =(String) bundle.getString("chatname");
            debugTextView.setText(str);


        }

        //RECYCLER

        listaUsuarios = new ArrayList<>();
        rv = findViewById(R.id.finished_details_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        llenarLista();

        FinishedDetailsUserListAdapter adapter = new FinishedDetailsUserListAdapter(listaUsuarios);
        rv.setAdapter(adapter);
    }

    private void llenarLista(){
        for (int i = 1; i <= 5; i++) {
            listaUsuarios.add(new FinishedDetailsUserList("123" + i, R.mipmap.ic_launcherv2_round));
        }

    }

    //FIN DE RECYCLER

}
