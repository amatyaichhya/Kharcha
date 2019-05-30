package com.thenewme.gharbachat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Calendar_fragment extends Fragment {

    LinearLayout indepth,income,expense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar_records,null);

        indepth = view.findViewById(R.id.indepth);
        income = view.findViewById(R.id.income);
        expense = view.findViewById(R.id.expense);

        return view;
    }
}
