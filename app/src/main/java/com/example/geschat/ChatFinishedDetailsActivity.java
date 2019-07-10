package com.example.geschat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.geschat.adapters.FinishedDetailsUserListAdapter;
import com.example.geschat.models.UserListChatDetailsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFinishedDetailsActivity extends AppCompatActivity {

    private TextView debugTextView;

    ArrayList<UserListChatDetailsModel> listaUsuarios;
    RecyclerView rv;
    String keyDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_finished_details);
        Bundle bundle = getIntent().getExtras();

        //header icon , color
        Toolbar toolbar = findViewById(R.id.chat_finished_details_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Finished Chat Details");

        if(bundle != null) {
            String name =(String) bundle.getString("chatname");
            String facilitador = bundle.getString("facilitador");
            String fecha = bundle.getString("fecha");
            String nivel = bundle.getString("nivel");
            keyDB = bundle.getString("keyDB");
            String startHour = bundle.getString("startHour");
            String endHour = bundle.getString("endHour");
            String amount = bundle.getString("amount");

            TextView nameTv= findViewById(R.id.chat_finished_details_debugtv);
            TextView facilitadortv = findViewById(R.id.chat_finished_facilitator);
            TextView fechatv = findViewById(R.id.chat_finished_date);
            TextView niveltv = findViewById(R.id.chat_finished_level);
            TextView startHourTv = findViewById(R.id.chat_finished_starthour);
            TextView endHourTv = findViewById(R.id.chat_finished_endhour);
            TextView amountTv = findViewById(R.id.chat_finished_amountpeople);

            nameTv.setText(name);
            facilitadortv.setText(facilitador);
            fechatv.setText(fecha);
            niveltv.setText(nivel);
            startHourTv.setText(startHour);
            endHourTv.setText(endHour);
            amountTv.setText(amount);


        }

        //RECYCLER

        listaUsuarios = new ArrayList<>();
        rv = findViewById(R.id.finished_details_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        llenarLista();


    }

    private void llenarLista(){
        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference().child("Chat").child("assistanceList");
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userUID : dataSnapshot.getChildren()) {
                    String userKey = userUID.getValue(String.class);

                    userRef.child(userKey).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String name = dataSnapshot.getValue(String.class);
                            listaUsuarios.add(new UserListChatDetailsModel(name , R.mipmap.ic_launcherv2_round));
                            setAdapter();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void setAdapter(){
        FinishedDetailsUserListAdapter adapter = new FinishedDetailsUserListAdapter(listaUsuarios);
        rv.setAdapter(adapter);
    }
    //FIN DE RECYCLER

}
