package com.example.geschat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.geschat.adapters.SocialNetworkGridAdapter;


public class ContactFragment extends Fragment{

    int logos[]={
            R.drawable.facebook, R.drawable.gmail, R.drawable.whatsapp, R.drawable.twitter,R.drawable.world_wide_web, R.drawable.instagram
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_contactus, container,false);
        GridView gv=(GridView) view.findViewById(R.id.gv_socialnetworks);
        gv.setAdapter(new SocialNetworkGridAdapter(view.getContext(), logos));

        return view;
    }
}
