package com.example.geschat.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geschat.R;
import com.example.geschat.models.UserListChatDetailsModel;

import java.util.ArrayList;

public class IncomingChatDetailsAdapter extends RecyclerView.Adapter<IncomingChatDetailsAdapter.ViewHolderCDIncoming> {

    ArrayList<UserListChatDetailsModel> listaIncoming;

    public IncomingChatDetailsAdapter(ArrayList<UserListChatDetailsModel> listaIncoming) {
        this.listaIncoming = listaIncoming;
    }

    @NonNull
    @Override
    public ViewHolderCDIncoming onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_chat_user_list_row,null,false);
        return new ViewHolderCDIncoming(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCDIncoming viewHolderCDIncoming, int i) {
        viewHolderCDIncoming.idUser.setText(listaIncoming.get(i).getId());
        viewHolderCDIncoming.pictUser.setImageResource(listaIncoming.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaIncoming.size();
    }

    public class ViewHolderCDIncoming extends RecyclerView.ViewHolder {

        TextView idUser;
        ImageView pictUser;

        public ViewHolderCDIncoming(@NonNull View itemView) {
            super(itemView);
            idUser = (TextView) itemView.findViewById(R.id.finished_chat_details_userId);
            pictUser = (ImageView) itemView.findViewById(R.id.finished_chat_details_user_prof_pict);
        }
    }
}
