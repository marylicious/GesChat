package com.example.geschat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geschat.adapters.ProfileNextChatAdapter;
import com.example.geschat.adapters.ProfilePreviousChatAdapter;
import com.example.geschat.models.ProfileNextPrevChat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ProfileFragment extends Fragment {

    ArrayList<ProfileNextPrevChat> listaNext;
    ArrayList<ProfileNextPrevChat> listaPrev;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private DatabaseReference db;
    TextView userProfileName;
    ImageView userProfilePict;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        //BASE DE DATOS
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference();

        listaNext = new ArrayList<>();
        listaPrev = new ArrayList<>();

        RecyclerView rv;
        rv = view.findViewById(R.id.profile_next_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        llenar();
        ProfileNextChatAdapter adaptador = new ProfileNextChatAdapter(listaNext);
        rv.setAdapter(adaptador);

        rv = view.findViewById(R.id.profile_previous_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        llenar2();
        ProfilePreviousChatAdapter adaptador2 = new ProfilePreviousChatAdapter(listaPrev);
        rv.setAdapter(adaptador2);

        //PARA SETEAR COSAS DEL PERFIL
        userProfileName = view.findViewById(R.id.userProfileName);
        userProfilePict = view.findViewById(R.id.userProfilePict);

        loadUserInformation();


        return view;


    }

    public void llenar(){
        for(int i = 1; i <= 6; i++){
            listaNext.add(new ProfileNextPrevChat("Chat # "+ i, "01/05/2000"));
        }

    }
    public void llenar2(){
        for(int i = 1; i <= 6; i++){
            listaPrev.add(new ProfileNextPrevChat("asd","asd"));
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(menu != null){

            menu.findItem(R.id.cambiarFoto).setVisible(true);
        }


    }

    private void loadUserInformation(){

        if (user != null) {
            userProfileName.setText(user.getEmail());

            //cargar nombre del usuario

            if(user.getDisplayName() == null){

                DatabaseReference userRef = db.child("Users").child(user.getUid()).child("name");


                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.getValue(String.class);
                        userProfileName.setText(name);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });

            } else {
                userProfileName.setText(user.getDisplayName());
            }

            //cargar foto del usuario
            if(user.getPhotoUrl() != null){
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        //.fitCenter()
                        .centerCrop()
                        .into(userProfilePict);
            }

        }



    }
}
