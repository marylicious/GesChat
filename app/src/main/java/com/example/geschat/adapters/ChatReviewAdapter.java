package com.example.geschat.adapters;


//La tarea del adaptador es convertir un objeto chat en un item columna de la lista de chats

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.geschat.R;
import com.example.geschat.models.Chat;
import com.example.geschat.models.ChatReviewModel;

import java.util.List;



public class ChatReviewAdapter extends RecyclerView.Adapter<ChatReviewAdapter.ViewHolder>{


    private List<ChatReviewModel> chats;
    private OnChatListListener onChatListListener;


    public ChatReviewAdapter(List<ChatReviewModel> chats, OnChatListListener onChatListListener) {
        this.chats = chats;
        this.onChatListListener = onChatListListener;
    }

    //Defino Viewholder

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView chatNameTextView;

        OnChatListListener onChatListener;

        TextView facName, fecha, nivel;

        public ViewHolder(View itemView, OnChatListListener onChatListener) {

            super(itemView);

            chatNameTextView = (TextView) itemView.findViewById(R.id.chatname_row);
            facName = (TextView) itemView.findViewById(R.id.chat_review_facilitador);
            fecha = (TextView) itemView.findViewById(R.id.chat_review_fecha);
            nivel = (TextView) itemView.findViewById(R.id.chat_review_nivel);

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
    public ChatReviewAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate el layout
        View chatView = inflater.inflate(R.layout.fragment_chat_review_row, parent, false);

        // Retorna una nueva instancia del holder
        ViewHolder viewHolder = new ViewHolder(chatView,onChatListListener);
        return viewHolder;
    }

    //onBindViewHold coloca los atributos en el view segun la data

    @Override
    public void onBindViewHolder(ChatReviewAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ChatReviewModel chat1 = chats.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.chatNameTextView;
        textView.setText(chat1.getChatName());

        viewHolder.facName.setText(chats.get(position).getFacilitador());
        viewHolder.fecha.setText(chats.get(position).getFecha());
        viewHolder.nivel.setText(chats.get(position).getNivel());



    }

    //Retorna el numero de chats en la lista

    @Override
    public int getItemCount() {
        return chats.size();
    }


}
