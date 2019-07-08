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

import com.example.geschat.adapters.ProfileNextChatAdapter;
import com.example.geschat.adapters.ProfilePreviousChatAdapter;
import com.example.geschat.models.ProfileNextChatModel;
import com.example.geschat.models.ProfilePreviousChatModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    ArrayList<ProfileNextChatModel> listaNext;
    ArrayList<ProfilePreviousChatModel> listaPrev;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_profile, container,false);


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

        return view;


    }

    public void llenar(){
        for(int i = 1; i <= 6; i++){
            listaNext.add(new ProfileNextChatModel("Chat # "+ i, "01/05/2000"));
        }

    }
    public void llenar2(){
        for(int i = 1; i <= 6; i++){
            listaPrev.add(new ProfilePreviousChatModel("asd","asd"));
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(menu != null){

            menu.findItem(R.id.cambiarFoto).setVisible(true);
        }


    }
}
