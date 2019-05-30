package com.thenewme.gharbachat;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Kharcha_main extends AppCompatActivity {

    LinearLayout accounts,records;

    ViewPager pager;

    Accounts accountsFragment;

    Records recordsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kharcha_main);

        accounts = findViewById(R.id.accounts);
        records = findViewById(R.id.records);

        accountsFragment=new Accounts();
        recordsFragment=new Records();

        accounts.setBackgroundColor(Color.TRANSPARENT);
        records.setBackgroundColor(Color.TRANSPARENT);

        accounts.setBackgroundResource(R.drawable.layerlist);
        getSupportFragmentManager().beginTransaction().replace(R.id.containermain,accountsFragment).commit();

        pager = findViewById(R.id.containermain);

        pager.setAdapter(new mainpageadapter(getSupportFragmentManager()));

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
}
