package com.thenewme.gharbachat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Records extends Fragment{

    calendar calendar;
    //recordsFragment recordsFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.records_layout,null);

        calendar = new calendar();
        //recordsFragment = new recordsFragment();


        getFragmentManager().beginTransaction().replace(R.id.containercalendar,calendar,null).commit();

        getFragmentManager().beginTransaction().replace(R.id.containerrecords,new Calendar_fragment(),null).commit();
        return view;
    }
}
