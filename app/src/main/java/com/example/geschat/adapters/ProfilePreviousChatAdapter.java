package com.example.geschat.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geschat.R;
import com.example.geschat.models.ProfileNextPrevChat;

import java.util.ArrayList;

public class ProfilePreviousChatAdapter extends RecyclerView.Adapter<ProfilePreviousChatAdapter.ViewHolderPNextC> {

    ArrayList<ProfileNextPrevChat> listaPrev;

    public ProfilePreviousChatAdapter(ArrayList<ProfileNextPrevChat> listaNext) {
        this.listaPrev = listaNext;
    }

    @NonNull
    @Override
    public ViewHolderPNextC onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_profile_next_chats_row, null , false);
        return new ViewHolderPNextC(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPNextC viewHolderPNextC, int i) {
        viewHolderPNextC.chatName.setText(listaPrev.get(i).getNombreChat());
        viewHolderPNextC.chatFecha.setText(listaPrev.get(i).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaPrev.size();
    }

    public class ViewHolderPNextC extends RecyclerView.ViewHolder {

        TextView chatName, chatFecha;

        public ViewHolderPNextC(@NonNull View itemView) {
            super(itemView);
            chatName = itemView.findViewById(R.id.profile_next_chat_name);
            chatFecha = itemView.findViewById(R.id.profile_next_chat_fecha);
        }
    }
}
