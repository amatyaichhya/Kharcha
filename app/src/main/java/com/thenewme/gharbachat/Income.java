package com.thenewme.gharbachat;

import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;

public class Income extends AppCompatActivity {

    LinearLayout container;

    DatabaseHelper databaseHelper;

    Adapter_listview_income adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        listView = findViewById(R.id.listview);
        registerForContextMenu(listView);

        container = findViewById(R.id.container);

        databaseHelper =new DatabaseHelper(this);

        adapter = new Adapter_listview_income(this,databaseHelper.getincomelist());

        listView.setAdapter(adapter);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private int id;
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getOrder()) {
            case 1:
                Intent intent = new Intent(Income.this,Add_income.class);
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
                        databaseHelper.deleteincome(id);
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
        listView.setAdapter(new Adapter_listview_income(this,databaseHelper.getincomelist()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(new Adapter_listview_income(this,databaseHelper.getincomelist()));
    }

}
