package com.example.geschat.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.example.geschat.BlankFragment;
import com.example.geschat.ContactFragment;
import com.example.geschat.DashFacilitadorFragment;
import com.example.geschat.DashSupervisorFragment;
import com.example.geschat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private final Boolean Supervisor;

    public SectionsPagerAdapter(Context context, FragmentManager fm, Boolean isSupervisor) {
        super(fm);
        mContext = context;
        Supervisor = isSupervisor;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                    fragment = new DashFacilitadorFragment();
                break;
            case 1:
                if(Supervisor){
                    fragment = new DashSupervisorFragment();}
                else{
                    fragment = new BlankFragment();}
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }


}