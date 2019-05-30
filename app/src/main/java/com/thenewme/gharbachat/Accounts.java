package com.thenewme.gharbachat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class Accounts extends Fragment {

    FloatingActionMenu menu;
    FloatingActionButton b1,b2;
    LinearLayout income,expense;
    TextView credit,cash,balance;
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_accounts,null);

        databaseHelper = new DatabaseHelper(getActivity());

        credit = view.findViewById(R.id.credit);
        cash = view.findViewById(R.id.cash);
        balance = view.findViewById(R.id.balance);

        cash.setText(String.valueOf(databaseHelper.getcashtotal()));
        credit.setText(String.valueOf(databaseHelper.getcredittotal()));
        balance.setText(String.valueOf(databaseHelper.getbalance()));

        income = view.findViewById(R.id.income);
        expense = view.findViewById(R.id.expense);

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Income.class);
                startActivity(intent);
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Expense.class);
                startActivity(intent);
            }
        });


        menu = view.findViewById(R.id.menu);
        menu.setClosedOnTouchOutside(true);

        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

            }
        });

        b1=view.findViewById(R.id.b1);
        b2=view.findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.close(true);
                Intent intent = new Intent(getActivity(),Add_expense.class);
                startActivity(intent);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.close(true);
                Intent intent=new Intent(getActivity(),Add_income.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {

        cash.setText(String.valueOf(databaseHelper.getcashtotal()));
        credit.setText(String.valueOf(databaseHelper.getcredittotal()));
        balance.setText(String.valueOf(databaseHelper.getbalance()));
        super.onResume();

    }
}
