package com.thenewme.gharbachat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signin extends AppCompatActivity {

    EditText username,password,email,confirmpass;

    RadioGroup gender;

    Button signup;

    SharedPreferences preferences;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

         preferences = getSharedPreferences("Userinfo",0);

         databaseHelper = new DatabaseHelper(this);

         username = findViewById(R.id.username);
         password = findViewById(R.id.password);
         email = findViewById(R.id.email);
         gender = findViewById(R.id.gender);
         signup = findViewById(R.id.signup);
         confirmpass=findViewById(R.id.confirmpass);

         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String usernameVal = username.getText().toString();
                 String passwordVal = password.getText().toString();
                 String emailVal = email.getText().toString();
                 String confirmpassVal = confirmpass.getText().toString();

                 RadioButton checkedbtn = findViewById(gender.getCheckedRadioButtonId());
                 String genderVal = checkedbtn.getText().toString();

                 if(passwordVal==confirmpassVal)
                 {

                     SharedPreferences.Editor editor = preferences.edit();
                     editor.putString("username",usernameVal);
                     editor.putString("password",passwordVal);
                     editor.putString("email",emailVal);
                     editor.putString("confirmpass",confirmpassVal);
                     editor.putString("gender",genderVal);

                     editor.apply();

                     ContentValues contentValues=new ContentValues();
                     contentValues.put("username",usernameVal);
                     contentValues.put("password",passwordVal);
                     contentValues.put("email",emailVal);
                     contentValues.put("gender",genderVal);

                     databaseHelper.insertUser(contentValues);

                     Toast.makeText(signin.this, "username:" + usernameVal , Toast.LENGTH_SHORT).show();

                     Intent intent = new Intent(signin.this, login.class);
                     startActivity(intent);
                 }

             }
         });
    }
}
