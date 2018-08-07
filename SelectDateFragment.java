package android.attendancecalculator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by me on 03-04-2016.
 */

public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    InputAttendance inputAttendance = new InputAttendance();

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), this, yy, mm, dd);

    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {

        mm = mm + 1; // because month starts from 0 and ends at 11

        inputAttendance.date = dd + "/" + mm + "/" + yy; // modify date

    }


}

