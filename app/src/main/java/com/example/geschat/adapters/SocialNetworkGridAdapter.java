package com.example.geschat.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.geschat.ContactFragment;
import com.example.geschat.MainActivity;
import com.example.geschat.R;

public class SocialNetworkGridAdapter extends BaseAdapter {
    int count = 6;
    Context context;
    private static LayoutInflater inflater=null;

    public SocialNetworkGridAdapter(Context c) {
        // TODO Auto-generated constructor stub
        this.context=c;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return count;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View rowView = inflater.inflate(R.layout.fragment_contactus_griditem, null);
        return rowView;
    }



}
