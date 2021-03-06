package com.school.edsense_lite.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by shyam on 12/30/2015.
 */
public class DatePickerFragment extends DialogFragment {
    DatePickerDialog.OnDateSetListener dateSet;
    public DatePickerFragment() {
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener)
    {
        dateSet = listener;
    }

    private int year, month, day;
    private long maxdate;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
        maxdate = args.getLong("maxdate", 0);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog dialog =  new DatePickerDialog(getActivity(), dateSet, year, month, day);
        if(maxdate != 0){
            dialog.getDatePicker().setMaxDate(maxdate);
        }
        return dialog;
    }
}
