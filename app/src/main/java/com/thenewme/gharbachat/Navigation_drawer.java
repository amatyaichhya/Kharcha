package com.thenewme.gharbachat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class Navigation_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout accounts,records;

    TimePicker timePicker;

    ViewPager pager;

    Accounts accountsFragment;

    Records recordsFragment;

    SharedPreferences preferences;

    TextView username,email,ok,cancel;

    ImageView gender;

    String id;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        preferences = getSharedPreferences("Userinfo",0);

        //id = getIntent().getStringExtra("id");

        id = preferences.getString("userid","");

        databaseHelper = new DatabaseHelper(this);

        accounts = findViewById(R.id.accounts);
        records = findViewById(R.id.records);

        accountsFragment=new Accounts();
        recordsFragment=new Records();

        accounts.setBackgroundColor(Color.TRANSPARENT);
        records.setBackgroundColor(Color.TRANSPARENT);

        accounts.setBackgroundResource(R.drawable.layerlist);
        getSupportFragmentManager().beginTransaction().replace(R.id.containermain,accountsFragment).commit();

        pager = findViewById(R.id.containermain);

        pager.setAdapter(new Navigation_drawer.mainpageadapter(getSupportFragmentManager()));

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                accounts.setBackgroundColor(Color.TRANSPARENT);
                records.setBackgroundColor(Color.TRANSPARENT);

                if(position==0)
                {
                    accounts.setBackgroundResource(R.drawable.layerlist);
                }
                else
                {
                    records.setBackgroundResource(R.drawable.layerlist);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        username = navigationView.getHeaderView(0).findViewById(R.id.username);
        email = navigationView.getHeaderView(0).findViewById(R.id.email);
        gender = navigationView.getHeaderView(0).findViewById(R.id.imageView);

        username.setText(preferences.getString("username", null));
        email.setText(preferences.getString("email", null));

        /*userinfo info = databaseHelper.getuserinfo(id);
        username.setText(info.username);
        email.setText(info.email);*/

        /*String sex = preferences.getString("gender", null);

        userinfo info = databaseHelper.getuserinfo(id);

        if (info.image != null) {
            gender.setImageBitmap(getBitmap(info.image));
        } else if (sex.equals("Male")) {
            gender.setImageResource(R.drawable.);
        } else if (sex.equals("Female")) {
            gender.setImageResource(R.drawable.);
        }
        //   }*/
    }
    @Override
    protected void onResume() {
        super.onResume();
        displayuser();
    }

    public void displayuser()
    {
        userinfo info = databaseHelper.getuserinfo(id);
        username.setText(info.username);
        email.setText(info.email);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.reminder) {
            showCustomdialog();

        } else if (id == R.id.logout) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Navigation_drawer.this);

            builder.setTitle("                   Kharcha");
            builder.setMessage("Logout?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    preferences.edit().putBoolean("rememberme",false).apply();
                    startActivity(new Intent(Navigation_drawer.this,tabloginActivity.class));
                    finish();
                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();


        } else if (id == R.id.nav_share) {
            Intent myintent = new Intent(Intent.ACTION_SEND);
            myintent.setType("text/plain");
            String sharesub = "Kharcha";
            String sharebody = "Download this app to manage your money efficiently";
            myintent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
            myintent.putExtra(Intent.EXTRA_TEXT,sharebody);
            startActivity(Intent.createChooser(myintent,"Share using"));

        } else if (id == R.id.about) {

        }else if (id == R.id.feedback) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={"amatyaichhya@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback");
            intent.putExtra(Intent.EXTRA_TEXT,"");
            intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void maintabclick (View view)
    {
        accounts.setBackgroundColor(Color.TRANSPARENT);
        records.setBackgroundColor(Color.TRANSPARENT);

        if (view.getId()==R.id.accounts)
        {
            accounts.setBackgroundResource(R.drawable.layerlist);
            pager.setCurrentItem(0);
        }
        else
        {
            records.setBackgroundResource(R.drawable.layerlist);
            pager.setCurrentItem(1);
        }
    }

    public class mainpageadapter extends FragmentPagerAdapter
    {

        public mainpageadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            if(position == 0)
                return new Accounts();
            return new Records();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void showCustomdialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Set a Reminder");

        View view = LayoutInflater.from(this).inflate(R.layout.activity_reminder,null);

        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        ok = view.findViewById(R.id.ok);
        cancel = view.findViewById(R.id.cancel);

        timePicker.setIs24HourView(false); // used to display AM/PM mode

        // perform set on time changed listener event
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minutes = mcurrentTime.get(Calendar.MINUTE);

                // display a toast with changed values of time picker
                Toast.makeText(getApplicationContext(), hourOfDay + " - " + minute, Toast.LENGTH_SHORT).show();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String timevalue = timePicker.getHour()+"-"+timePicker.getMinute();
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.setContentView(view);
        dialog.show();

    }

}
