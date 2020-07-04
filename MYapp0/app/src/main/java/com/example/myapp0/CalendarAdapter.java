package com.example.myapp0;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    private ArrayList<String> dateArray =new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    Calendar cl;
    public String apw;


    private static class ViewHolder {
        public TextView dateText;
    }

    public CalendarAdapter(Context context,String asw0,String asw) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        cl = Calendar.getInstance();
        apw = asw;
        int year = cl.get(Calendar.YEAR);
        int month = Integer.valueOf(asw0);
        System.out.println(month);
        System.out.println(cl.getTime());
        int g= computeWeekDay(year,month,1);
        System.out.println(g);
        int dy=1;
        switch(month) {
            case 1 : case 3 : case 5 :case 7: case 8 : case 10: case 12:
                for (int p = 0; p < cl.getActualMaximum(Calendar.WEEK_OF_MONTH) * 7; p++) {
                    if(p<g && month-1==2 && isLeapYear(year)){
                        dateArray.add(String.valueOf(29-g+p+1));
                        continue;
                    }else if(p<=g && month-1==2 && !isLeapYear(year)){
                        dateArray.add(String.valueOf(28-g+p+1));
                        continue;
                    }
                    else if(p<g && month-1==7){
                        dateArray.add(String.valueOf(31-g+p+1));
                        continue;
                    }
                    else if (p<g && month ==1){
                        dateArray.add(String.valueOf(31-g+p+1));
                        continue;
                    }
                    else if (p<g){
                        dateArray.add(String.valueOf(30-g+p+1));
                        continue;
                    }
                    if(dy>31){
                        dy=1;
                    }
                    dateArray.add(String.valueOf(dy));
                    dy++;
                }
                break;
            case 2:
                for (int p = 0; p < cl.getActualMaximum(Calendar.WEEK_OF_MONTH) * 7; p++) {
                    if (p<g){
                        dateArray.add(String.valueOf(31-g+p+1));
                        continue;
                    }
                    if(isLeapYear(year)) {
                        if (dy > 29) {
                            dy = 1;
                        }
                    }else {
                        if (dy > 28) {
                            dy = 1;
                        }
                    }
                    dateArray.add(String.valueOf(dy));
                    dy++;
                }
                break;
            case 4: case 6: case 9: case 11:
                for (int p = 0; p < cl.getActualMaximum(Calendar.WEEK_OF_MONTH) * 7 ; p++) {
                    if (p<g){
                        dateArray.add(String.valueOf(30-g+p+1));
                        continue;
                    }
                    if(dy>=31){
                        dy=1;
                    }
                    dateArray.add(String.valueOf(dy));
                    dy++;
                }
                break;
        }
    }
    static boolean isLeapYear(int year) {
        boolean leap = false;
        if(year >=1900) {
            if(year %100==0 ) {
                if(year %400 ==0) {
                    leap=true;
                }
            }else{
                if(year %4 ==0) {
                    leap=true;
                }
            }
        }
        return leap;
    }
    static int computeWeekDaySub(int month, int day) {
        int wd = 0;
        switch(month) {
            case 1:
                wd = (day-1)%7;
                break;
            case 2:
                day += 31;
                wd = (day-1)%7;
                break;
            case 3:
                day += 59;
                wd = (day-1)%7;
                break;
            case 4:
                day += 90;
                wd = (day-1)%7;
                break;
            case 5:
                day += 120;
                wd = (day-1)%7;
                break;
            case 6:
                day += 151;
                wd = (day-1)%7;
                break;
            case 7:
                day += 181;
                wd = (day-1)%7;
                break;
            case 8:
                day += 212;
                wd = (day-1)%7;
                break;
            case 9:
                day += 243;
                wd = (day-1)%7;
                break;
            case 10:
                day += 273;
                wd = (day-1)%7;
                break;
            case 11:
                day += 304;
                wd = (day-1)%7;
                break;
            case 12:
                day += 334;
                wd = (day-1)%7;
                break;
        }
        return wd;
    }

    static int computeWeekDay(int year, int month, int day) {
        int wd = (year + (year - 1) / 4 - (year - 1) / 100 +
                (year - 1) / 400 - 2359) % 7;
        // BEGIN
        if (isLeapYear(year)) {
            if(month <3) {
                wd=computeWeekDaySub(month,day+wd);
            }else {
                wd=computeWeekDaySub(month,day+1+wd);
            }
        }
        else {
            wd = computeWeekDaySub(month, day + wd);
        }
        return wd;
    }


    @Override
    public int getCount() {
        return dateArray.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.calendar_cell, null);
            holder = new ViewHolder();
            holder.dateText = convertView.findViewById(R.id.dateText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //セルのサイズを指定
        float dp = mContext.getResources().getDisplayMetrics().density;
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(parent.getWidth()/7 - (int)dp,  (parent.getHeight() - (int)dp * cl.getActualMaximum(Calendar.WEEK_OF_MONTH) ) / cl.getActualMaximum(Calendar.WEEK_OF_MONTH));
        convertView.setLayoutParams(params);
        /////////////////////



        ///////////////////
        //System.out.println(rs);
        holder.dateText.setText(dateArray.get(position));

        //当月以外のセルをグレーアウト
        convertView.setBackgroundColor(Color.WHITE);
        //日曜日を赤、土曜日を青に
        int colorId = Color.BLACK;
        if(position%7==0){
            colorId=Color.RED;
        }
        else if(position%7==6){
            colorId=Color.BLUE;
        }
        System.out.println(apw);
        if(dateArray.get(position).equals(apw)) {
            colorId = Color.MAGENTA;
        }
        holder.dateText.setTextColor(colorId);

        return convertView;


    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}

