package com.example.countdowner;

import android.widget.TextView;

import java.util.Calendar;

class Event {

    private Calendar now;
    private Calendar thatDay;
    private Calendar start;
    private long startDiffInMillis;
    private long diffInMillis;
    private long daysLeft;
    private long hoursLeft;
    private long minutesLeft;
    private long secondsLeft;

    Event(){
        now = Calendar.getInstance();
        thatDay = Calendar.getInstance();
        start = Calendar.getInstance();
        startDiffInMillis = 0;
        diffInMillis = 0;
        daysLeft = 0;
        hoursLeft = 0;
        minutesLeft = 0;
        secondsLeft = 0;
    }
    public void showTextProgress(TextView dateText){
        now = Calendar.getInstance();
        startDiffInMillis = thatDay.getTimeInMillis() - start.getTimeInMillis();
        diffInMillis =  thatDay.getTimeInMillis() - now.getTimeInMillis();
        daysLeft = (long) (diffInMillis / (24 * 60 * 60 * 1000));
        hoursLeft = (long) (diffInMillis / (60 * 60 * 1000) % 24);
        minutesLeft = (long) (diffInMillis / (60 * 1000) % 60);
        secondsLeft = (long) ((diffInMillis / 1000) % 60);
        if(diffInMillis >= 0){
            String output = Long.toString(daysLeft) + " days, " + Long.toString(hoursLeft) + " hours, " + Long.toString(minutesLeft) + " minutes and " + Long.toString(secondsLeft) + " secounds left.";
            dateText.setText(output);
        }
        else{
            String output = "Countdown finished!";
            dateText.setText(output);
        }
    }
    public int progress(){
        int progress = 0;
        if(startDiffInMillis != 0){
            progress = (int)  (100 - (((diffInMillis * 100) / startDiffInMillis)));
        }
        return progress;
    }
    public void setTime(int hour, int minute){
        thatDay.set(Calendar.MINUTE, minute);
        thatDay.set(Calendar.HOUR_OF_DAY, hour);
    }
    public void setDate(int year, int month, int dayOfMonth){
        thatDay.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        thatDay.set(Calendar.MONTH,month); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);
    }
    public void setStart(){
        start = Calendar.getInstance();
    }
}
