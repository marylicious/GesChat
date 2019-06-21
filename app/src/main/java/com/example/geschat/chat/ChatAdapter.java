package com.example.geschat.chat;

//La tarea del adaptador es convertir un objeto chat en un item columna de la lista de chats

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.geschat.R;
import java.util.List;



public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{


    private List<Chat> chats;
    private OnChatListListener onChatListListener;


    public ChatAdapter(List<Chat> chats, OnChatListListener onChatListListener) {
        this.chats = chats;
        this.onChatListListener = onChatListListener;
    }

    //Defino Viewholder

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView chatNameTextView;
        public Button messageButton;
        OnChatListListener onChatListener;


        public ViewHolder(View itemView, OnChatListListener onChatListener) {

            super(itemView);

            chatNameTextView = (TextView) itemView.findViewById(R.id.chatname_row);
            messageButton = (Button) itemView.findViewById(R.id.confirmButton);
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
    public ChatAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate el layout
        View chatView = inflater.inflate(R.layout.fragment_chats_row, parent, false);

        // Retorna una nueva instancia del holder
        ViewHolder viewHolder = new ViewHolder(chatView,onChatListListener);
        return viewHolder;
    }

    //onBindViewHold coloca los atributos en el view segun la data

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Chat chat1 = chats.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.chatNameTextView;
        textView.setText(chat1.getChatName());
        Button button = viewHolder.messageButton;
        button.setText(chat1.isFinished() ? "Confirm" : "Finished");
        button.setEnabled(chat1.isFinished());
    }

    //Retorna el numero de chats en la lista

    @Override
    public int getItemCount() {
        return chats.size();
    }


}
