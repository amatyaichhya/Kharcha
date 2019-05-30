package com.thenewme.gharbachat;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    int id;

    EditText username,email;

    Button update;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        id = getIntent().getIntExtra("id",0);

        update = findViewById(R.id.update);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        databaseHelper = new DatabaseHelper(this);
        userinfo info = databaseHelper.getuserinfo(""+id);
        username.setText(info.username);
        email.setText(info.email);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString();
                String emailVal = email.getText().toString();

                final ContentValues contentValues = new ContentValues();
                contentValues.put("username",usernameVal);
                contentValues.put("email",emailVal);

                databaseHelper.updateuser(""+id,contentValues);
                Toast.makeText(update.this,"Updated",Toast.LENGTH_SHORT);
                finish();
            }
        });


    }
}
