package com.kevin.calendarapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.widget.Button;
import org.joda.time.DateTime;

public class GridAdapter extends BaseAdapter
{
    private String days[];
    private Context context;
    private int month;

    public GridAdapter(Context context, String days[], int month)
    {
        this.context = context;
        this.days = days;
        this.month = month;
    }

    @Override
    public int getCount()
    {
        return days.length;
    }

    @Override
    public Object getItem(int i)
    {
        return days[i];
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater;
        DateTime jodaDate = DateTime.now();
        View gridView = view;

        if(view == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.panels, viewGroup, false);
        }

        Button day = gridView.findViewById(R.id.days);

        day.setText(days[i]);


        if(i < 7 && Integer.parseInt(days[i]) > 7)
            day.setTextColor(Color.LTGRAY);

        if(i > 27 && Integer.parseInt(days[i]) < 22)
            day.setTextColor(Color.LTGRAY);

        if(month == (jodaDate.getMonthOfYear() - 1))
            if (i >= getFirstDayIndex() && i <= getLastDayIndex() && Integer.parseInt(days[i]) == jodaDate.getDayOfMonth())
                day.setBackgroundColor(Color.parseColor("#E8E8E8"));

        return gridView;
    }

    private int getFirstDayIndex()
    {
        int retval = 0;
        int i;

        for(i = 0; i < 6; i++)
            if(Integer.parseInt(days[i]) == 1)
                retval = i;

        return retval;
    }

    private int getLastDayIndex()
    {
        int retval = 0;
        int i;
        DateTime jodaDate;

        jodaDate = DateTime.now();
        jodaDate = jodaDate.dayOfMonth().withMaximumValue();

        for(i = 27; i < 42; i++)
            if(Integer.parseInt(days[i]) == jodaDate.getDayOfMonth())
                retval = i;

        return retval;
    }
}