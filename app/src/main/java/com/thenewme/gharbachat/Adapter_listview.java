package com.thenewme.gharbachat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter_listview extends ArrayAdapter<ExpenseInfo> {


    Context context;

    SharedPreferences preferences;

    public Adapter_listview(@NonNull Context context, ArrayList<ExpenseInfo> list) {
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

        view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(id, v.getId(), 1,"Edit");
                menu.add(id, v.getId(), 2,"Delete");
            }
        });

        if(info.description.isEmpty()){
            description.setText("No description");
        }
        else{
            description.setText(info.description);
        }

        switch (info.category){
            case "Household":
                imageView.setImageResource(R.drawable.ic_household);
                break;
            case "Eating-out":
                imageView.setImageResource(R.drawable.ic_eating_out);
                break;
            case "Grocery":
                imageView.setImageResource(R.drawable.ic_grocery);
                break;
            case "Personal":
                imageView.setImageResource(R.drawable.ic_personal);
                break;
            case "Utilities":
                imageView.setImageResource(R.drawable.ic_utilities);
                break;
            case "Medical":
                imageView.setImageResource(R.drawable.ic_medical);
                break;
            case "Education":
                imageView.setImageResource(R.drawable.ic_education);
                break;
            case "Entertainment":
                imageView.setImageResource(R.drawable.ic_entertainment);
                break;
            case "Clothing":
                imageView.setImageResource(R.drawable.ic_clothing);
                break;
            case "Transportation":
                imageView.setImageResource(R.drawable.ic_transportation);
                break;
            case "Shopping":
                imageView.setImageResource(R.drawable.ic_shopping);
                break;
            case "Others":
                imageView.setImageResource(R.drawable.savings);
                break;
            default:
                break;
        }
                /*preferences = context.getSharedPreferences("idinfo",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("id",info.id );*/


        return view;
    }

}
