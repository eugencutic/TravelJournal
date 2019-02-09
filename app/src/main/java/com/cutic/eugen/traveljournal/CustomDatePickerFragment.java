package com.cutic.eugen.traveljournal;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CustomDatePickerFragment extends DialogFragment {

    private Bundle mArgs;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mArgs = getArguments();

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    AddTripActivity addTripActivity = (AddTripActivity) getActivity();

                    int pickDate = mArgs.getInt("pick_date", 1);
                    if(pickDate == 1)
                        addTripActivity.setStartDate(view.getDayOfMonth(), view.getMonth(), view.getYear());
                    else
                        addTripActivity.setEndDate(view.getDayOfMonth(), view.getMonth(), view.getYear());
                }
            };
}