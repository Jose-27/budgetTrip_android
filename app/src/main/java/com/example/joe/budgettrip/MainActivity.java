package com.example.joe.budgettrip;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCheckin, mCheckout;

    private int month,day,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mCheckin = (TextView) findViewById(R.id.checkin);
        mCheckout = (TextView) findViewById(R.id.checkout);

        mCheckin.setOnClickListener(this);
        mCheckout.setOnClickListener(this);
        mCheckin.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.calendar, 0, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {


        if (v==mCheckin){
            final Calendar c = Calendar.getInstance();

            month= c.get(Calendar.MONTH);
            day=c.get(Calendar.DAY_OF_MONTH);
            year=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mCheckin.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                }
            }, month, day, year);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        }

        if (v==mCheckout){
            final Calendar c = Calendar.getInstance();

            month= c.get(Calendar.MONTH);
            day=c.get(Calendar.DAY_OF_MONTH);
            year=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mCheckout.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                }
            }, month, day, year);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
    }
}
