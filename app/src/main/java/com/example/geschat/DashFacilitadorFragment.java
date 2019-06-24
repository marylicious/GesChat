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

public class DashFacilitadorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.dash_facilitador_fragment, container,false);

        LinearLayout incoming = (LinearLayout) view.findViewById(R.id.dash_fac_incoming);

        //On click listener
        incoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ChatReviewActivity.class);
                startActivity(in);
            }
        });

        LinearLayout revision = (LinearLayout) view.findViewById(R.id.dash_fac_review);

        //On click listener
        revision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ChatReviewActivity.class);
                startActivity(in);
            }
        });

        LinearLayout finished = (LinearLayout) view.findViewById(R.id.dash_fac_finished);

        //On click listener
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ChatReviewActivity.class);
                startActivity(in);
            }
        });



        return view;
    }
}
