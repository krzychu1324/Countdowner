package com.example.countdowner;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextView dateText;
    ProgressBar progressBar;
    Event event = new Event();
    Button showDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.dateText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        showDialog = (Button) findViewById(R.id.showDialog);

        final Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int progress = event.progress();
                                event.showTextProgress(dateText);
                                progressBar.setProgress(progress);
                                if(progress != 0) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
        showDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        showTimePickerDialog();
        datePickerDialog.show();
    }
    private void showTimePickerDialog(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        event.setDate(year, month, dayOfMonth);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        event.setTime(hour, minute);
    }

}
