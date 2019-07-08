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

public class FacChatCompletedDetailsAdapter extends RecyclerView.Adapter<FacChatCompletedDetailsAdapter.ViewHolderCompDetails> {

    ArrayList<UserListChatDetailsModel> listaDetail;

    public FacChatCompletedDetailsAdapter(ArrayList<UserListChatDetailsModel> listaDetail) {
        this.listaDetail = listaDetail;
    }

    @NonNull
    @Override
    public ViewHolderCompDetails onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_chat_user_list_row,null,false);
        return new ViewHolderCompDetails(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCompDetails viewHolderCompDetails, int i) {
        viewHolderCompDetails.id.setText(listaDetail.get(i).getId());
        viewHolderCompDetails.foto.setImageResource(listaDetail.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaDetail.size();
    }

    public class ViewHolderCompDetails extends RecyclerView.ViewHolder {

        TextView id;
        ImageView foto;
        public ViewHolderCompDetails(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.finished_chat_details_userId);
            foto = (ImageView) itemView.findViewById(R.id.finished_chat_details_user_prof_pict);
        }
    }
}
