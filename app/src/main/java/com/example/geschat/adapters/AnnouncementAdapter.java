package com.example.geschat.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView,authorTextView,bodyTextView,dateTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.AnnRow_title);
            authorTextView = (TextView) itemView.findViewById(R.id.AnnRow_author);
            bodyTextView =(TextView) itemView.findViewById(R.id.AnnRow_textbody);
            dateTextView = (TextView) itemView.findViewById(R.id.AnnRow_textbody);

        }

    }

    public AnnouncementAdapter(List<Announcement> anns){
        this.anns = anns;
    }

    @Override
    public AnnouncementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View annView = inflater.inflate(R.layout.fragment_home_annrow, parent, false);

        ViewHolder viewHolder = new ViewHolder(annView);
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
        bodyTV.setText(ann.getBody());

    }


    @Override
    public int getItemCount() {
        return anns.size();
    }

}
