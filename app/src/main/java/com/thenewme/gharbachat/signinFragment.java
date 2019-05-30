package com.thenewme.gharbachat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class signinFragment extends Fragment {

    EditText username,password;
    Button login;
    CheckBox rememberme;

    SharedPreferences preferences;
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_login,null);
        preferences = getActivity().getSharedPreferences("Userinfo",0);

        databaseHelper=new DatabaseHelper(getActivity());

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        rememberme = view.findViewById(R.id.rememberme);

        if (preferences.getBoolean("rememberme",false))
        {
            Intent intent = new Intent(getActivity(),Navigation_drawer.class);
            startActivity(intent);
            getActivity().finish();
        }

        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString();
                String passwordVal = password.getText().toString();

                Log.i("check","username:"+usernameVal);

                String registeredusername = preferences.getString("username"," ");
                String registeredpassword = preferences.getString("password"," ");
                if(databaseHelper.isloginsuccessful(usernameVal,passwordVal))
                {
                    Intent intent = new Intent(getActivity(),Navigation_drawer.class);
                    startActivity(intent);
                    if (rememberme.isChecked()) {
                        preferences.edit().putBoolean("rememberme", true).apply();

                    }
                    preferences.edit().putString("userid",""+databaseHelper.getUserId(usernameVal, passwordVal)).apply();

                    getActivity().finish();

                    Toast.makeText(getActivity(), "successfully logged in!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "login failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
