package com.example.joe.budgettrip;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int DEPARTURE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int LANDING_AUTOCOMPLETE_REQUEST_CODE   = 2;

    private static final String TAG = "";

    private int month, day, year;

    @BindView(R.id.checkin)   TextView mCheckin;
    @BindView(R.id.checkout)  TextView mCheckout;
    @BindView(R.id.departure) TextView mDeparture;
    @BindView(R.id.landing)   TextView mLanding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.checkin, R.id.checkout,R.id.landing,R.id.departure})
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        
        switch (v.getId()){
            case R.id.departure:
                openAutocompleteActivity(DEPARTURE_AUTOCOMPLETE_REQUEST_CODE);
                break;
            case R.id.landing:
                openAutocompleteActivity(LANDING_AUTOCOMPLETE_REQUEST_CODE);
                break;
            case R.id.checkin:

                Calendar c = Calendar.getInstance();

                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                year = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCheckin.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, month, day, year);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.checkout:

                c = Calendar.getInstance();

                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                year = c.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCheckout.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, month, day, year);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DEPARTURE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);

                mDeparture.setText(place.getAddress());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {

                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, "Error: Status = " + status.toString());

            } else if (resultCode == RESULT_CANCELED) {

            }
        }
        else if (requestCode == LANDING_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);

                mLanding.setText(place.getAddress());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {

                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, "Error: Status = " + status.toString());

            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    private void openAutocompleteActivity(int mode) {

        try {
            // The autocomplete activity requires Google Play Services to be available. The intent
            // builder checks this and throws an exception if it is not the case.
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, mode);
        } catch (GooglePlayServicesRepairableException e) {
            // Indicates that Google Play Services is either not installed or not up to date. Prompt
            // the user to correct the issue.
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e(TAG, message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

}
