package com.example.geschat.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geschat.R;
import com.example.geschat.models.IncomingChatModel;

import java.util.ArrayList;

public class IncomingChatAdapter extends RecyclerView.Adapter<IncomingChatAdapter.HolderIncomingChat>{

    ArrayList<IncomingChatModel> listaChatsProximos;
    private OnIncomingChatListener onIncomingChatListener;

    public IncomingChatAdapter(ArrayList<IncomingChatModel> listaChatsProximos, OnIncomingChatListener onIncomingChatListener) {
        this.listaChatsProximos = listaChatsProximos;
        this.onIncomingChatListener = onIncomingChatListener;
    }

    @NonNull
    @Override
    public HolderIncomingChat onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_dash_incoming_chat_row,null,false);
        return new HolderIncomingChat(view, onIncomingChatListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderIncomingChat holderIncomingChat, int i) {
        holderIncomingChat.numInscritos.setText(listaChatsProximos.get(i).getInscritos());
        holderIncomingChat.chatNombre.setText(listaChatsProximos.get(i).getNombreChat());
        holderIncomingChat.facilitadorNombre.setText(listaChatsProximos.get(i).getNombreFacilitador());
        holderIncomingChat.fecha.setText(listaChatsProximos.get(i).getFecha());
        holderIncomingChat.nivel.setText(listaChatsProximos.get(i).getNivel());
    }

    @Override
    public int getItemCount() {
        return listaChatsProximos.size();
    }


    public interface OnIncomingChatListener{
        void onIncomingChatClick(int position);
    }


    public class HolderIncomingChat extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView chatNombre, facilitadorNombre, numInscritos, nivel,fecha;
        OnIncomingChatListener onIncomingChatListener;

        public HolderIncomingChat(@NonNull View itemView, OnIncomingChatListener onIncomingChatListener) {
            super(itemView);

            chatNombre = (TextView) itemView.findViewById(R.id.incomingChat_chatName);
            facilitadorNombre = (TextView) itemView.findViewById(R.id.incomingChat_name_facilitador);
            numInscritos = (TextView) itemView.findViewById(R.id.incomingChat_totalUsersInscritos);
            nivel = (TextView) itemView.findViewById(R.id.incomingChat_level);
            fecha = (TextView) itemView.findViewById(R.id.incomingChat_date);
            this.onIncomingChatListener = onIncomingChatListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onIncomingChatListener.onIncomingChatClick(getAdapterPosition());
        }
    }
}
