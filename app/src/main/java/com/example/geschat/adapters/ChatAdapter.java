package com.example.geschat.adapters;

//La tarea del adaptador es convertir un objeto chat en un item columna de la lista de chats

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.geschat.R;
import com.example.geschat.models.Chat;
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

        public TextView chatNameTextView, facilitatorNameTv, levelTv,dateTv, numTv;
        public Button messageButton;
        OnChatListListener onChatListener;


        public ViewHolder(View itemView, OnChatListListener onChatListener) {

            super(itemView);

            chatNameTextView = itemView.findViewById(R.id.chatname_row);
            messageButton =  itemView.findViewById(R.id.confirmButton);
            facilitatorNameTv = itemView.findViewById(R.id.facilitador_chatrow);
            levelTv = itemView.findViewById(R.id.chatrow_level);
            dateTv = itemView.findViewById(R.id.chatrow_date);
            numTv = itemView.findViewById(R.id.chatrow_num);


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

        TextView textView = viewHolder.chatNameTextView;
        TextView facilitator = viewHolder.facilitatorNameTv;
        TextView level = viewHolder.levelTv;
        TextView date = viewHolder.dateTv;
        TextView num = viewHolder.numTv;
        Button button = viewHolder.messageButton;


        button.setBackgroundColor(chat1.getFilled() ? Color.parseColor("#16cc4c") : Color.parseColor("#e5322e"));
        button.setEnabled(false);
        textView.setText(chat1.getChatName());
        facilitator.setText("Facilitator "+chat1.getFacilitator());
        level.setText("Level "+chat1.getLevel());
        num.setText(chat1.getAmountPeople());
        date.setText("Date"+ chat1.getDateEpoch());

    }

    //Retorna el numero de chats en la lista

    @Override
    public int getItemCount() {
        return chats.size();
    }


}
