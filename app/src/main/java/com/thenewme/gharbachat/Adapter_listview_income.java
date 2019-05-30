package com.thenewme.gharbachat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter_listview_income extends ArrayAdapter<ExpenseInfo> {


    Context context;

    SharedPreferences preferences;

    public Adapter_listview_income(@NonNull Context context, ArrayList<ExpenseInfo> list) {
        super(context, 0,list);
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.listview_inflator,null);
        TextView name=view.findViewById(R.id.name),
                description = view.findViewById(R.id.description),
                amount=view.findViewById(R.id.amount);
        ImageView imageView = view.findViewById(R.id.image);

        final ExpenseInfo info = getItem(position);

        name.setText(info.category);
        amount.setText(String.valueOf(info.amount));

        final int id = info.id;

        if ("Cash".equals(info.category)) {
            imageView.setImageResource(R.drawable.ic_cash);

        } else if ("Credit Card".equals(info.category)) {
            imageView.setImageResource(R.drawable.ic_credit_card);

        }
        if(info.description.isEmpty()){
            description.setText("No description");
        }
        else{
            description.setText(info.description);
        }

        view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(id, v.getId(), 1,"Edit");
                menu.add(id, v.getId(), 2,"Delete");
            }
        });

        return view;
    }


}
