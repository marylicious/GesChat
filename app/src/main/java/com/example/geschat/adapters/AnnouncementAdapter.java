package com.example.geschat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.geschat.R;
import com.example.geschat.models.Announcement;
import java.util.List;


public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    private List<Announcement> anns;
    private OnAnnListListener onAnnListListener;


    public AnnouncementAdapter(List<Announcement> anns, OnAnnListListener onAnnListListener){
        this.anns = anns;
        this.onAnnListListener = onAnnListListener;
    }


    //Defino ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTextView,authorTextView,bodyTextView,dateTextView;
        OnAnnListListener onAnnListener;

        public ViewHolder(View itemView, OnAnnListListener onAnnListener) {

            super(itemView);

            titleTextView =  itemView.findViewById(R.id.AnnRow_title);
            authorTextView =  itemView.findViewById(R.id.AnnRow_author);
            bodyTextView = itemView.findViewById(R.id.AnnRow_textbody);
            dateTextView =  itemView.findViewById(R.id.AnnRow_date);

            this.onAnnListener = onAnnListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view){
            onAnnListener.onAnnClick(getAdapterPosition());
        }

    }


    public interface OnAnnListListener{
        void onAnnClick(int position);
    }


    @Override
    public AnnouncementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View annView = inflater.inflate(R.layout.fragment_home_annrow, parent, false);

        ViewHolder viewHolder = new ViewHolder(annView,onAnnListListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(AnnouncementAdapter.ViewHolder viewHolder, int position) {

        Announcement ann = anns.get(position);

        TextView titleTV = viewHolder.titleTextView;
        titleTV.setText(ann.getTitle());

        TextView authorTV = viewHolder.authorTextView;
        authorTV.setText(ann.getAuthor());

        TextView dateTV = viewHolder.dateTextView;
        dateTV.setText(ann.getDate());


        TextView bodyTV = viewHolder.bodyTextView;
        bodyTV.setText(ann.getPreview());

    }


    @Override
    public int getItemCount() {
        return anns.size();
    }

}
