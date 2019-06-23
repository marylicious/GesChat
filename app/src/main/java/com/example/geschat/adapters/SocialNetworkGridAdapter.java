package com.example.geschat.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.geschat.ContactFragment;
import com.example.geschat.MainActivity;
import com.example.geschat.R;

public class SocialNetworkGridAdapter extends BaseAdapter {

    int logos[];
    Context context;
    LayoutInflater inflater=null;

    public SocialNetworkGridAdapter(Context appContext, int[] logos) {
        this.context=appContext;
        this.logos=logos;
        this.inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return logos.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fragment_contactus, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.gv_socialnetworkicon); // get the reference of ImageView
        icon.setImageResource(logos[i]); // set logo images
        return view;
    }



}
