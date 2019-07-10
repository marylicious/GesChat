package com.example.geschat.adapters;


//La tarea del adaptador es convertir un objeto chat en un item columna de la lista de chats

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geschat.R;
import com.example.geschat.models.Chat;

import java.util.List;


public class ChatFinishedAdapter extends RecyclerView.Adapter<ChatFinishedAdapter.ViewHolder>{


    private List<Chat> chats;
    private OnChatListListener onChatListListener;


    public ChatFinishedAdapter(List<Chat> chats, OnChatListListener onChatListListener) {
        this.chats = chats;
        this.onChatListListener = onChatListListener;
    }

    //Defino Viewholder

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView chatNameTextView, facilitatorTv,dateTv,levelTv;

        OnChatListListener onChatListener;


        public ViewHolder(View itemView, OnChatListListener onChatListener) {

            super(itemView);

            chatNameTextView = itemView.findViewById(R.id.finishedChat_chatName);
            facilitatorTv = itemView.findViewById(R.id.finishedChat_name);
            dateTv = itemView.findViewById(R.id.finishedChat_date);
            levelTv = itemView.findViewById(R.id.finishedChat_level);

            this.onChatListener = onChatListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view){
            onChatListener.onChatClick(getAdapterPosition());
        }

    }

    //Defino interface para manejar los eventos click

    public interface OnChatListListener{
        void onChatClick(int position);
    }


    //onCreateViewHolder llama inflate en el layout del item y retorna el holder
    @NonNull
    @Override
    public ChatFinishedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate el layout
        View chatView = inflater.inflate(R.layout.fragment_dash_sup_finished_chat_row, parent, false);

        // Retorna una nueva instancia del holder
        ViewHolder viewHolder = new ViewHolder(chatView,onChatListListener);
        return viewHolder;
    }

    //onBindViewHold coloca los atributos en el view segun la data

    @Override
    public void onBindViewHolder(ChatFinishedAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Chat chat1 = chats.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.chatNameTextView;
        textView.setText(chat1.getChatName());

        TextView dateTv = viewHolder.dateTv;
        TextView facTv = viewHolder.facilitatorTv;
        TextView levelTv = viewHolder.levelTv;

        dateTv.setText(chat1.getDate());
        facTv.setText(chat1.getFacilitatorName());
        levelTv.setText(chat1.getLevel());



    }

    //Retorna el numero de chats en la lista

    @Override
    public int getItemCount() {
        return chats.size();
    }


}
