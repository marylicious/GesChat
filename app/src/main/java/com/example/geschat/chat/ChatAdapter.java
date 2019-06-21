package com.example.geschat.chat;

//La tarea del adaptador es convertir un objeto chat en un item columna de la lista de chats

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.geschat.R;
import java.util.List;



public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    //Defino Viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView chatNameTextView;
        public Button messageButton;


        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            chatNameTextView = (TextView) itemView.findViewById(R.id.chatname_row);
            messageButton = (Button) itemView.findViewById(R.id.confirmButton);
        }

    }

    // Atributos del Adapter y constructor
    private List<Chat> chats;


    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    //onCreateViewHolder llama inflate en el layout del item y retorna el holder


    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate el layout
        View chatView = inflater.inflate(R.layout.fragment_chats_row, parent, false);

        // Retorna una nueva instancia del holder
        ViewHolder viewHolder = new ViewHolder(chatView);
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
