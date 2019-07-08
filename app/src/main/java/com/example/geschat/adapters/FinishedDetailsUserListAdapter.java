package com.example.geschat.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geschat.R;
import com.example.geschat.models.FinishedDetailsUserList;

import java.util.ArrayList;

public class FinishedDetailsUserListAdapter extends RecyclerView.Adapter<FinishedDetailsUserListAdapter.ViewHolderUserList> {

    ArrayList<FinishedDetailsUserList> listaUsuario;

    public FinishedDetailsUserListAdapter(ArrayList<FinishedDetailsUserList> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @NonNull
    @Override
    public FinishedDetailsUserListAdapter.ViewHolderUserList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_chat_user_list_row,null,false);

        return new FinishedDetailsUserListAdapter.ViewHolderUserList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinishedDetailsUserListAdapter.ViewHolderUserList viewHolderUserList, int i) {
        viewHolderUserList.id.setText(listaUsuario.get(i).getId());
        viewHolderUserList.foto.setImageResource(listaUsuario.get(i).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class ViewHolderUserList extends RecyclerView.ViewHolder {

        TextView id;
        ImageView foto;

        public ViewHolderUserList(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.finished_chat_details_userId);
            foto = (ImageView) itemView.findViewById(R.id.finished_chat_details_user_prof_pict);
        }
    }
}
