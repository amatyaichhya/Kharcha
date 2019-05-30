package com.thenewme.gharbachat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText username,password;
    Button login;
    CheckBox rememberme;

    SharedPreferences preferences;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("Userinfo",0);

        databaseHelper=new DatabaseHelper(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        rememberme = findViewById(R.id.rememberme);

        if (preferences.getBoolean("rememberme",false))
        {
            Intent intent = new Intent(login.this,homepage.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString();
                String passwordVal = password.getText().toString();

                Log.i("check","username:"+usernameVal);

                String registeredusername = preferences.getString("username"," ");
                String registeredpassword = preferences.getString("password"," ");
                if(databaseHelper.isloginsuccessful(usernameVal,passwordVal))
                {
                    Intent intent = new Intent(login.this,homepage.class);
                    startActivity(intent);
                    if (rememberme.isChecked()) {
                        preferences.edit().putBoolean("rememberme", true).apply();

                    }
                    preferences.edit().putString("userid",""+databaseHelper.getUserId(usernameVal, passwordVal)).apply();

                    Toast.makeText(login.this, "successfully logged in!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(login.this, "login failed!!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        );
    }

}
