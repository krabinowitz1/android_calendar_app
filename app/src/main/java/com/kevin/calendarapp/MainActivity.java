package com.kevin.calendarapp;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import org.joda.time.DateTime;
import java.time.LocalDate;

public class MainActivity extends FragmentActivity
{
    GridView gridView;
    ViewPager viewPager = null;
    FragmentPagerAdapter adapterViewPager;
    ImageButton leftArrow;
    ImageButton rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.sheet);
        leftArrow = findViewById(R.id.left_nav);
        rightArrow = findViewById(R.id.right_nav);


        adapterViewPager = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(getCurrentMonth());
        leftArrow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                }
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });
    }

    private int getCurrentMonth()
    {
        LocalDate date;
        DateTime jodaDate;
        int month;

        if(Build.VERSION.SDK_INT > 25)
        {
            date = LocalDate.now();
            month = date.getMonthValue();
            month -= 1;
        }

        else
        {
            jodaDate = DateTime.now();
            month = jodaDate.getMonthOfYear();
            month -= 1;
        }

        switch(month)
        {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;
            default:
        }

        return 0;
    }
}

class MyAdapter extends FragmentPagerAdapter
{
    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        Fragment fragment;
        fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt("index", i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {

        return 12;
    }
}