package com.kevin.calendarapp;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import org.joda.time.DateTime;
import java.time.LocalDate;
import java.util.GregorianCalendar;

public class CalendarFragment extends Fragment
{
    GridView gridview;
    TextView nameofMonth;
    GregorianCalendar calendar;

    String dayList[];

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle args = getArguments();
        int index = args.getInt("index", 0);
        dayList = new String[42];
        calendar = new GregorianCalendar();
        configureCalendar(index);
        View v = inflater.inflate(R.layout.sheets, container, false);
        gridview = v.findViewById(R.id.gridView);
        nameofMonth = v.findViewById(R.id.month);
        nameofMonth.setText(getMonthName(index));
        gridview.setAdapter(new GridAdapter(getContext(), dayList, index));

        return v;
    }

    private String getMonthName(int i)
    {
        switch (i)
        {
            case 0:
                if(Build.VERSION.SDK_INT > 25)
                    return "January " + LocalDate.now().getYear();
                else
                    return "January " + DateTime.now().getYear();
            case 1:
                if(Build.VERSION.SDK_INT > 25)
                    return "February " + LocalDate.now().getYear();
                else
                    return "February " + DateTime.now().getYear();
            case 2:
                if(Build.VERSION.SDK_INT > 25)
                    return "March " + LocalDate.now().getYear();
                else
                    return "March " + DateTime.now().getYear();
            case 3:
                if(Build.VERSION.SDK_INT > 25)
                    return "April " + LocalDate.now().getYear();
                else
                    return "April " + DateTime.now().getYear();
            case 4:
                if(Build.VERSION.SDK_INT > 25)
                    return "May " + LocalDate.now().getYear();
                else
                    return "May " + DateTime.now().getYear();
            case 5:
                if(Build.VERSION.SDK_INT > 25)
                    return "June " + LocalDate.now().getYear();
                else
                    return "June " + DateTime.now().getYear();
            case 6:
                if(Build.VERSION.SDK_INT > 25)
                    return "July " + LocalDate.now().getYear();
                else
                    return "July " + DateTime.now().getYear();
            case 7:
                if(Build.VERSION.SDK_INT > 25)
                    return "August " + LocalDate.now().getYear();
                else
                    return "August " + DateTime.now().getYear();
            case 8:
                if(Build.VERSION.SDK_INT > 25)
                    return "September " + LocalDate.now().getYear();
                else
                    return "September " + DateTime.now().getYear();
            case 9:
                if(Build.VERSION.SDK_INT > 25)
                    return "October " + LocalDate.now().getYear();
                else
                    return "October " + DateTime.now().getYear();
            case 10:
                if(Build.VERSION.SDK_INT > 25)
                    return "November " + LocalDate.now().getYear();
                else
                    return "November " + DateTime.now().getYear();
            case 11:
                if(Build.VERSION.SDK_INT > 25)
                    return "December " + LocalDate.now().getYear();
                else
                    return "December " + DateTime.now().getYear();
            default:
        }

        return null;
    }

    private void fillDates(String firstDay, int month)
    {
        int currentMonth;
        int lastdayOfPreviousMonth;
        int lastdayOfCurrentMonth;
        int i;
        int currentDate;
        int firstDate;
        DateTime jodaDate;

        jodaDate = DateTime.now();
        currentMonth = jodaDate.getMonthOfYear();
        currentMonth -= 1;

        if(currentMonth > month)
            jodaDate = jodaDate.minusMonths(currentMonth - month);

        else
            jodaDate = jodaDate.plusMonths(month - currentMonth);


        jodaDate = jodaDate.dayOfMonth().withMaximumValue();
        lastdayOfCurrentMonth = jodaDate.getDayOfMonth();
        jodaDate = jodaDate.minusMonths(1);
        jodaDate = jodaDate.dayOfMonth().withMaximumValue();
        lastdayOfPreviousMonth = jodaDate.getDayOfMonth();

        if(firstDay.equals("SUNDAY") || firstDay.equals("Sunday"))
            firstDate = 0;

        else if(firstDay.equals("MONDAY") || firstDay.equals("Monday"))
            firstDate = 1;

        else if(firstDay.equals("TUESDAY") || firstDay.equals("Tuesday"))
            firstDate = 2;

        else if(firstDay.equals("WEDNESDAY") || firstDay.equals("Wednesday"))
            firstDate = 3;

        else if(firstDay.equals("THURSDAY") || firstDay.equals("Thursday"))
            firstDate = 4;

        else if(firstDay.equals("FRIDAY") || firstDay.equals("Friday"))
            firstDate = 5;

        else
            firstDate = 6;

        currentDate = 1;

        for(i = firstDate - 1; i >= 0; i--)
        {
            dayList[i] = Integer.toString(lastdayOfPreviousMonth--);
        }

        for(i = firstDate;  i < 42 && currentDate <= lastdayOfCurrentMonth; i++)
        {
            dayList[i] = Integer.toString(currentDate++);
        }

        currentDate = 1;

        for(; i < 42; i++)
        {
            dayList[i] = Integer.toString(currentDate++);
        }
    }

    private void configureCalendar(int i)
    {
        int year;
        int currentMonth;
        int dayOfMonth;
        LocalDate date;
        LocalDate newDate;
        DateTime jodaDate;
        String firstDay;

        if(Build.VERSION.SDK_INT > 25) {
            date = LocalDate.now();
            year = date.getYear();
            currentMonth = date.getMonthValue();
            currentMonth -= 1;

            if (currentMonth > i)
                date = date.minusMonths(currentMonth - i);

            else
                date = date.plusMonths(i - currentMonth);

            newDate = LocalDate.of(year, date.getMonth(), 1);
            firstDay = newDate.getDayOfWeek().toString();
            fillDates(firstDay, i);
        }

        else
        {
            jodaDate = DateTime.now();
            currentMonth = jodaDate.getMonthOfYear();
            currentMonth -= 1;

            if(currentMonth > i)
                jodaDate = jodaDate.minusMonths(currentMonth - i);

            else
                jodaDate = jodaDate.plusMonths(i - currentMonth);

            dayOfMonth = jodaDate.getDayOfMonth();
            dayOfMonth = dayOfMonth - 1;
            jodaDate = jodaDate.minusDays(dayOfMonth);
            firstDay = jodaDate.dayOfWeek().getAsText();
            fillDates(firstDay, i);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onStart() {
        super.onStart();
    }
}