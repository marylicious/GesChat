package com.example.geschat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import com.example.geschat.models.Chat;
import com.example.geschat.adapters.ChatAdapter;


public class ChatFragment extends Fragment implements ChatAdapter.OnChatListListener{

    ArrayList<Chat> chats;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container,false);


        //para abrir el add chat

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.btn_addChat);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddChatActivity.class);
                startActivity(in);
            }
        });


        RecyclerView rvChats = (RecyclerView) view.findViewById(R.id.chatView);

        //Populamos unos ejemplos de chat

        chats = Chat.createChatList(20);

        // Se crea el adaptador pasando los chats de ejemplo

        ChatAdapter adapter = new ChatAdapter(chats,this);

        //Unimos el adaptador y el RecyclerView
        rvChats.setAdapter(adapter);

        //Le agregamos un LayoutManager por defecto
        rvChats.setLayoutManager(new LinearLayoutManager(getActivity()));




        return view;
    }

    @Override
    public void onChatClick(int position){

        //Obtengo el chat que estoy tocando
        Chat chat = chats.get(position);

        //Navegaremos a nueva Activity cuando el user toque
        Intent intent = new Intent(getActivity(), ChatActivity.class);

        //Esto es para debuggear, se debe parsear y enviar un objeto chat
        intent.putExtra("chatname", chat.getChatName());


        startActivity(intent);

    }


}
