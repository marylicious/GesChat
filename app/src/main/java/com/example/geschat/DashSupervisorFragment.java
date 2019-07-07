package com.example.geschat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

public class DashSupervisorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dash_supervisor_fragment, container,false);

        LinearLayout chatProposal = (LinearLayout) view.findViewById(R.id.dash_sup_chatproposals);

        //On click listener
        chatProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ChatReviewActivity.class);
                startActivity(in);
            }
        });

        LinearLayout homeUpdate = (LinearLayout) view.findViewById(R.id.dashHomeUpdate);

        homeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), HomePageUpdateActivity.class);
                startActivity(in);
            }
        });

        LinearLayout finishedChats = (LinearLayout) view.findViewById(R.id.dash_sup_finishedchats);


        finishedChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ChatFinishedActivity.class);
                startActivity(in);
            }
        });


        return view;
    }
}
