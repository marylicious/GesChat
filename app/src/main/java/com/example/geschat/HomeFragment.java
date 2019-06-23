package com.example.geschat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geschat.adapters.AnnouncementAdapter;
import com.example.geschat.models.Announcement;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements AnnouncementAdapter.OnAnnListListener{

    ArrayList<Announcement> anns;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container,false);

        //Boton
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.btn_addAnnouncement);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddAnnouncementActivity.class);
                startActivity(in);
            }
        });

        //RecyclerView
        RecyclerView rvAnnouncements = (RecyclerView) view.findViewById(R.id.rvAnnouncements);

        //Falsos anuncios
        anns = Announcement.createAnnList(20);

        AnnouncementAdapter annAdapter = new AnnouncementAdapter(anns,this);

        rvAnnouncements.setAdapter(annAdapter);

        rvAnnouncements.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;

    }

    @Override
    public void onAnnClick(int position) {

        Announcement ann = anns.get(position);
        Intent intent = new Intent(getActivity(), AnnouncementActivity.class);

        startActivity(intent);

    }
}
