package com.example.geschat.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geschat.R;
import com.example.geschat.models.Chat;

import java.util.ArrayList;

public class FacChatRevisionStatusAdapter extends RecyclerView.Adapter<FacChatRevisionStatusAdapter.ViewHolderFCRS> {
   ArrayList<Chat> listaStatus;
   private OnStatusListener onStatusListener;

    public FacChatRevisionStatusAdapter(ArrayList<Chat> listaStatus, OnStatusListener onStatusListener) {
        this.listaStatus = listaStatus;
        this.onStatusListener = onStatusListener;
    }

    @NonNull
    @Override
    public ViewHolderFCRS onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_fac_revision_status_chat_row,null,false);
        return new ViewHolderFCRS(view, onStatusListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFCRS viewHolderFCRS, int i) {
        viewHolderFCRS.chatNombre.setText(listaStatus.get(i).getChatName());
        viewHolderFCRS.facNombre.setText(listaStatus.get(i).getFacilitatorName());
        viewHolderFCRS.status.setText(listaStatus.get(i).getStatus());
        viewHolderFCRS.nivel.setText(listaStatus.get(i).getLevel());
        viewHolderFCRS.fecha.setText(listaStatus.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return listaStatus.size();
    }

    public class ViewHolderFCRS extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView chatNombre, facNombre, status,nivel,fecha;
        OnStatusListener onStatusListener;

        public ViewHolderFCRS(@NonNull View itemView,OnStatusListener onStatusListener) {

            super(itemView);
            chatNombre = (TextView) itemView.findViewById(R.id.revisionStatus_chatName);
            facNombre = (TextView) itemView.findViewById(R.id.revisionStatus_name_facilitador);
            status = (TextView) itemView.findViewById(R.id.revisionStatus_status);
            nivel = (TextView) itemView.findViewById(R.id.revisionStatus_level);
            fecha = (TextView) itemView.findViewById(R.id.revisionStatus_date);

            this.onStatusListener = onStatusListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onStatusListener.onStatusClick(getAdapterPosition());
        }
    }

    public interface OnStatusListener{
        void onStatusClick(int position);
    }
}
