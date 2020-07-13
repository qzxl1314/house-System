package com.example.housemanagementsystem.tool;

import java.text.SimpleDateFormat;

public class time {
    public String getTime(){
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM");

        long nowMills=System.currentTimeMillis();

        String time=sdf3.format(nowMills);
        return time;
    }
}
