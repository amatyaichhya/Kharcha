package com.thenewme.gharbachat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    String id;
    DatabaseHelper databaseHelper;
    TextView name,email;
    ImageView edit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        id=getIntent().getStringExtra("id");

        databaseHelper=new DatabaseHelper(this);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        edit=findViewById(R.id.edit);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this,homepage.class));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(profile.this,update.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayuser();
    }

    public void displayuser()
    {
        userinfo info = databaseHelper.getuserinfo(id);
        name.setText(info.username);
        email.setText(info.email);

    }
}
