package com.thenewme.gharbachat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Expense extends AppCompatActivity {

    ListView listView;

    LinearLayout container;

    DatabaseHelper databaseHelper;

    Adapter_listview adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listview);
        registerForContextMenu(listView);

        container = findViewById(R.id.container);

        databaseHelper =new DatabaseHelper(this);

        adapter = new Adapter_listview(this,databaseHelper.getexpenselist());


        //listView.setAdapter(adapter);

    }


    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listview) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
       }*/

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private int id;
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getOrder()) {
            case 1:
                Intent intent = new Intent(Expense.this,Add_expense.class);
                id = item.getGroupId();
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case 2:
                id = item.getGroupId();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Delete item !!");
                builder.setMessage("Are you sure you want to delete this?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.deleteexpense(id);
                        expadapt();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                // remove stuff here
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public void expadapt(){
        listView.setAdapter(new Adapter_listview(this,databaseHelper.getexpenselist()));
    }

   @Override
    protected void onResume() {
        super.onResume();
       listView.setAdapter(new Adapter_listview(this,databaseHelper.getexpenselist()));
    }

    /*public void displayexpense()
    {
        ArrayList<ExpenseInfo> list = databaseHelper.getexpenselist();
        for (ExpenseInfo info:list)
        {
            View view = LayoutInflater.from(this).inflate(R.layout.Listview_inflator,null);
            TextView name=view.findViewById(R.id.name),
                    amount=view.findViewById(R.id.amount);

            name.setText(info.category);
            amount.setText(info.amount);

            container.addView(view);

        }
    }*/
}
