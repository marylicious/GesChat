package com.example.geschat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import com.example.geschat.chat.Chat;
import com.example.geschat.chat.ChatAdapter;


public class ChatFragment extends Fragment {

    ArrayList<Chat> chats;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container,false);

        RecyclerView rvChats = (RecyclerView) view.findViewById(R.id.chatView);

        //Populamos unos ejemplos de chat

        chats = Chat.createChatList(20);

        // Se crea el adaptador pasando los chats de ejemplo

        ChatAdapter adapter = new ChatAdapter(chats);

        //Unimos el adaptador y el RecyclerView
        rvChats.setAdapter(adapter);

        //Le agregamos un LayoutManager por defecto
        rvChats.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }





}
