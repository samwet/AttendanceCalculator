package android.attendancecalculator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by me on 27-02-2016.
 */
public class InputAttendance extends Fragment {
    View rootView;
    ImageButton imageButton;
    Button submitButton;
    static int year, month, day;
    NumberPicker numberPicker;
    static int maxLectures = 8;
    TextView maxLecturesText;
    Context context;

    // declare Variables for Date and Lectures
    public static String date;
    public static int lecturesAttended;

    DatabaseHandler databaseHandler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_input_attendance, container, false);

        databaseHandler = new DatabaseHandler(getActivity(),null,null,1);

        imageButton= (ImageButton)rootView.findViewById(R.id.changeDateButton);
        submitButton= (Button)rootView.findViewById(R.id.submitButton);
        numberPicker=(NumberPicker)rootView.findViewById(R.id.numberPicker);
        maxLecturesText=(TextView)rootView.findViewById(R.id.maxLecturesText);
        maxLecturesText.setText("Note: Total Lectures Per Day = " + maxLectures);


        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);


        // converting date into a single String
        date = day + "/" + month + "/" + year;

        // store the value of lectures as maxlectures by default
        lecturesAttended = maxLectures;


        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });


        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(maxLectures);
        numberPicker.setValue(maxLectures);
        numberPicker.setWrapSelectorWheel(false);


        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                lecturesAttended = newVal; // update value of lectures
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Attendance attendance = new Attendance(date, lecturesAttended);
                databaseHandler.addAttendance(attendance);
                Toast.makeText(getActivity(), "Saved in Database", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

}
