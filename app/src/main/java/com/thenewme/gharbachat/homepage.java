package com.thenewme.gharbachat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {

    LinearLayout overview;
    SharedPreferences preferences;
    DatabaseHelper databaseHelper;
    LinearLayout savings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        preferences = getSharedPreferences("Userinfo",0);

        savings = findViewById(R.id.savings);

        savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this,profile.class));
            }
        });

        databaseHelper=new DatabaseHelper(this);
        findViewById(R.id.popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopmenu(v);
            }
        });

    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.settings:
            {
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.info:
            {
                Toast.makeText(this, "info", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.logout:
            {
                preferences.edit().putBoolean("rememberme",false).apply();
                startActivity(new Intent(homepage.this,tabloginActivity.class));
                finish();
                break;
            }
            case R.id.profile:
            {
                 Intent intent=new Intent(this,profile.class);
                intent.putExtra("id",preferences.getString("userid","0"));
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void showpopmenu(View anchor)
    {
        PopupMenu popupMenu = new PopupMenu(this,anchor);
        getMenuInflater().inflate(R.menu.weektallypopup,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.week1:
                    {
                        startActivity(new Intent(homepage.this,week1.class));
                        break;
                    }
                    case R.id.week2:
                    {
                        startActivity(new Intent(homepage.this,week2.class));
                        break;
                    }
                    case R.id.week3:
                    {
                        startActivity(new Intent(homepage.this,week3.class));
                        break;
                    }
                    case R.id.week4:
                    {
                        startActivity(new Intent(homepage.this,week4.class));
                        break;
                    }
                    case R.id.week5:
                    {
                        startActivity(new Intent(homepage.this,week5.class));
                        break;
                    }
                }

                return false;
            }
        });
        popupMenu.show();
    }

}
