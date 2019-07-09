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

public class FacChatCompletedAdapter extends RecyclerView.Adapter<FacChatCompletedAdapter.ViewHolderCompleted> {

    ArrayList<Chat> listaCompletados;
    private OnCompletedListener onCompletedListener;

    public FacChatCompletedAdapter(ArrayList<Chat> listaCompletados, OnCompletedListener onCompletedListener) {
        this.listaCompletados = listaCompletados;
        this.onCompletedListener = onCompletedListener;
    }

    @NonNull
    @Override
    public ViewHolderCompleted onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_fac_completed_chat_row,null,false);
        return new ViewHolderCompleted(view,onCompletedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCompleted viewHolderCompleted, int i) {
        viewHolderCompleted.chatName.setText(listaCompletados.get(i).getChatName());
        viewHolderCompleted.facName.setText(listaCompletados.get(i).getFacilitatorName());
        viewHolderCompleted.fecha.setText(listaCompletados.get(i).getDate());
        viewHolderCompleted.nivel.setText(listaCompletados.get(i).getLevel());
        viewHolderCompleted.inscritos.setText(Integer.toString(listaCompletados.get(i).getAmountPeople()));
    }

    @Override
    public int getItemCount() {
        return listaCompletados.size();
    }

    public class ViewHolderCompleted extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView chatName, facName, fecha,nivel,inscritos;
        OnCompletedListener onCompletedListener;

        public ViewHolderCompleted(@NonNull View itemView,OnCompletedListener onCompletedListener) {
            super(itemView);
            chatName = (TextView) itemView.findViewById(R.id.completedChat_chatName);
            facName = (TextView) itemView.findViewById(R.id.completedChat_name);
            fecha = (TextView) itemView.findViewById(R.id.completedChat_date);
            nivel = (TextView) itemView.findViewById(R.id.completedChat_level);
            inscritos = (TextView) itemView.findViewById(R.id.completedChat_inscritos);
            this.onCompletedListener = onCompletedListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCompletedListener.onCompletedClick(getAdapterPosition());
        }
    }

    public interface OnCompletedListener{
        void onCompletedClick(int position);

    }
}
